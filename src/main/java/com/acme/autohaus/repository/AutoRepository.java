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
package com.acme.autohaus.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.IF_NONE_MATCH;

/// _HTTP Interface_ für den REST-Client für Autodaten.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@HttpExchange("/api/auto")
public interface AutoRepository {
    /// Einen Autodatensatz vom Microservice _auto_ mit einem Token anfordern.
    ///
    /// @param id ID des angeforderten Auto
    /// @return Gefundenes Auto oder null
    @GetExchange("/{id}")
    Auto getById(@PathVariable String id);

    /// Einen Autondatensatz vom Microservice _auto_ mit einem Token anfordern.
    ///
    /// @param id ID des angeforderten Auton
    /// @param version Version des angeforderten Datensatzes
    /// @param authorization String für den HTTP-Header `Authorization`
    /// @return Gefundener Auto im Response-Body oder Statuscode `304` oder Statuscode `404`.
    @GetExchange("/{id}")
    @SuppressWarnings("unused")
    ResponseEntity<Auto> getById(
        @PathVariable String id,
        @RequestHeader(IF_NONE_MATCH) String version,
        @RequestHeader(AUTHORIZATION) String authorization
    );
}
