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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [Projektname]. If not, see <http://www.gnu.org/licenses/>.
 */

package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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
     * @param suchkriterien Query-Parameter als Map.
     * @return eine Liste aller vorhandenen Autohäuser
     * @throws NotFoundException wenn keine Autohäuser in der Datenbank vorhanden sind
     */
    @SuppressWarnings({"ReturnCount", "NestedIfDepth", "checkstyle:CyclomaticComplexity"})
    public @NonNull List<Autohaus> get(@NonNull final MultiValueMap<String, String> suchkriterien) {
        LOGGER.debug("suche: suchkriterien = {}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return autohausRepository.getAll();
        }

        if (suchkriterien.size() == 1) {
            final var name = suchkriterien.get("name");
            if (name != null && name.size() == 1) {
                final var autohaeuser = autohausRepository.getByName(name.getFirst());
                if (autohaeuser.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                LOGGER.debug("suche (name): {}", name);
                return autohaeuser;
            }

            final var standort = suchkriterien.get("standort");
            if (standort != null && standort.size() == 1) {
                final var autohaeuser = autohausRepository.getByStandort(standort.getFirst());
                if (autohaeuser.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                LOGGER.debug("suche (standort): {}", standort);
                return autohaeuser;
            }

            final var autohaeuser = autohausRepository.get(suchkriterien);
            if (autohaeuser.isEmpty()) {
                throw new NotFoundException(suchkriterien);
            }
            LOGGER.debug("suche: {}", autohaeuser);
            return autohaeuser;
        }

        final List<Autohaus> autohaeuser = autohausRepository.get(suchkriterien);
        if (autohaeuser.isEmpty()) {
            throw new NotFoundException("Keine Autohäuser in der Datenbank gefunden.");
        }

        return autohaeuser;
    }

    /**
     * Sucht ein Autohaus anhand seiner ID.
     *
     * @param id die ID des Autohauses
     * @return das gefundene Autohaus
     * @throws NotFoundException wenn kein Autohaus mit dieser ID gefunden wird
     */
    public @NonNull Autohaus getByID(final String id) {
        LOGGER.debug("Starte Suche nach Autohaus mit id: {}", id);

        final Autohaus autohaus = autohausRepository.getByID(id)
            .orElseThrow(() -> new NotFoundException("Kein Autohaus für die angegebene ID gefunden."));

        LOGGER.debug("Suche nach Autohaus mit id beendet");
        return autohaus;
    }
}
