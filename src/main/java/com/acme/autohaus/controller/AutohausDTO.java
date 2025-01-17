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
import com.acme.autohaus.entity.Auto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

/**
 * DTO (Data Transfer Object) für die Darstellung eines Autohauses.
 * Enthält grundlegende Informationen wie Name, Standort, Telefonnummer, E-Mail,
 * sowie Listen von Autos und Mitarbeitern.
 *
 * @param name          der Name des Autohauses; darf nicht leer sein und muss einem bestimmten Muster entsprechen.
 * @param standort      der Standort des Autohauses; darf nicht leer oder null sein.
 * @param telefonnummer die Telefonnummer des Autohauses; muss einem bestimmten Muster entsprechen.
 * @param email         die E-Mail-Adresse des Autohauses; muss ein gültiges E-Mail-Format haben.
 * @param autos         die Liste der Autos, die in diesem Autohaus verfügbar sind; darf nicht null sein.
 * @param mitarbeiter   die Liste der Mitarbeiter des Autohauses; darf nicht null sein.
 */
public record AutohausDTO(
    @NotNull
    String name,

    @NotNull
    String telefonnummer,

    @NotNull
    @Email
    String email,

    @Valid
    @NotNull(groups = OnCreate.class)
    List<Auto> autos,

    @Valid
    @NotNull(groups = OnCreate.class)
    Adresse adresse

) {
    /**
     * Regulärer Ausdruck zur Validierung des Namens eines Autohauses.
     * Unterstützt Namen mit optionalen Suffixen wie "GmbH", "AG", "Inc." oder "Ltd.".
     */
    public static final String NAME_PATTERN =
        "^[A-ZÄÖÜa-zäöüß0-9&.,'’\\-()\\s]{2,70}$\n";

    /**
     * Regulärer Ausdruck zur Validierung von Telefonnummern.
     * Unterstützt Ziffern, Leerzeichen, Klammern und das "+"-Präfix.
     */
    public static final String TELEFONNUMER_PATTERN = "^(\\+49\\s?|0)[1-9][0-9\\s]{2,12}[0-9]$\n";

    /**
     * Validierungsgruppe für die Erstellung eines neuen Autohauses.
     */
    public interface OnCreate { }
}
