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
import com.acme.autohaus.service.AutohausReadService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.NOT_MODIFIED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


/// Controller für die Verwaltung von Autohaus-Anfragen.
/// Dieser Controller bietet Endpunkte zum Abrufen von Autohäusern aus der Datenbank.
/// ![Klassendiagramm](../../../../../asciidoc/AutohausGetController.svg)
@RestController
@RequestMapping(AutohausGetController.API_PATH)
@OpenAPIDefinition(info = @Info(title = "Autohaus API", version = "v1"))
public class AutohausGetController {
    /**
     * Der Basis-Pfad für alle Endpunkte dieses Controllers.
     */
    public static final String API_PATH = "/autohaus";

    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausGetController.class);

    private final AutohausReadService autohausReadService;

    /**
     * Konstruktor für den AutohausGetController.
     *
     * @param autohausReadService Der Service zum Lesen von Autohaus-Daten.
     */
    public AutohausGetController(final AutohausReadService autohausReadService) {
        this.autohausReadService = autohausReadService;
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

    /**
     * Endpunkt zum Abrufen eines Autohauses anhand seiner ID.
     *
     * @param id Die ID des Autohauses.
     * @param version die Version des Entities als Optional
     * @return Das Autohaus-Objekt mit der angegebenen ID.
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Observed(name = "get-by-id")
    @Operation(summary = "Suche mit der Autohaus-ID", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Autohaus gefunden")
    @ApiResponse(responseCode = "404", description = "Autohaus nicht gefunden")
    public ResponseEntity<Autohaus> getByID(
        @PathVariable final UUID id,
        @RequestHeader("If-None-Match") final Optional<String> version) {
        LOGGER.debug("getById: id={}, version={}", id, version);

        final var autohaus = autohausReadService.findById(id, false);
        LOGGER.trace("getById: {}", autohaus);

        final var currentVersion = "\"" + autohaus.getVersion() + '"';
        if (Objects.equals(version.orElse(null), currentVersion)) {
            return status(NOT_MODIFIED).build();
        }

        System.out.println("autohaus=" + autohaus);
        LOGGER.debug("getById: autohaus={}", autohaus);
        return ok().eTag(currentVersion).body(autohaus);
    }
}
