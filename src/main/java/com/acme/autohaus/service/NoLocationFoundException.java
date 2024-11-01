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

/// [RuntimeException], falls kein autohaus gefunden wurde.
public final class NoLocationFoundException extends RuntimeException {
    /// Fehlerhafte location
    private final String location;

    /// Standardkonstruktor für den [AutohausReadService], wenn alle autohausn gesucht werden, aber keine existieren.
    NoLocationFoundException() {
        super("Keine autohausn gefunden.");
        location = null;
    }

    /// Konstruktor für den [AutohausReadService] bei fehlerhafter ID.
    ///
    /// @param location Die fehlerhafte location
    NoLocationFoundException(final String location) {
        super("Kein autohaus mit der location" + location + " gefunden.");
        this.location = location;
    }

    /// id ermitteln.
    ///
    /// @return Die fehlerhafte location.
    public String getLocation() {
        return location;
    }
}
