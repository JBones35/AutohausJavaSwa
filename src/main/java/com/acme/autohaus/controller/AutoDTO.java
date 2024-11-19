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

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * Das AutoDTO repräsentiert die Daten eines Autos,
 * die zwischen verschiedenen Schichten der Anwendung übertragen werden.
 * Es enthält Informationen wie Marke, Modell, Baujahr, Besitzer und Preis.
 *
 * @param marke   die Marke des Autos; darf nicht leer sein und muss aus 2 bis 50 gültigen Zeichen bestehen.
 * @param modell  das Modell des Autos; darf nicht leer sein und muss aus 1 bis 50 gültigen Zeichen bestehen.
 * @param baujahr das Baujahr des Autos; muss zwischen 1886 (erstes Automobil) und dem aktuellen Jahr liegen.
 * @param besitzer der Besitzer des Autos; darf nicht leer sein und muss aus 2 bis 100 gültigen Zeichen bestehen.
 * @param preis   der Preis des Autos; muss positiv und darf nicht null sein.
 */
public record AutoDTO(
    @NotBlank
    @Pattern(regexp = MARKEN_PATTERN)
    String marke,

    @NotBlank
    @Pattern(regexp = MODELL_PATTERN)
    String modell,

    int baujahr,

    @NotBlank
    @Pattern(regexp = BESITZER_PATTERN)
    String besitzer,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal preis

) {
    /**
     * Regulärer Ausdruck zur Validierung der Marke.
     * Die Marke muss aus 2 bis 50 gültigen Zeichen bestehen, inklusive Buchstaben, Ziffern und Leerzeichen.
     */
    public static final String MARKEN_PATTERN = "^[A-Za-zÄÖÜäöüß0-9 ]{2,50}$";

    /**
     * Regulärer Ausdruck zur Validierung des Modells.
     * Das Modell muss aus 1 bis 50 gültigen Zeichen bestehen, inklusive Buchstaben, Ziffern und Leerzeichen.
     */
    public static final String MODELL_PATTERN = "^[A-Za-zÄÖÜäöüß0-9 ]{1,50}$";

    /**
     * Regulärer Ausdruck zur Validierung des Besitzernamens.
     * Der Name muss aus 2 bis 100 gültigen Zeichen bestehen, inklusive Buchstaben und Leerzeichen.
     */
    public static final String BESITZER_PATTERN = "^[A-Za-zÄÖÜäöüß ]{2,100}$";
}
