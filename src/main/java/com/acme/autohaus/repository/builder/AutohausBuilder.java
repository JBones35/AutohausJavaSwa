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
import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Autohaus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Der AutohausBuilder ermöglicht den schrittweisen Aufbau eines Autohaus-Objekts.
 * Er folgt dem Builder-Muster, um die Erstellung eines Autohaus-Objekts mit mehreren optionalen
 * Parametern zu vereinfachen und eine klare Trennung von Konstruktion und Verwendung zu gewährleisten.
 */
public class AutohausBuilder {
    private String name;

    private Adresse adresse;

    private String telefonnummer;

    private String email;

    private int version;

    private UUID id;

    private List<Auto> autos;

    private String username;

    private LocalDateTime erzeugt;

    private LocalDateTime aktualisiert;

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
     * Setzt die UUID des Autohauses.
     *
     * @param id Die eindeutige UUID des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Setzt die Version des Autohauses.
     *
     * @param version Die E-Mail-Adresse des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setVersion(final int version) {
        this.version = version;
        return this;
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
     * Setzt die Adresse des Autohauses.
     *
     * @param adresse Der Standort des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setAdresse(final Adresse adresse) {
        this.adresse = adresse;
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
     * Setzt den Username des Autohauses.
     *
     * @param username Username des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setUsername(final String username) {
        this.username = username;
        return this;
    }

    /**
     * Setzt das Erzeugungsdatum des Autohauses.
     *
     * @param erzeugt Das Erzeugungsdatum des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setErzeugt(final LocalDateTime erzeugt) {
        this.erzeugt = erzeugt;
        return this;
    }

    /**
     * Setzt das Erzeugungsdatum des Autohauses.
     *
     * @param aktualisiert Das Aktualisierungsdatum des Autohauses.
     * @return Der aktuelle AutohausBuilder.
     */
    public AutohausBuilder setAktualisiert(final LocalDateTime aktualisiert) {
        this.aktualisiert = aktualisiert;
        return this;
    }

    /**
     * Baut und gibt ein neues Autohaus-Objekt mit den aktuell gesetzten Werten zurück.
     *
     * @return Das neu erstellte Autohaus-Objekt.
     */
    public Autohaus build() {
        return new Autohaus(id, version, name, telefonnummer, email, adresse,
            autos, username, erzeugt, aktualisiert);
    }
}
