package com.acme.autohaus.controller;

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

import com.acme.autohaus.entity.Adresse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Das MitarbeiterDTO repräsentiert die Daten eines Mitarbeiters,
 * die zwischen verschiedenen Schichten der Anwendung übertragen werden.
 */
public record MitarbeiterDTO(
    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    String name,

    @NotNull
    @Past
    LocalDate geburtsdatum,

    @NotNull
    String position,

    @NotNull
    BigDecimal gehalt,

    Adresse adresse
) {
    public static final String NAME_PATTERN = "^[A-Za-zÄäÖöÜüß\\s'-]{2,50}$";
}
