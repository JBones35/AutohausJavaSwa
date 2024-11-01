/*
 * This file is part of [Projektname].
 *
 * [Projektname] is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * [Projektname] is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [Projektname].  If not, see <http://www.gnu.org/licenses/>.
 */

package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Service-Klasse für Leseoperationen auf Autohaus-Daten.
 */
@Service
public class AutohausReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausReadService.class);
    private final AutohausRepository autohausRepository;

    /**
     * Erstellt eine Instanz der AutohausReadService-Klasse.
     *
     * @param autohausRepository das Repository für Autohaus-Operationen
     */
    public AutohausReadService(final AutohausRepository autohausRepository) {
        this.autohausRepository = autohausRepository;
    }



    /**
     * Gibt alle Autohaus-Objekte in der Datenbank zurück.
     *
     * @return eine Liste aller vorhandenen Autohauser
     * @throws IllegalArgumentException wenn keine Autohauser in der Datenbank vorhanden sind
     */
    public @NonNull List<Autohaus> getAll() {
        LOGGER.debug("Starte Abruf aller Autohauser");

        final List<Autohaus> autohauser = autohausRepository.getAll();
        if (autohauser.isEmpty()) {
            throw new NotFoundException("Keine Autohäuser in der Datenbank gefunden.");
        }

        return autohauser;
    }

    /**
     * Sucht ein Autohaus anhand seiner id.
     *
     * @param id die id des Autohauses
     * @return das gefundene Autohaus
     * @throws IllegalArgumentException wenn kein Autohaus mit dieser id gefunden wird
     */
    public @NonNull Autohaus getByID(final String id) {
        LOGGER.debug("Starte Suche nach Autohaus mit id: {}", id);

        final Autohaus autohaus = autohausRepository.getByID(id)
            .orElseThrow(() -> new NotFoundException("Kein Autohaus für die angegebene id gefunden."));

        LOGGER.debug("Suche nach Autohaus mit id beendet");
        return autohaus;
    }  /**
     * Sucht nach Autohaus-Objekten an einem bestimmten Standort.
     *
     * @param location der Standort, an dem gesucht wird
     * @return eine Liste der gefundenen Autohaus-Objekte
     * @throws IllegalArgumentException wenn kein Autohaus an diesem Standort gefunden wird
     */
    public @NonNull List<Autohaus> getByLocation(final String location) {
        LOGGER.debug("Starte Standortsuche: {}", location);

        final List<Autohaus> autohauser = autohausRepository.getByLocation(location);
        if (autohauser.isEmpty()) {
            LOGGER.warn("Keine Autohauser am Standort {} gefunden.", location);
            throw new NoLocationFoundException(location);
        }

        LOGGER.debug("Standortsuche beendet: {}", location);
        return autohauser;
    }
}
