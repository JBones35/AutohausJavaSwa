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

/**
 * Die Klasse Adresse repräsentiert eine physische Adresse mit typischen Attributen wie Straße, Hausnummer,
 * Postleitzahl und Stadt. Sie kann in verschiedenen Kontexten verwendet werden, beispielsweise zur
 * Speicherung der Adresse eines Kunden im Autohaus.
 */
public class Adresse {
    private String strasse;
    private String hausnummer;
    private String plz;
    private String stadt;

    /**
     * Konstruktor zur Initialisierung einer Adresse mit spezifischen Angaben.
     *
     * @param strasse     Die Straße der Adresse (darf nicht null sein).
     * @param hausnummer  Die Hausnummer der Adresse (darf nicht null sein).
     * @param plz         Die Postleitzahl der Adresse (darf nicht null sein).
     * @param stadt       Die Stadt der Adresse (darf nicht null sein).
     */
    public Adresse(final String strasse, final String hausnummer, final String plz, final String stadt) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.stadt = stadt;
    }

    /**
     * Gibt die Straße der Adresse zurück.
     *
     * @return Die Straße der Adresse.
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Setzt die Straße der Adresse.
     *
     * @param strasse Die Straße der Adresse (darf nicht null sein).
     */
    public void setStrasse(final String strasse) {
        this.strasse = strasse;
    }

    /**
     * Gibt die Hausnummer der Adresse zurück.
     *
     * @return Die Hausnummer der Adresse.
     */
    public String getHausnummer() {
        return hausnummer;
    }

    /**
     * Setzt die Hausnummer der Adresse.
     *
     * @param hausnummer Die Hausnummer der Adresse (darf nicht null sein).
     */
    public void setHausnummer(final String hausnummer) {
        this.hausnummer = hausnummer;
    }

    /**
     * Gibt die Postleitzahl der Adresse zurück.
     *
     * @return Die Postleitzahl der Adresse.
     */
    public String getPlz() {
        return plz;
    }

    /**
     * Setzt die Postleitzahl der Adresse.
     *
     * @param plz Die Postleitzahl der Adresse (darf nicht null sein).
     */
    public void setPlz(final String plz) {
        this.plz = plz;
    }

    /**
     * Gibt die Stadt der Adresse zurück.
     *
     * @return Die Stadt der Adresse.
     */
    public String getStadt() {
        return stadt;
    }

    /**
     * Setzt die Stadt der Adresse.
     *
     * @param stadt Die Stadt der Adresse (darf nicht null sein).
     */
    public void setStadt(final String stadt) {
        this.stadt = stadt;
    }

    @Override
    public String toString() {
        return "Adresse{" +
            "strasse='" + strasse + '\'' +
            ", hausnummer='" + hausnummer + '\'' +
            ", plz='" + plz + '\'' +
            ", stadt='" + stadt + '\'' +
            '}';
    }
}
