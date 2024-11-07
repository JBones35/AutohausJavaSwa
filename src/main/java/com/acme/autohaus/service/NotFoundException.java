/*
 * This file is part of [autohaus].
 *
 * [autohaus] is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * [autohaus] is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [autohaus].  If not, see <http://www.gnu.org/licenses/>.
 */

package com.acme.autohaus.service;

import java.util.List;
import java.util.Map;

/// [RuntimeException], falls kein autohaus gefunden wurde.
public final class NotFoundException extends RuntimeException {
    /// Fehlerhafte ID
    private final String id;

    ///  Fehlerhafte Suchkriterien
    private final Map<String, List<String>> suchkriterien;

    /// Standardkonstruktor für den [AutohausReadService], wenn alle autohausn gesucht werden, aber keine existieren.
    NotFoundException() {
        super("Keine Autohaeuser gefunden in der Datenbank");
        id = null;
        suchkriterien = null;
    }

    /// Konstruktor für den [AutohausReadService] bei fehlerhafter ID.
    ///
    /// @param id Die fehlerhafte ID
    NotFoundException(final String id) {
        super("Kein Autohaus mit der ID " + id + " gefunden.");
        this.id = id;
        suchkriterien = null;
    }

    /// Konstruktor für den [AutohausReadService] bei fehlerhaften Suchkriterien.
    ///
    /// @param suchkriterien Die fehlerhaften Suchkriterien
    NotFoundException(final Map<String, List<String>> suchkriterien) {
        super("Keine Autohaeuser gefunden.");
        id = null;
        this.suchkriterien = suchkriterien;
    }

    /// id ermitteln.
    ///
    /// @return Die fehlerhafte id.
    public String getId() {
        return id;
    }

    public Map<String, List<String>> getSuchkriterien() {
        return suchkriterien;
    }
}
