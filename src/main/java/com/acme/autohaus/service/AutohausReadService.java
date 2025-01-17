/*
 * Copyright (C) 2016 - present Juergen Zimmermann, Hochschule Karlsruhe
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
package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import com.acme.autohaus.repository.AutoRecord;
import com.acme.autohaus.repository.AutoRepository;
import io.micrometer.observation.annotation.Observed;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

/// Anwendungslogik für Autohausen.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@Service
@Transactional(readOnly = true)
public class AutohausReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausReadService.class);

    private final AutohausRepository repo;
    private final AutoRepository autoRepo;

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    ///
    /// @param repo Repository-Objekt für _Spring Data_.
    /// @param autoRepo Repository-Objekt für den HTTP-Client von _Spring_.
    AutohausReadService(final AutohausRepository repo, final AutoRepository autoRepo) {
        this.repo = repo;
        this.autoRepo = autoRepo;
    }

    /// Alle Autohausen ermitteln.
    ///
    /// @return Alle Autohausen.
    public List<Autohaus> findAll() {
        final var autohaeuser = repo.findAll();
        autohaeuser.forEach(autohaus -> {
            final var autos = autohaus.getAutos();
            final List<AutoRecord> autoRecords = autos.stream()
                .map(auto -> findAutoById(auto.getId()))
                .toList();
            autohaus.setAutoRecords(autoRecords);
            });
        return autohaeuser;
    }

    /// Eine Autohaus anhand der ID suchen.
    ///
    /// @param id Die Id der gesuchten Autohaus.
    /// @return Die gefundene Autohaus.
    /// @throws NotFoundException Falls keine Autohaus gefunden wurde.
    @Observed(name = "find-by-id")
    public Autohaus findById(final UUID id) {
        LOGGER.debug("findById: id={}", id);
        final var autohaus = repo.findById(id).orElseThrow(NotFoundException::new);
        LOGGER.trace("findById: {}", autohaus);
        final var autos = autohaus.getAutos().stream().map(auto -> findAutoById(auto.getId()))
            .toList();

        autohaus.setAutoRecords(autos);
        return autohaus;
    }

    /// Autohausen zur Auto-ID suchen.
    ///
    /// @param autoId Die Id des gegebenen Auton.
    /// @return Die gefundenen Autohausen.
    /// @throws NotFoundException Falls keine Autohausen gefunden wurden.
    public List<Autohaus> findByAutoId(final UUID autoId) {
        LOGGER.debug("findByAutoId: autoId={}", autoId);

        final var autohaeuser = repo.findByAutoId(autoId);
        if (autohaeuser.isEmpty()) {
            throw new NotFoundException();
        }

        final var auto = findAutoById(autoId);
        LOGGER.trace("findByAutoId: auto: {}", auto);

        autohaeuser.forEach(autohaus -> {
            autohaus.setAutoRecords(new ArrayList<>(Collections.singletonList(auto)));
        });

        LOGGER.debug("findByAutoId: autohaeuser ={}", autohaeuser);
        return autohaeuser;
    }

    @SuppressWarnings("ReturnCount")
    private AutoRecord findAutoById(final UUID autoId) {
        LOGGER.debug("findAutoById: autoId={}", autoId);

        final AutoRecord autoRecord;
        try {
            autoRecord = autoRepo.getById(autoId.toString());
        } catch (final HttpClientErrorException.NotFound ex) {
            // Statuscode 404
            LOGGER.debug("findAutoById: HttpClientErrorException.NotFound");
            return new AutoRecord("N/A", "not.found@acme.com", null);
        } catch (final HttpStatusCodeException ex) {
            // sonstiger Statuscode 4xx oder 5xx
            // HttpStatusCodeException oder RestClientResponseException (z.B. ServiceUnavailable)
            LOGGER.warn("findAutoById", ex);
            return new AutoRecord("HttpStatusCodeException", "httpStatusCodeException@acme.com", null);
        } catch (final ResourceAccessException ex) {
            LOGGER.warn("findAutoById", ex);
            return new AutoRecord("ResourceAccessException", "resourceAccessException@acme.com", null);
        }
        LOGGER.debug("findAutoById: {}", autoRecord);
        return autoRecord;
    }
}
