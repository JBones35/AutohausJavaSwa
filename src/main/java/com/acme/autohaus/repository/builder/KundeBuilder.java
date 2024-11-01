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
import com.acme.autohaus.entity.Kunde;
import java.time.LocalDate;

/**
 * Der KundeBuilder ermöglicht die schrittweise Erstellung eines Kunden.
 */
public class KundeBuilder {
    private String name;
    private int alter;
    private LocalDate geburtsdatum;
    private String kundennummer;
    private Adresse adresse;
    private String telefonnummer;
    private String email;

    /**
     * Erstellt einen neuen KundeBuilder.
     *
     * @return Das Builder-Objekt.
     */
    public static KundeBuilder getBuilder() {
        return new KundeBuilder();
    }

    /**
     * Setzt den Namen des Kunden.
     *
     * @param name Der Name des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Setzt das Alter des Kunden.
     *
     * @param alter Das Alter des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setAlter(final int alter) {
        this.alter = alter;
        return this;
    }

    /**
     * Setzt das Geburtsdatum des Kunden.
     *
     * @param geburtsdatum Das Geburtsdatum des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }

    /**
     * Setzt die Kundennummer.
     *
     * @param kundennummer Die Kundennummer des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setKundennummer(final String kundennummer) {
        this.kundennummer = kundennummer;
        return this;
    }

    /**
     * Setzt die Adresse des Kunden.
     *
     * @param adresse Die Adresse des Kunden als Adresse-Objekt.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setAdresse(final Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    /**
     * Setzt die Telefonnummer des Kunden.
     *
     * @param telefonnummer Die Telefonnummer des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setTelefonnummer(final String telefonnummer) {
        this.telefonnummer = telefonnummer;
        return this;
    }

    /**
     * Setzt die E-Mail-Adresse des Kunden.
     *
     * @param email Die E-Mail-Adresse des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Baut eine Kunde-Instanz basierend auf den bisher festgelegten Werten.
     *
     * @return Eine neue Kunde-Instanz.
     */
    public Kunde build() {
        return new Kunde(name, alter, geburtsdatum, kundennummer, adresse, telefonnummer, email);
    }
}
