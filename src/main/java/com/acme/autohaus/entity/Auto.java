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
package com.acme.autohaus.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Die Klasse Auto repräsentiert ein Fahrzeug im Autohaus und enthält typische Eigenschaften wie
 * die Fahrzeug-ID, Marke, Modell, Baujahr, Farbe, Kilometerstand und den Preis.
 */
public class Auto {
    private UUID uuid;
    private String marke;
    private String modell;
    private int baujahr;
    private String besitzer;
    private BigDecimal preis;

    /**
     * Konstruktor zur Initialisierung eines Autos mit spezifischen Eigenschaften.
     *
     * @param uuid    Die eindeutige Fahrzeug-ID (darf nicht null sein).
     * @param marke         Die Marke des Autos (darf nicht null sein).
     * @param modell        Das Modell des Autos (darf nicht null sein).
     * @param baujahr       Das Baujahr des Autos (sollte positiv sein).
     * @param besitzer Der Besitzer des Autos (sollte nicht negativ sein).
     * @param preis         Der Preis des Autos (sollte nicht negativ sein).
     */
    public Auto(final UUID uuid, final String marke, final String modell,
                final int baujahr, final String besitzer, final BigDecimal preis) {
        this.uuid = uuid;
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.besitzer = besitzer;
        this.preis = preis;
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Auto auto && Objects.equals(uuid, auto.getUUId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    /**
     * Gibt die Fahrzeug-ID zurück.
     *
     * @return Die Fahrzeug-ID.
     */
    public UUID getUUId() {
        return uuid;
    }

    /**
     * Setzt die Fahrzeug-ID.
     *
     * @param uuid Die Fahrzeug-ID (darf nicht null sein).
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public void setUUId(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gibt die Marke des Autos zurück.
     *
     * @return Die Marke des Autos.
     */
    public String getMarke() {
        return marke;
    }

    /**
     * Setzt die Marke des Autos.
     *
     * @param marke Die Marke des Autos (darf nicht null sein).
     */
    public void setMarke(final String marke) {
        this.marke = marke;
    }

    /**
     * Gibt das Modell des Autos zurück.
     *
     * @return Das Modell des Autos.
     */
    public String getModell() {
        return modell;
    }

    /**
     * Setzt das Modell des Autos.
     *
     * @param modell Das Modell des Autos (darf nicht null sein).
     */
    public void setModell(final String modell) {
        this.modell = modell;
    }

    /**
     * Gibt das Baujahr des Autos zurück.
     *
     * @return Das Baujahr des Autos.
     */
    public int getBaujahr() {
        return baujahr;
    }

    /**
     * Setzt das Baujahr des Autos.
     *
     * @param baujahr Das Baujahr des Autos (sollte positiv sein).
     */
    public void setBaujahr(final int baujahr) {
        this.baujahr = baujahr;
    }

    /**
     * Gibt den Besitzer des Autos zurück.
     *
     * @return Der Besitzer des Autos.
     */
    public String getBesitzer() {
        return besitzer;
    }

    /**
     * Setzt den Besitzer des Autos.
     *
     * @param besitzer Der Kilometerstand des Autos (sollte nicht negativ sein).
     */
    public void setBesitzer(final String besitzer) {
        this.besitzer = besitzer;
    }

    /**
     * Gibt den Preis des Autos zurück.
     *
     * @return Der Preis des Autos (sollte nicht negativ sein).
     */
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * Setzt den Preis des Autos.
     *
     * @param preis Der Preis des Autos (sollte nicht negativ sein).
     */
    public void setPreis(final BigDecimal preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Auto{" +
            "fahrzeugId='" + uuid + '\'' +
            ", marke='" + marke + '\'' +
            ", modell='" + modell + '\'' +
            ", baujahr=" + baujahr +
            ", besitzer=" + besitzer +
            ", preis=" + preis +
            '}';
    }
}
