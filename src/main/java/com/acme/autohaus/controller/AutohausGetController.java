/*
 * This file is part of [autohaus].
 *
 * [autohaus] is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * [autohaus] is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [autohaus]. If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.controller;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.security.JwtService;
import com.acme.autohaus.security.RolleAdmin;
import com.acme.autohaus.security.RolleAdminOrUser;
import com.acme.autohaus.service.AutohausReadService;
import com.c4_soft.springaddons.security.oidc.starter.properties.SpringAddonsOidcProperties;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.PostConstruct;

import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.NOT_MODIFIED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

/// Controller für die Verwaltung von Autohaus-Anfragen.
/// Dieser Controller bietet Endpunkte zum Abrufen von Autohäusern aus der Datenbank.
/// ![Klassendiagramm](../../../../../asciidoc/AutohausGetController.svg)
@RestController
@RequestMapping(AutohausGetController.API_PATH)
@OpenAPIDefinition(info = @Info(title = "Autohaus API", version = "v3"))
@SuppressWarnings({"ClassFanOutComplexity", "java:S1075"})
public class AutohausGetController {
    /**
     * Der Basis-Pfad für alle Endpunkte dieses Controllers.
     */
    public static final String API_PATH = "/autohaus";

    /// Muster bzw. regulärer Ausdruck für eine UUID.
    static final String ID_PATTERN = "[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}";

    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausGetController.class);

    private final AutohausReadService autohausReadService;

    private final SpringAddonsOidcProperties oidcProps;

    /**
     * Konstruktor für den AutohausGetController.
     *
     * @param autohausReadService Der Service zum Lesen von Autohaus-Daten.
     * @param oidcProps Die SpringAddOnsOidc-Properties
     */
    public AutohausGetController(final AutohausReadService autohausReadService,
                                 final SpringAddonsOidcProperties oidcProps) {
        this.autohausReadService = autohausReadService;
        this.oidcProps = oidcProps;
    }

    /// `@PostConstruct` aus _Common Annotations (JSR 250)_, um die _Issuer-URI_ für _OIDC_ zu protokollieren.
    @PostConstruct
    void logOidcProperties() {
        LOGGER.info("SpringAddonsOidcProperties: Issuer-URI = {}", oidcProps.getOps().getFirst().getIss());
    }

    /// Suche mit diversen Suchkriterien als Query-Parameter. Es wird eine Collection zurückgeliefert, damit auch der
    /// Statuscode `204` möglich ist.
    ///
    /// @param queryParams Query-Parameter als Map.
    /// @param request Das Request-Objekt, um Links für _HATEOAS_ zu erstellen.
    /// @return Ein Response mit dem Statuscode `200` und einer Collection mit den gefundenen Bestellungen
    ///     einschließlich _Atom-Links_, oder aber Statuscode `204`.
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @RolleAdmin
    @Operation(summary = "Suche mit Suchkriterien", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "CollectionModel mid den Bestellungen")
    @ApiResponse(responseCode = "404", description = "Keine Bestellungen gefunden")
    @SuppressWarnings("ReturnCount")
    ResponseEntity<List<Autohaus>> get(
        @RequestParam final Map<String, String> queryParams,
        final HttpServletRequest request
    ) {
        LOGGER.debug("get: queryParams={}", queryParams);
        if (queryParams.size() > 1) {
            return notFound().build();
        }

        final List<Autohaus> autohaeuser;
        if (queryParams.isEmpty()) {
            autohaeuser = autohausReadService.findAll();
        } else {
            final var autoIdStr = queryParams.get("autoId");
            if (autoIdStr == null) {
                return notFound().build();
            }
            final var kundeId = UUID.fromString(autoIdStr);
            autohaeuser = autohausReadService.findByAutoId(kundeId);
        }

        return ok(autohaeuser);
    }

    /// Suche anhand der Autohaus-ID.
    ///
    /// @param id ID der zu suchenden Bestellung
    /// @param version Versionsnummer beim Request-Header `If-None-Match`.
    /// @param request Das Request-Objekt, um Links für _HATEOAS_ zu erstellen.
    /// @return Ein Response mit dem Statuscode `200` und der gefundenen Bestellung einschließlich _Atom-Links_,
    ///      oder aber Statuscode `204`.
    @GetMapping(path = "/{id:" + ID_PATTERN + '}', produces = APPLICATION_JSON_VALUE)
    @RolleAdmin
    @Observed(name = "get-by-id")
    @Operation(summary = "Suche mit der Bestellung-ID", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Bestellung gefunden")
    @ApiResponse(responseCode = "404", description = "Bestellung nicht gefunden")
    ResponseEntity<Autohaus> getById(
        @PathVariable final UUID id,
        @RequestHeader("If-None-Match") final Optional<String> version,
        final HttpServletRequest request
    ) {
        LOGGER.debug("getById: id={}, version={}", id, version);

        final var autohaus = autohausReadService.findById(id);
        final var currentVersion = "\"" + autohaus.getVersion() + '"';
        if (Objects.equals(version.orElse(null), currentVersion)) {
            return status(NOT_MODIFIED).build();
        }

        return ok(autohaus);
    }
}
