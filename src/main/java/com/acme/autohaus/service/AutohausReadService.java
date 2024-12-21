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
import com.acme.autohaus.repository.AutohausRepositoryDeprecated;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/// Anwendungslogik für Autohäuser
/// ![Klassendiagramm](../../../../../asciidoc/AutohausReadService.svg)
@Service
@Transactional(readOnly = true)
public class AutohausReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausReadService.class);

    private final AutohausRepositoryDeprecated autohausRepositoryDeprecated;

    private final SpecificationBuilder specificationBuilder;

    /**
     * Erstellt eine Instanz der AutohausReadService-Klasse.
     *
     * @param autohausRepositoryDeprecated das Repository für Autohaus-Operationen
     */
    public AutohausReadService(final AutohausRepositoryDeprecated autohausRepositoryDeprecated) {
        this.autohausRepositoryDeprecated = autohausRepositoryDeprecated;
    }

    /**
     * Gibt alle Autohaus-Objekte in der Datenbank zurück.
     *
     * @param suchkriterien Query-Parameter als Map.
     * @return eine Liste aller vorhandenen Autohäuser
     * @throws NotFoundException wenn keine Autohäuser in der Datenbank vorhanden sind
     */
    @SuppressWarnings({"ReturnCount", "NestedIfDepth", "checkstyle:CyclomaticComplexity"})
    public @NonNull List<Autohaus> get(@NonNull final Map<String, List<String>> suchkriterien) {
        LOGGER.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return autohausRepositoryDeprecated.getAll();
        }

        if (suchkriterien.size() == 1) {
            final var namen = suchkriterien.get("name");
            if (namen != null && namen.size() == 1) {
                return findByName(namen.getFirst(), suchkriterien);
            }

            final var stadt = suchkriterien.get("stadt");
            if (stadt != null && stadt.size() == 1) {
                return findByStadt(stadt.getFirst(), suchkriterien);
            }
        }

        final var specification = specificationBuilder
            .build(suchkriterien)
            .orElseThrow(() -> new NotFoundException(suchkriterien));
        final var autohaus = AutohausRepositoryDeprecated.getAll(specification);
        if (autohaus.isEmpty()) {
            throw new NotFoundException(suchkriterien);
        }
        LOGGER.debug("find: {}", autohaus);
        return autohaus;
    }

    private List<Autohaus> findByName(final String name, final Map<String, List<String>> suchkriterien) {
        LOGGER.trace("findByName: {}", name);
        final var kunden = autohausRepositoryDeprecated.getByName(name);
        if (kunden.isEmpty()) {
            throw new NotFoundException(suchkriterien);
        }
        LOGGER.debug("findByName: {}", kunden);
        return kunden;
    }

    private Collection<Autohaus> findByStadt(final String stadt, final Map<String, List<String>> suchkriterien) {
        LOGGER.trace("findByStadt: {}", stadt);
        final var autohaus = autohausRepositoryDeprecated
            .getByStadt(stadt)
            .orElseThrow(() -> new NotFoundException(suchkriterien));
        final var autohaeuser = List.of(autohaus);
        LOGGER.debug("findByStadt: {}", autohaeuser);
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

        final Autohaus autohaus = autohausRepositoryDeprecated.getById(id)
            .orElseThrow(() -> new NotFoundException("Kein Autohaus für die angegebene ID gefunden."));

        LOGGER.debug("Suche nach Autohaus mit id beendet");
        return autohaus;
    }
}
