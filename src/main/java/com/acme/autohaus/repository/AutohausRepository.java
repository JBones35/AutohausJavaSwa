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

package com.acme.autohaus.repository;

import com.acme.autohaus.entity.Autohaus;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import static com.acme.autohaus.repository.MockDB.AUTOHAEUSER;
import static java.util.Collections.emptyList;

/**
 * Repository-Klasse für den Zugriff auf und die Verwaltung von Autohaus-Daten.
 */
@Repository
public class AutohausRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausRepository.class);

    /**
     * Erstellt eine Instanz der AutohausRepository-Klasse.
     */
    public AutohausRepository() {
    }

    /**
     * Gibt eine Liste aller Autohaus-Objekte zurück.
     *
     * @return eine Liste aller Autohaeuser.
     */
    public @NonNull List<Autohaus> getAll() {
        return AUTOHAEUSER;
    }

    /**
     * Gibt eine Liste von Autohaus-Objekten basierend auf den Suchkriterien zurück.
     *
     * @param suchkriterien MultiValueMap mit Suchparametern wie "name" und "standort".
     * @return gefilterte Liste von Autohaus-Objekten oder alle, wenn keine Kriterien vorhanden sind.
     */
    @SuppressWarnings("ReturnCount")
    public @NonNull List<Autohaus> get(@NonNull final MultiValueMap<String, String> suchkriterien) {
        if (suchkriterien.isEmpty()) {
            return getAll();
        }

        for (final var entry : suchkriterien.entrySet()) {
            switch (entry.getKey()) {
                case "name" -> {
                    return getByName(entry.getValue().getFirst());
                }
                case "standort" -> {
                    return getByStandort(entry.getValue().getFirst());
                }
                default -> {
                    LOGGER.debug("find: ungueltiges Suchkriterium={}", entry.getKey());
                    return emptyList();
                }
            }
        }
        return emptyList();
    }

    /**
     * Sucht Autohaeuser anhand des Namens.
     *
     * @param name der Name des gesuchten Autohauses.
     * @return Liste von Autohaeusern, deren Name den angegebenen String enthält.
     */
    public @NonNull List<Autohaus> getByName(final String name) {
        LOGGER.debug("getByName: name={}", name);
        final var autohaeuser = AUTOHAEUSER.stream()
            .filter(autohaus -> autohaus.getName().contains(name))
            .toList();
        LOGGER.debug("getByName: autohaeuser={}", autohaeuser);
        return autohaeuser;
    }

    /**
     * Sucht Autohaeuser anhand des Standorts.
     *
     * @param standort der Standort des gesuchten Autohauses.
     * @return Liste von Autohaeusern, deren Standort den angegebenen String enthält.
     */
    public @NonNull List<Autohaus> getByStandort(final String standort) {
        LOGGER.debug("getByStandort: standort={}", standort);
        final var autohaeuser = AUTOHAEUSER.stream()
            .filter(autohaus -> autohaus.getStandort().contains(standort))
            .toList();
        LOGGER.debug("getByStandort: autohaeuser={}", autohaeuser);
        return autohaeuser;
    }

    /**
     * Sucht ein Autohaus-Objekt anhand seiner ID.
     *
     * @param id die UUID des gesuchten Autohauses.
     * @return Optional mit dem gefundenen Autohaus, falls vorhanden.
     */
    public @NonNull Optional<Autohaus> getByID(@NonNull final String id) {
        LOGGER.debug("Suche nach Autohaus mit id: {}", id);
        final Optional<Autohaus> autohaus = AUTOHAEUSER.stream()
            .filter(a -> Objects.equals(id, a.getUUId().toString()))
            .findFirst();
        LOGGER.debug("Ergebnis der Suche nach id {}: {}", id, autohaus);
        return autohaus;
    }
}
