package com.acme.autohaus.controller;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.service.AutohausReadService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(AutohausGetController.API_PATH)
@OpenAPIDefinition(info = @Info(title = "Autohaus API", version = "v1"))
public class AutohausGetController {

    public static final String API_PATH = "/autohaus";
    public static final String STANDORT_PATH = "/standort";
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausGetController.class);
    private final AutohausReadService autohausReadService;

    public AutohausGetController(final AutohausReadService autohausReadService) {
        this.autohausReadService = autohausReadService;
    }

    @GetMapping(path = STANDORT_PATH + "/{standort}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Suche Autohäuser nach Standort", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Autohäuser erfolgreich gefunden")
    @ApiResponse(responseCode = "404", description = "Kein Autohaus an diesem Standort gefunden")
    public List<Autohaus> getByLocation(@PathVariable final String standort) {
        LOGGER.info("Suche nach Autohäusern am Standort: {}", standort);

        final List<Autohaus> autohauser = autohausReadService.getByLocation(standort);
        LOGGER.info("Suche nach Autohäusern am Standort {} abgeschlossen", standort);
        return autohauser;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Suche alle Autohäuser", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Alle Autohäuser erfolgreich gefunden")
    @ApiResponse(responseCode = "404", description = "Keine Autohäuser in der Datenbank gefunden")
    public List<Autohaus> getAll() {
        LOGGER.info("Suche nach allen Autohäusern in der Datenbank gestartet");

        final List<Autohaus> autohauser = autohausReadService.getAll();
        LOGGER.info("Suche nach allen Autohäusern in der Datenbank abgeschlossen");
        return autohauser;
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Suche Autohaus nach id", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Autohaus erfolgreich gefunden")
    @ApiResponse(responseCode = "404", description = "Kein Autohaus mit dieser id gefunden")
    public Autohaus getByID(@PathVariable final String id) {
        LOGGER.info("Suche nach Autohaus mit id: {}", id);

        final Autohaus autohaus = autohausReadService.getByID(id);
        LOGGER.info("Suche nach Autohaus mit id {} abgeschlossen", id);
        return autohaus;
    }
}
