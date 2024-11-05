
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

package com.acme.autohaus.repository;

import com.acme.autohaus.entity.Autohaus;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import static com.acme.autohaus.repository.MockDB.AUTOHAEUSER;

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
     * @return eine Liste aller Autohauser
     */
    public @NonNull List<Autohaus> getAll() {
        return AUTOHAEUSER;
    }

    /**
     * Sucht ein Autohaus-Objekt anhand seiner id.
     *
     * @param id die UUID des gesuchten Autohauses
     * @return das gefundene Autohaus, falls vorhanden
     */
    public @NonNull Optional<Autohaus> getByID(@NonNull final String id) {
        LOGGER.debug("Suche nach Autohaus mit id: {}", id);

        final Optional<Autohaus> autohaus = AUTOHAEUSER.stream()
            .filter(a -> Objects.equals(id, a.getUUID().toString()))
            .findFirst();

        LOGGER.debug("Ergebnis der Suche nach id {}: {}", id, autohaus);
        return autohaus;
    }
}
