package com.acme.autohaus.controller;

import com.acme.autohaus.service.AutohausWriteService;
import com.acme.autohaus.service.EmailExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.acme.autohaus.controller.AutohausGetController.API_PATH;
import static com.acme.autohaus.controller.AutohausGetController.ID_PATTERN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

/**
 * Controller für Schreiboperationen im Autohaus.
 * Verarbeitet HTTP-POST- und PUT-Anfragen für das Erstellen und Aktualisieren von Autohaus-Daten.
 * Behandelt außerdem gängige Exceptions und gibt ProblemDetail-Objekte gemäß RFC 9457 zurück.
 */
@Controller
@RequestMapping(path = API_PATH)
public class AutohausWriteController {
    public static final String PROBLEM_PATH = "/problem";
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausWriteController.class);
    private final AutohausWriteService autohausWriteService;
    private final AutohausMapper autohausMapper;
    private final UriHelper uriHelper;

    /**
     * Konstruktor für {@link AutohausWriteController}.
     *
     * @param autohausWriteService Service für Schreiboperationen.
     * @param autohausMapper       Mapper für Autohaus-DTOs.
     * @param uriHelper            Hilfsklasse für URI-Generierung.
     */
    AutohausWriteController(final AutohausWriteService autohausWriteService, final AutohausMapper autohausMapper, final UriHelper uriHelper) {
        this.autohausWriteService = autohausWriteService;
        this.autohausMapper = autohausMapper;
        this.uriHelper = uriHelper;
    }

    /**
     * POST-Handler zum Erstellen eines neuen Autohauses.
     *
     * @param autohausDTO DTO-Objekt des Autohauses.
     * @param request     HTTP-Request-Objekt.
     * @return ResponseEntity mit dem Location-Header des erstellten Autohauses.
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> post(
        @RequestBody @Validated final AutohausDTO autohausDTO,
        final HttpServletRequest request
    ) {
        LOGGER.debug("post: {}", autohausDTO);
        final var autohausInput = autohausMapper.toAutohaus(autohausDTO);
        final var autohaus = autohausWriteService.create(autohausInput);
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var location = URI.create(baseUri + '/' + autohaus.getUUId());
        return created(location).build();
    }

    /**
     * PUT-Handler zum Aktualisieren eines bestehenden Autohauses.
     *
     * @param id          UUID des zu aktualisierenden Autohauses.
     * @param autohausDTO DTO-Objekt des Autohauses.
     */
    @PutMapping(path = "{id:" + ID_PATTERN + "}", consumes = APPLICATION_JSON_VALUE)
    void put(
        @PathVariable final UUID id,
        @RequestBody final AutohausDTO autohausDTO
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
        final var detail = ((String) detailMessages[1]).replace(", and ", ", ");
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
