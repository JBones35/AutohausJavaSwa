package com.acme.autohaus.repository.builder;

import com.acme.autohaus.entity.Adresse;

/**
 * Der AdresseBuilder ermöglicht die schrittweise Erstellung einer Adresse.
 */
public class AdresseBuilder {
    private String strasse;
    private String hausnummer;
    private String plz;
    private String stadt;

    /** Ein Builder-Objekt für die Klasse _Adresse_ bauen.
     *
     * @return Das Builder-Objekt
     */
    public static AdresseBuilder getBuilder() {
        return new AdresseBuilder();
    }

    /**
     * Setzt die Straße der Adresse.
     *
     * @param strasse Die Straße.
     * @return Der aktuelle AdresseBuilder.
     */
    public AdresseBuilder setStrasse(final String strasse) {
        this.strasse = strasse;
        return this;
    }

    /**
     * Setzt die Hausnummer der Adresse.
     *
     * @param hausnummer Die Hausnummer.
     * @return Der aktuelle AdresseBuilder.
     */
    public AdresseBuilder setHausnummer(final String hausnummer) {
        this.hausnummer = hausnummer;
        return this;
    }

    /**
     * Setzt die Postleitzahl der Adresse.
     *
     * @param plz Die Postleitzahl.
     * @return Der aktuelle AdresseBuilder.
     */
    public AdresseBuilder setPlz(final String plz) {
        this.plz = plz;
        return this;
    }

    /**
     * Setzt die Stadt der Adresse.
     *
     * @param stadt Die Stadt.
     * @return Der aktuelle AdresseBuilder.
     */
    public AdresseBuilder setStadt(final String stadt) {
        this.stadt = stadt;
        return this;
    }

    /**
     * Baut eine Adresse-Instanz basierend auf den bisher festgelegten Werten.
     *
     * @return Eine neue Adresse-Instanz.
     */
    public Adresse build() {
        return new Adresse(strasse, hausnummer, plz, stadt);
    }
}
