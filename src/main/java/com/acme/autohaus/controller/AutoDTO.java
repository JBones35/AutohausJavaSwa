package com.acme.autohaus.controller;

import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

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
public record AutoDTO(
    @Pattern(regexp = MARKEN_PATTERN)
    String marke,

    @Pattern(regexp = MODELL_PATTERN)
    String modell,

    int baujahr,

    @Pattern(regexp = BESITZER_PATTERN)
    String besitzer,

    BigDecimal preis
) {
    public static final String MARKEN_PATTERN = "^[A-Za-zÄÖÜäöüß0-9 ]{2,50}$";

    public static final String MODELL_PATTERN = "^[A-Za-zÄÖÜäöüß0-9 ]{1,50}$";

    public static final String BESITZER_PATTERN = "^[A-Za-zÄÖÜäöüß ]{2,100}$";
}
