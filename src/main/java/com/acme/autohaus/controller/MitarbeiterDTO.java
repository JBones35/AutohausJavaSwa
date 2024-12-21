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
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Das MitarbeiterDTO repräsentiert die Daten eines Mitarbeiters,
 * die zwischen verschiedenen Schichten der Anwendung übertragen werden.
 *
 * @param name         der Name des Mitarbeiters; darf nicht null sein und muss einem bestimmten Muster entsprechen.
 * @param geburtsdatum das Geburtsdatum des Mitarbeiters; muss in der Vergangenheit liegen und darf nicht null sein.
 * @param position     die Position des Mitarbeiters im Unternehmen; darf nicht null oder leer sein.
 * @param gehalt       das Gehalt des Mitarbeiters; darf nicht null sein und muss positiv sein.
 */
public record MitarbeiterDTO(
    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    String name,

    @NotNull
    @Past
    LocalDate geburtsdatum,

    @NotNull
    @NotBlank
    String position,

    @NotNull
    BigDecimal gehalt
) {
    /**
     * Regulärer Ausdruck zur Validierung des Namens eines Mitarbeiters.
     */
    public static final String NAME_PATTERN =
        "(o'|von|von der|von und zu|van)?[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";
}
