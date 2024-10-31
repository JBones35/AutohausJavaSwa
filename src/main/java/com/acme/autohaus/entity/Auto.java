package com.acme.autohaus.entity;

import java.util.Objects;

/**
 * Die Klasse Auto repräsentiert ein Fahrzeug im Autohaus und enthält typische Eigenschaften wie
 * die Fahrzeug-ID, Marke, Modell, Baujahr, Farbe, Kilometerstand und den Preis.
 */
public class Auto {
    private String fahrzeugId;
    private String marke;
    private String modell;
    private int baujahr;
    private String besitzer;
    private double preis;

    /**
     * Konstruktor zur Initialisierung eines Autos mit spezifischen Eigenschaften.
     *
     * @param fahrzeugId    Die eindeutige Fahrzeug-ID (darf nicht null sein).
     * @param marke         Die Marke des Autos (darf nicht null sein).
     * @param modell        Das Modell des Autos (darf nicht null sein).
     * @param baujahr       Das Baujahr des Autos (sollte positiv sein).
     * @param besitzer Der Besitzer des Autos (sollte nicht negativ sein).
     * @param preis         Der Preis des Autos (sollte nicht negativ sein).
     */
    public Auto(final String fahrzeugId, final String marke, final String modell,
                final int baujahr, final String besitzer, final double preis) {
        this.fahrzeugId = fahrzeugId;
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.besitzer = besitzer;
        this.preis = preis;
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Auto auto && Objects.equals(fahrzeugId, auto.getFahrzeugId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fahrzeugId);
    }

    /**
     * Gibt die Fahrzeug-ID zurück.
     *
     * @return Die Fahrzeug-ID.
     */
    public String getFahrzeugId() {
        return fahrzeugId;
    }

    /**
     * Setzt die Fahrzeug-ID.
     *
     * @param fahrzeugId Die Fahrzeug-ID (darf nicht null sein).
     */
    public void setFahrzeugId(final String fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
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
    public double getPreis() {
        return preis;
    }

    /**
     * Setzt den Preis des Autos.
     *
     * @param preis Der Preis des Autos (sollte nicht negativ sein).
     */
    public void setPreis(final double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Auto{" +
            "fahrzeugId='" + fahrzeugId + '\'' +
            ", marke='" + marke + '\'' +
            ", modell='" + modell + '\'' +
            ", baujahr=" + baujahr +
            ", besitzer=" + besitzer +
            ", preis=" + preis +
            '}';
    }
}
