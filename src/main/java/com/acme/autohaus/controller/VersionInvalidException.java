/*
 * This file is part of JürgenZimmermanns Modul Softwarearchitektur.
 *
 * Autohaus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Autohaus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Autohaus.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.controller;

import java.net.URI;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import static com.acme.autohaus.controller.AutohausWriteController.PROBLEM_PATH;
import static com.acme.autohaus.controller.ProblemType.PRECONDITION;

/// Exception, falls die Versionsnummer im Request-Header bei `If-Match` fehlt oder syntaktisch ungültig ist.
class VersionInvalidException extends ErrorResponseException {
    /// Konstruktor für die Verwendung in KundeWriteController
    ///
    /// @param status HTTP-Statuscode.
    /// @param message Die eigentliche Meldung.
    /// @param uri des _PUT_- oder _PATCH_-Requests.
    VersionInvalidException(final HttpStatusCode status, final String message, final URI uri) {
        this(status, message, uri, null);
    }

    /// Konstruktor für die Verwendung in KundeWriteController
    ///
    /// @param status HTTP-Statuscode.
    /// @param message Die eigentliche Meldung.
    /// @param uri des _PUT_- oder _PATCH_-Requests.
    /// @param cause Ursache als innere Exception
    VersionInvalidException(
        final HttpStatusCode status,
        final String message,
        final URI uri,
        final Throwable cause
    ) {
        super(status, asProblemDetail(status, message, uri), cause);
    }

    private static ProblemDetail asProblemDetail(final HttpStatusCode status, final String detail, final URI uri) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setType(URI.create(PROBLEM_PATH + PRECONDITION.getValue()));
        problemDetail.setInstance(uri);
        return problemDetail;
    }
}