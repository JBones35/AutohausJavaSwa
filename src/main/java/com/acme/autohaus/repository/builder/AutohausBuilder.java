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
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.entity.Mitarbeiter;
import java.util.List;
import java.util.UUID;

/**
 * Der AutohausBuilder ermöglicht den schrittweisen Aufbau eines Autohaus-Objekts.
 * Er folgt dem Builder-Muster, um die Erstellung eines Autohaus-Objekts mit mehreren optionalen
 * Parametern zu vereinfachen und eine klare Trennung von Konstruktion und Verwendung zu gewährleisten.
 */
public class AutohausBuilder {
    private String name;
    private String standort;
    private String telefonnummer;
    private String email;

    private UUID uuid;
    private List<Auto> autos;
    private List<Mitarbeiter> mitarbeiter;

    /**
     * Standardkonstruktor für die Erstellung eines AutohausBuilders.
     * Dieser Konstruktor wird verwendet, um den Builder zu instanziieren,
     * bevor die einzelnen Attribute des Autohauses gesetzt werden.
     */
    public AutohausBuilder() {
        // Standardkonstruktor ohne spezielle Initialisierungen
    }

    /**
     * Stellt einen neuen AutohausBuilder zur Verfügung.
     *
     * @return Ein neuer AutohausBuilder.
     */
    public static AutohausBuilder getBuilder() {
        return new AutohausBuilder();
    }

    /**
     * Setzt den Namen des Autohauses.
     *
     * @param name Der Name des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Setzt den Standort des Autohauses.
     *
     * @param standort Der Standort des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setStandort(final String standort) {
        this.standort = standort;
        return this;
    }

    /**
     * Setzt die Telefonnummer des Autohauses.
     *
     * @param telefonnummer Die Telefonnummer des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setTelefonnummer(final String telefonnummer) {
        this.telefonnummer = telefonnummer;
        return this;
    }

    /**
     * Setzt die UUID des Autohauses.
     *
     * @param id Die eindeutige UUID des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setUUID(final UUID id) {
        this.uuid = id;
        return this;
    }

    /**
     * Setzt die E-Mail-Adresse des Autohauses.
     *
     * @param email Die E-Mail-Adresse des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Setzt die Liste von Autos, die im Autohaus verfügbar sind.
     *
     * @param autos Eine Liste von Autos.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setAutos(final List<Auto> autos) {
        this.autos = autos;
        return this;
    }

    /**
     * Setzt die Liste von Mitarbeitern im Autohaus.
     *
     * @param mitarbeiter Eine Liste von Mitarbeitern.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setMitarbeiter(final List<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
        return this;
    }

    /**
     * Baut und gibt ein neues Autohaus-Objekt mit den aktuell gesetzten Werten zurück.
     *
     * @return Das neu erstellte Autohaus-Objekt.
     */
    public Autohaus build() {
        return new Autohaus(name, standort, telefonnummer, uuid, email, autos, mitarbeiter);
    }
}
