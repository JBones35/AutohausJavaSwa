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

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Klasse Kunde repräsentiert einen Kunden im Autohaus und erweitert die abstrakte Klasse Mensch.
 * Sie enthält spezifische Eigenschaften wie Kundennummer, Adresse und Telefonnummer.
 */
public class Kunde extends AbstractMensch {
    private String kundennummer;
    private Adresse adresse;
    private String telefonnummer;
    private String email;

    /**
     * Konstruktor zur Initialisierung eines Kunden
     * mit den Eigenschaften aus Mensch und den spezifischen Kundenattributen.
     *
     * @param name          Der Name des Kunden.
     * @param alter         Das Alter des Kunden.
     * @param geburtsdatum  Das Geburtsdatum des Kunden.
     * @param kundennummer  Die eindeutige Kundennummer des Kunden.
     * @param adresse       Die Adresse des Kunden als Adresse-Objekt.
     * @param telefonnummer Die Telefonnummer des Kunden.
     * @param email         Die E-Mail-Adresse des Kunden.
     */
    public Kunde(final String name, final int alter, final LocalDate geburtsdatum, final String kundennummer,
                 final Adresse adresse, final String telefonnummer, final String email) {
        super(name, alter, geburtsdatum);
        this.kundennummer = kundennummer;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.email = email;
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Kunde kunde && Objects.equals(kundennummer, kunde.kundennummer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(kundennummer);
    }

    public String getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(final String kundennummer) {
        this.kundennummer = kundennummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(final Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(final String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Kunde{" +
            "kundennummer='" + kundennummer + '\'' +
            ", adresse=" + adresse +
            ", telefonnummer='" + telefonnummer + '\'' +
            ", email='" + email + '\'' +
            ", name='" + getName() + '\'' +
            ", alter=" + getAlter() +
            ", geburtsdatum=" + getGeburtsdatum() +
            '}';
    }
}
