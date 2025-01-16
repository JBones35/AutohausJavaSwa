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
import com.acme.autohaus.security.RolleAdminOrUser;
import com.acme.autohaus.service.AutohausReadService;
import com.c4_soft.springaddons.security.oidc.starter.properties.SpringAddonsOidcProperties;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausGetController.class);

    private final AutohausReadService autohausReadService;

    private final JwtService jwtService;

    private final SpringAddonsOidcProperties oidcProps;

    /**
     * Konstruktor für den AutohausGetController.
     *
     * @param autohausReadService Der Service zum Lesen von Autohaus-Daten.
     * @param jwtService Der JwtService
     * @param oidcProps Die SpringAddOnsOidc-Properties
     */
    public AutohausGetController(final AutohausReadService autohausReadService, final JwtService jwtService,
                                 final SpringAddonsOidcProperties oidcProps) {
        this.autohausReadService = autohausReadService;
        this.jwtService = jwtService;
        this.oidcProps = oidcProps;
    }

    /// `@PostConstruct` aus _Common Annotations (JSR 250)_, um die _Issuer-URI_ für _OIDC_ zu protokollieren.
    @PostConstruct
    void logOidcProperties() {
        LOGGER.info("SpringAddonsOidcProperties: Issuer-URI = {}", oidcProps.getOps().getFirst().getIss());
    }

    /**
     * Suche mit diversen Suchkriterien als Query-Parameter.
     *
     * @param suchkriterien Query-Parameter als Map.
     * @return Gefundene Autohäuser als [List].
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Suche mit Suchkriterien", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Liste mit Autohäuser")
    @ApiResponse(responseCode = "404", description = "Keine Autohäuser gefunden")
    public List<Autohaus> get(
        @RequestParam @NonNull final MultiValueMap<String, String> suchkriterien
    ) {
        LOGGER.debug("get:Suchkriterien= {}", suchkriterien);
        final List<Autohaus> autohauser = autohausReadService.find(suchkriterien);
        LOGGER.debug("get:Autohaeuser= {}", autohauser);
        return autohauser;
    }

    /// Suche anhand der Autohaus-ID als Pfad-Parameter.
    ///
    /// @param id ID des zu suchenden Kunden
    /// @param version Versionsnummer aus dem Header If-None-Match
    /// @param authentication Injiziertes Objekt für Authentication von Spring Security
    /// @return Ein Response mit dem Statuscode 200 und dem gefundenen Kunden oder Statuscode 404.
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @RolleAdminOrUser
    @Observed(name = "get-by-id")
    @Operation(summary = "Suche mit der Autohaus-ID", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Autohaus gefunden")
    @ApiResponse(responseCode = "404", description = "Autohaus nicht gefunden")
    public ResponseEntity<Autohaus> getByID(
        @PathVariable final UUID id,
        @RequestHeader("If-None-Match") final Optional<String> version,
        final Authentication authentication) {
        if (LOGGER.isTraceEnabled() && authentication instanceof JwtAuthenticationToken authenticationToken) {
            LOGGER.trace("getById: name={}", authenticationToken.getName());

            final var jwt = authenticationToken.getToken();
            LOGGER.trace("getById: tokenValue={}", jwt.getTokenValue());
            LOGGER.trace("getById: headers={}", jwt.getHeaders());
            LOGGER.trace("getById: issuedAt={}", jwt.getIssuedAt());
            LOGGER.trace("getById: expiresAt={}", jwt.getExpiresAt());
            LOGGER.trace("getById: claims={}", jwt.getClaims());
        }

        final var username = jwtService.getUsername(authentication);
        LOGGER.debug("getById: id={}, version={}", id, version);

        if (username == null) {
            LOGGER.error("Trotz Spring Security wurde getById() ohne Benutzername im JWT aufgerufen");
            return status(UNAUTHORIZED).build();
        }
        final var rollen = jwtService.getRollen(authentication);
        LOGGER.trace("getById: rollen={}", rollen);

        final var autohaus = autohausReadService.findById(id, username, rollen, false);
        LOGGER.trace("getById: {}", autohaus);

        final var versionStr = "\"" + autohaus.getVersion() + '"';
        if (versionStr.equals(version.orElse(null))) {
            return status(NOT_MODIFIED).build();
        }

        LOGGER.debug("getById: autohaus={}", autohaus);
        return ok().eTag(versionStr).body(autohaus);
    }
}
