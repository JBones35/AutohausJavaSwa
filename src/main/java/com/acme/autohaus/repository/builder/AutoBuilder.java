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
package com.acme.autohaus.repository.builder;

import com.acme.autohaus.entity.Auto;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Der AutoBuilder ermöglicht die schrittweise Erstellung eines Autos.
 */
public class AutoBuilder {
    private UUID id;
    private String marke;
    private String modell;
    private int baujahr;
    private String besitzer;
    private BigDecimal preis;

    /**
     * Standardkonstruktor für die Erstellung eines AutoBuilders.
     * Dieser Konstruktor wird verwendet, um das Builder-Objekt zu instanziieren,
     * bevor die Attribute des Autos gesetzt werden.
     */
    public AutoBuilder() {
        // Standardkonstruktor ohne spezielle Initialisierungen
    }

    /**
     * Erstellt einen neuen AutoBuilder.
     *
     * @return Das Builder-Objekt.
     */
    public static AutoBuilder getBuilder() {
        return new AutoBuilder();
    }

    /**
     * Setzt die Fahrzeug-ID.
     *
     * @param id Die Fahrzeug-ID.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Setzt die Marke des Autos.
     *
     * @param marke Die Marke des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setMarke(final String marke) {
        this.marke = marke;
        return this;
    }

    /**
     * Setzt das Modell des Autos.
     *
     * @param modell Das Modell des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setModell(final String modell) {
        this.modell = modell;
        return this;
    }

    /**
     * Setzt das Baujahr des Autos.
     *
     * @param baujahr Das Baujahr des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setBaujahr(final int baujahr) {
        this.baujahr = baujahr;
        return this;
    }

    /**
     * Setzt den Preis des Autos.
     *
     * @param preis Der Preis des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setPreis(final BigDecimal preis) {
        this.preis = preis;
        return this;
    }

    /**
     * Setzt den Besitzer des Autos.
     *
     * @param besitzer Der Besitzer des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setBesitzer(final String besitzer) {
        this.besitzer = besitzer;
        return this;
    }

    /**
     * Baut eine Auto-Instanz basierend auf den bisher festgelegten Werten.
     *
     * @return Eine neue Auto-Instanz.
     */
    public Auto build() {
        return new Auto(id, marke, modell, baujahr, besitzer, preis);
    }
}
