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

import com.acme.autohaus.entity.Adresse;
import java.util.UUID;

/**
 * Der AdresseBuilder ermöglicht die schrittweise Erstellung einer Adresse.
 */
public final class AdresseBuilder {
    private UUID id;

    private String strasse;

    private String hausnummer;

    private String plz;

    private String stadt;

    /**
     * Privater Konstruktor, der verwendet wird, um einen neuen AdresseBuilder zu erstellen.
     * Dieser Konstruktor verhindert die direkte Instanziierung der Klasse außerhalb des Builders.
     */
    private AdresseBuilder() {
        // Der Konstruktor bleibt leer, um die Verwendung des Builders zu erzwingen.
    }

    /**
     * Erstellt einen neuen AdresseBuilder.
     *
     * @return Das Builder-Objekt.
     */
    public static AdresseBuilder getBuilder() {
        return new AdresseBuilder();
    }

    /**
     * Setzt die ID der Adresse.
     *
     * @param id Die ID.
     * @return Der aktuelle AdresseBuilder.
     */
    public AdresseBuilder setId(final UUID id) {
        this.id = id;
        return this;
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
        return new Adresse(id, strasse, hausnummer, plz, stadt);
    }
}
