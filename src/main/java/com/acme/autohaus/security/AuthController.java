/*
 * Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import static com.acme.autohaus.security.AuthController.AUTH_PATH;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/// Controller für Abfragen zu Security.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@RestController
@RequestMapping(AUTH_PATH)
@Tag(name = "Authentifizierung API")
@SuppressWarnings({"ClassFanOutComplexity", "java:S1075"})
public class AuthController {
    /// Pfad für Authentifizierung.
    public static final String AUTH_PATH = "/auth";

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final KeycloakRepository keycloakRepository;
    private final KeycloakProps keycloakProps;
    private final CompromisedPasswordChecker passwordChecker;

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    ///
    /// @param keycloakRepository Repository für einen _HTTP Client_ mit _Spring_ für die REST-Schnittstelle von
    /// _Keycloak_.
    /// @param keycloakProps Property-Objekt zu _Keycloak_.
    /// @param passwordChecker Zur Überprüfung von sicheren Passwörtern.
    AuthController(final com.acme.autohaus.security.KeycloakRepository keycloakRepository,
                   final com.acme.autohaus.security.KeycloakProps keycloakProps,
                   final CompromisedPasswordChecker passwordChecker) {
        this.keycloakRepository = keycloakRepository;
        this.keycloakProps = keycloakProps;
        this.passwordChecker = passwordChecker;
    }

    /// `@PostConstruct` aus _Common Annotations (JSR 250)_, um die Properties für _Keycloak_ zu protokollieren.
    @PostConstruct
    void logInit() {
        LOGGER.info("Keycloak Props = {}", keycloakProps);
    }

    /// Bearer-Token zu Benutzername und Passwort durch einen POST-Request ermitteln.
    ///
    /// @param loginDto DTO-Objekt mit Benutzername und Passwort.
    /// @return Bearer-Token mit Statuscode `200` bei gültigen Login-Daten, sonst Statuscode `401`.
    @PostMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Token zu Benutzername und Passwort", tags = "Auth")
    @ApiResponse(responseCode = "200", description = "Token erhalten")
    @ApiResponse(responseCode = "401", description = "Fehler bei Username oder Passwort")
    com.acme.autohaus.security.TokenDTO token(@RequestBody final LoginDTO loginDto) {
        LOGGER.debug("token: loginDto={}", loginDto);
        final var tokenDTO = keycloakRepository.token(
            "username=" + loginDto.username() + "&password=" + loginDto.password() + "&grant_type=password" +
                "&client_id=" + keycloakProps.clientId() + "&client_secret=" + keycloakProps.clientSecret(),
            APPLICATION_FORM_URLENCODED_VALUE
        );
        LOGGER.debug("token: tokenDTO={}", tokenDTO);
        return tokenDTO;
    }

    /// Einen JWT gemäß _OAuth 2_ durch einen GET-Request dekodieren.
    ///
    /// @param jwt JWT.
    /// @return Map für den JSON-Datensatz zum dekodierten JWT.
    @GetMapping("/me")
    @RolleAdminOrUser
    @Operation(summary = "JWT bei OAuth 2.0 abfragen", tags = "Auth")
    @ApiResponse(responseCode = "200", description = "Eingeloggt")
    @ApiResponse(responseCode = "401", description = "Fehler bei Username oder Passwort")
    Map<String, Object> me(@AuthenticationPrincipal final Jwt jwt) {
        // https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html
        LOGGER.debug("me: isCompromised() bei Passwort 'pass1234': {}",
            passwordChecker.check("pass1234").isCompromised());

        return Map.of(
            "subject", jwt.getSubject(),
            "claims", jwt.getClaims()
        );
    }

    /// _ExceptionHandler_, falls bei einer _Query_ oder _Mutation_ ein Kunde nicht gefunden wird.
    ///
    /// @param ex Exception, falls ein Login mit ungültigen Daten versucht wird.
    @ExceptionHandler
    @ResponseStatus(UNAUTHORIZED)
    void onUnauthorized(@SuppressWarnings("unused") final HttpClientErrorException.Unauthorized ex) {
        // keine Verarbeitung fuer den leeren Response-Body
    }
}
