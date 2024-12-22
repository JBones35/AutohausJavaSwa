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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/// ValueObject für das Neuanlegen und Ändern eines neuen Kunden.
///
/// @param plz Postleitzahl
/// @param stadt Ort
/// @param strasse Strasse
/// @param hausnummer int
public record AdresseDTO(
    @NotNull
    @Pattern(regexp = PLZ_PATTERN)
    String plz,

    @NotNull
    @NotBlank
    String stadt,

    @NotNull
    @NotBlank
    String hausnummer,

    @NotNull
    @NotBlank
    String strasse
) {
    /// Konstante für den regulären Ausdruck einer Postleitzahl als 5-stellige Zahl mit führender Null.
    public static final String PLZ_PATTERN = "^\\d{5}$";
}
