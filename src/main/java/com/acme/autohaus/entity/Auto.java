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
    private String farbe;
    private int kilometerstand;
    private double preis;

    /**
     * Konstruktor zur Initialisierung eines Autos mit spezifischen Eigenschaften.
     *
     * @param fahrzeugId    Die eindeutige Fahrzeug-ID (darf nicht null sein).
     * @param marke         Die Marke des Autos (darf nicht null sein).
     * @param modell        Das Modell des Autos (darf nicht null sein).
     * @param baujahr       Das Baujahr des Autos (sollte positiv sein).
     * @param farbe         Die Farbe des Autos (darf nicht null sein).
     * @param kilometerstand Der Kilometerstand des Autos (sollte nicht negativ sein).
     * @param preis         Der Preis des Autos (sollte nicht negativ sein).
     */
    public Auto(final String fahrzeugId, final String marke, final String modell,
                final int baujahr, final String farbe, final int kilometerstand, final double preis) {
        this.fahrzeugId = fahrzeugId;
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.farbe = farbe;
        this.kilometerstand = kilometerstand;
        this.preis = preis;
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Auto auto && Objects.equals(fahrzeugId, auto.fahrzeugId);
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
     * Gibt die Farbe des Autos zurück.
     *
     * @return Die Farbe des Autos.
     */
    public String getFarbe() {
        return farbe;
    }

    /**
     * Setzt die Farbe des Autos.
     *
     * @param farbe Die Farbe des Autos (darf nicht null sein).
     */
    public void setFarbe(final String farbe) {
        this.farbe = farbe;
    }

    /**
     * Gibt den Kilometerstand des Autos zurück.
     *
     * @return Der Kilometerstand des Autos.
     */
    public int getKilometerstand() {
        return kilometerstand;
    }

    /**
     * Setzt den Kilometerstand des Autos.
     *
     * @param kilometerstand Der Kilometerstand des Autos (sollte nicht negativ sein).
     */
    public void setKilometerstand(final int kilometerstand) {
        this.kilometerstand = kilometerstand;
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
            ", farbe='" + farbe + '\'' +
            ", kilometerstand=" + kilometerstand +
            ", preis=" + preis +
            '}'; // Ausgabeformat: "Fahrzeug-ID: ID, Marke: Marke, Modell: Modell, Baujahr: Jahr, Farbe: Farbe, Kilometerstand: km, Preis: Betrag"
    }
}
