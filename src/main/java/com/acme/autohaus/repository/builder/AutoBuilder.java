package com.acme.autohaus.repository.builder;

import com.acme.autohaus.entity.Auto;

/**
 * Der AutoBuilder ermöglicht die schrittweise Erstellung eines Autos.
 */
public class AutoBuilder {
    private String fahrzeugId;
    private String marke;
    private String modell;
    private int baujahr;
    private String farbe;
    private int kilometerstand;
    private double preis;

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
     * @param fahrzeugId Die Fahrzeug-ID.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setFahrzeugId(String fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
        return this;
    }

    /**
     * Setzt die Marke des Autos.
     *
     * @param marke Die Marke des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setMarke(String marke) {
        this.marke = marke;
        return this;
    }

    /**
     * Setzt das Modell des Autos.
     *
     * @param modell Das Modell des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setModell(String modell) {
        this.modell = modell;
        return this;
    }

    /**
     * Setzt das Baujahr des Autos.
     *
     * @param baujahr Das Baujahr des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setBaujahr(int baujahr) {
        this.baujahr = baujahr;
        return this;
    }

    /**
     * Setzt die Farbe des Autos.
     *
     * @param farbe Die Farbe des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setFarbe(String farbe) {
        this.farbe = farbe;
        return this;
    }

    /**
     * Setzt den Kilometerstand des Autos.
     *
     * @param kilometerstand Der Kilometerstand des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setKilometerstand(int kilometerstand) {
        this.kilometerstand = kilometerstand;
        return this;
    }

    /**
     * Setzt den Preis des Autos.
     *
     * @param preis Der Preis des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setPreis(double preis) {
        this.preis = preis;
        return this;
    }

    /**
     * Baut eine Auto-Instanz basierend auf den bisher festgelegten Werten.
     *
     * @return Eine neue Auto-Instanz.
     */
    public Auto build() {
        return new Auto(fahrzeugId, marke, modell, baujahr, farbe, kilometerstand, preis);
    }
}
