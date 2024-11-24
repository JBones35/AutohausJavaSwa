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

/**
 * Problem Type enum für einen spezifischeren Response-Header
 */
enum ProblemType {
    /// Constraints als Fehlerursache.
    CONSTRAINTS("constraints"),

    /// Fehler, wenn z.B. Emailadresse bereits existiert.
    UNPROCESSABLE("unprocessable"),

    /// Fehler beim Header `If-Match`.
    PRECONDITION("precondition"),

    /// Fehler bei z.B. einer Patch-Operation.
    BAD_REQUEST("badRequest");

    private final String value;

    ProblemType(final String value) {
        this.value = value;
    }

    /// Den internen Wert eines Enums ermitteln.
    ///
    /// @return Der interne Wert
    String getValue() {
        return value;
    }
}
