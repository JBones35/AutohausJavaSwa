package com.acme.autohaus.controller;

import com.acme.autohaus.service.AutohausWriteService;
import com.acme.autohaus.service.EmailExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.net.URI;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.acme.autohaus.controller.AutohausDTO.OnCreate;
import static com.acme.autohaus.controller.AutohausGetController.API_PATH;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

/**
 * Controller für Schreiboperationen im Autohaus.
 * Verarbeitet HTTP-POST- und PUT-Anfragen für das Erstellen und Aktualisieren von Autohaus-Daten.
 * Behandelt außerdem gängige Exceptions und gibt ProblemDetail-Objekte gemäß RFC 9457 zurück.
 */
@Controller
@Validated
@RequestMapping(API_PATH)
@SuppressWarnings({"ClassFanOutComplexity", "java:S1075"})
public class AutohausWriteController {
    /**
     * Problem path, falls der ExceptionHandler aufgerufen wird
     */
    public static final String PROBLEM_PATH = "/problem";

    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausWriteController.class);

    private final AutohausWriteService autohausWriteService;

    private final AutohausMapper autohausMapper;

    /**
     * Konstruktor für {@link AutohausWriteController}.
     *
     * @param autohausWriteService Service für Schreiboperationen.
     * @param autohausMapper       Mapper für Autohaus-DTOs.
     */
    AutohausWriteController(final AutohausWriteService autohausWriteService,
                            final AutohausMapper autohausMapper) {
        this.autohausWriteService = autohausWriteService;
        this.autohausMapper = autohausMapper;
    }

    /**
     * POST-Handler zum Erstellen eines neuen Autohauses.
     *
     * @param autohausDTO DTO-Objekt des Autohauses.
     * @param request     HTTP-Request-Objekt.
     * @return Response mit Statuscode `201` einschließlich Location-Header oder Statuscode `422`, falls Constraints
     * verletzt sind oder die E-Mail-Adresse bereits existiert oder Statuscode `400`, falls syntaktische Fehler im
     * Request-Body vorliegen.
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Ein neues Autohaus anlegen", tags = "Neuanlegen")
    @ApiResponse(responseCode = "201", description = "Autohaus neu angelegt")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "422", description = "Ungültige Werte oder Email vorhanden")
    ResponseEntity<Void> post(
        @RequestBody @Validated({Default.class, OnCreate.class})final AutohausDTO autohausDTO,
        final HttpServletRequest request
    ) {
        LOGGER.debug("post: {}", autohausDTO);
        final var autohausInput = autohausMapper.toAutohaus(autohausDTO);
        final var autohaus = autohausWriteService.create(autohausInput);
        final var location = URI.create(API_PATH + '/' + autohaus.getUUId());
        return created(location).build();
    }

    /**
     * PUT-Handler zum Aktualisieren eines bestehenden Autohauses.
     *
     * @param id          UUID des zu aktualisierenden Autohauses.
     * @param autohausDTO DTO-Objekt des Autohauses.
     */
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Ein Autohaus mit neuen Werten aktualisieren", tags = "Aktualisieren")
    @ApiResponse(responseCode = "204", description = "Aktualisiert")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "404", description = "Autohaus nicht vorhanden")
    @ApiResponse(responseCode = "422", description = "Ungültige Werte oder Email vorhanden")
    void put(
        @PathVariable final UUID id,
        @RequestBody @Valid final AutohausDTO autohausDTO
    ) {
        LOGGER.debug("put: id={}, {}", id, autohausDTO);
        final var autohausInput = autohausMapper.toAutohaus(autohausDTO);
        autohausWriteService.update(autohausInput, id);
    }

    /**
     * ExceptionHandler für {@link MethodArgumentNotValidException}.
     * Wird bei Validierungsfehlern im Request-Body ausgelöst.
     *
     * @param ex      Die ausgelöste Exception.
     * @param request HTTP-Request-Objekt.
     * @return ProblemDetail-Objekt mit Details zur Validierungsverletzung.
     */
    @ExceptionHandler
    ProblemDetail onConstraintViolations(
        final MethodArgumentNotValidException ex,
        final HttpServletRequest request
    ) {
        LOGGER.debug("onConstraintViolations: {}", ex.getMessage());
        final var detailMessages = ex.getDetailMessageArguments();
        final var detail = detailMessages == null
            ? "Constraint Violations"
            : ((String) detailMessages[1]).replace(", and ", ", ");
        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, detail);
        problemDetail.setType(URI.create(PROBLEM_PATH + ProblemType.CONSTRAINTS.getValue()));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetail;
    }

    /**
     * ExceptionHandler für {@link EmailExistsException}.
     * Wird ausgelöst, wenn die Email-Adresse bereits existiert.
     *
     * @param ex      Die ausgelöste Exception.
     * @param request HTTP-Request-Objekt.
     * @return ProblemDetail-Objekt mit Details zur Email-Fehlermeldung.
     */
    @ExceptionHandler
    ProblemDetail onEmailExists(final EmailExistsException ex, final HttpServletRequest request) {
        LOGGER.debug("onEmailExists: {}", ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setType(URI.create(PROBLEM_PATH + ProblemType.CONSTRAINTS.getValue()));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetail;
    }

    /**
     * ExceptionHandler für {@link HttpMessageNotReadableException}.
     * Wird ausgelöst, wenn der Request-Body syntaktisch ungültig ist.
     *
     * @param ex      Die ausgelöste Exception.
     * @param request HTTP-Request-Objekt.
     * @return ProblemDetail-Objekt mit Details zur ungültigen Nachricht.
     */
    @ExceptionHandler
    ProblemDetail onMessageNotReadable(
        final HttpMessageNotReadableException ex,
        final HttpServletRequest request
    ) {
        LOGGER.debug("onMessageNotReadable: {}", ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create(PROBLEM_PATH + ProblemType.BAD_REQUEST.getValue()));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetail;
    }
}
