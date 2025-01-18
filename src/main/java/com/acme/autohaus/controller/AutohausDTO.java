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

import com.acme.autohaus.entity.Adresse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) für die Darstellung eines Autohauses.
 * Enthält grundlegende Informationen wie Name, Standort, Telefonnummer, E-Mail,
 * sowie Listen von Autos und Mitarbeitern.
 *
 * @param name          der Name des Autohauses; darf nicht leer sein und muss einem bestimmten Muster entsprechen.
 * @param telefonnummer die Telefonnummer des Autohauses; muss einem bestimmten Muster entsprechen.
 * @param email         die E-Mail-Adresse des Autohauses; muss ein gültiges E-Mail-Format haben.
 * @param adresse       Adresse des Autohauses
 * @param username      Benutzername
 * @param password      Password
 */
public record AutohausDTO(
    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    @Size(max = NAME_MAX_LENGTH)
    String name,

    @NotNull
    @Pattern(regexp = TELEFONNUMER_PATTERN)
    String telefonnummer,

    @NotNull
    @Email
    @Size(max = EMAIL_MAX_LENGTH)
    String email,

    @Valid
    @NotNull(groups = OnCreate.class)
    Adresse adresse,

    String username,

    String password
) {
    /**
     * Regulärer Ausdruck zur Validierung des Namens eines Autohauses.
     * Unterstützt Namen mit optionalen Suffixen wie "GmbH", "AG", "Inc." oder "Ltd.".
     */
    public static final String NAME_PATTERN =
        "^[A-Za-z0-9ÄäÖöÜüß&.,'\" ()\\-]{2,100}$";

    /**
     * Regulärer Ausdruck zur Validierung von Telefonnummern.
     * Unterstützt Ziffern, Leerzeichen, Klammern und das "+"-Präfix.
     */
    public static final String TELEFONNUMER_PATTERN =  "^(\\+49|0)[1-9]\\d{1,14}$";

    private static final int NAME_MAX_LENGTH = 40;

    private static final int EMAIL_MAX_LENGTH = 40;

    /**
     * Validierungsgruppe für die Erstellung eines neuen Autohauses.
     */
    public interface OnCreate { }
}
