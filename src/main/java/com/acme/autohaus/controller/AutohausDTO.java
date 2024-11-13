package com.acme.autohaus.controller;

import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Mitarbeiter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

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
public record AutohausDTO(
    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    String name,

    @NotNull
    String standort,

    @NotNull
    @Pattern(regexp = TELEFONNUMER_PATTERN)
    String telefonnummer,

    @Email
    @NotNull
    String email,

    List<Auto> autos,

    List<Mitarbeiter> mitarbeiter

) {
    public static final String NAME_PATTERN = "^[A-Za-zÄÖÜäöüß0-9]+(?:\\s[A-Za-zÄÖÜäöüß0-9]+)*(?:\\s(?:GmbH|AG|Inc\\.|Ltd\\.))?$";


    public static final String TELEFONNUMER_PATTERN = "^[0-9]+$";
}
