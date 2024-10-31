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
    private String besitzer;
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
    public AutoBuilder setFahrzeugId(final String fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
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
     * @param preis Die Farbe des Autos.
     * @return Der aktuelle AutoBuilder.
     */
    public AutoBuilder setPreis(final double preis) {
        this.preis = preis;
        return this;
    }

    /**
     * Setzt den besitzer des Autos.
     *
     * @param besitzer Der Kilometerstand des Autos.
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
        return new Auto(fahrzeugId, marke, modell, baujahr, besitzer, preis);
    }
}
