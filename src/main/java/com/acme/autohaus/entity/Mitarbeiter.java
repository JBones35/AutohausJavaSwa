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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Die Klasse Mitarbeiter repräsentiert einen Mitarbeiter im Autohaus und erweitert die abstrakte Klasse Mensch.
 * Sie enthält spezifische Eigenschaften eines Mitarbeiters wie Mitarbeiter-ID, Position, Gehalt und Adresse.
 */
@Entity
public class Mitarbeiter {
    /**
     * Die eindeutige ID des Mitarbeiters (automatisch generiert als UUID).
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Der Name des Mitarbeiters.
     */
    private String name;

    /**
     * Das Geburtsdatum des Mitarbeiters.
     */
    private LocalDate geburtsdatum;

    /**
     * Die Position des Mitarbeiters (z. B. Manager, Techniker).
     */
    private String position;

    /**
     * Das Gehalt des Mitarbeiters.
     * Verwendet BigDecimal für eine präzise Darstellung von Geldwerten.
     */
    private BigDecimal gehalt;

    /// Konstruktor
    public Mitarbeiter() {
        //StandardKonstruktor
    }

    /**
     * Konstruktor zur Initialisierung eines Mitarbeiters.
     *
     * @param name          Der Name des Mitarbeiters.
     * @param geburtsdatum  Das Geburtsdatum des Mitarbeiters.
     * @param id Die eindeutige Mitarbeiter-ID.
     * @param position      Die Position des Mitarbeiters im Autohaus.
     * @param gehalt        Das Gehalt des Mitarbeiters.
     */
    public Mitarbeiter(final String name, final LocalDate geburtsdatum,
                       final UUID id, final String position, final BigDecimal gehalt) {
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.id = id;
        this.position = position;
        this.gehalt = gehalt;
    }

    /**
     * Gibt die eindeutige Mitarbeiter-ID zurück.
     *
     * @return Die Mitarbeiter-ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setzt die Mitarbeiter-ID.
     *
     * @param id Die neue Mitarbeiter-ID.
     */
    public void setId(final UUID id) {
        this.id = id;
    }

    /**
     * Gibt den Namen des Mitarbeiters zurück.
     *
     * @return Der Name des Mitarbeiters.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Mitarbeiters.
     *
     * @param name Der Name des Mitarbeiters.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gibt das Geburtsdatum des Mitarbeiters zurück.
     *
     * @return Das Geburtsdatum des Mitarbeiters.
     */
    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Setzt das Geburtsdatum des Mitarbeiters.
     *
     * @param geburtsdatum Das Geburtsdatum des Mitarbeiters.
     */
    public void setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }



    /**
     * Gibt die Position des Mitarbeiters im Autohaus zurück.
     *
     * @return Die Position des Mitarbeiters.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Setzt die Position des Mitarbeiters im Autohaus.
     *
     * @param position Die neue Position des Mitarbeiters.
     */
    public void setPosition(final String position) {
        this.position = position;
    }

    /**
     * Gibt das Gehalt des Mitarbeiters zurück.
     *
     * @return Das Gehalt des Mitarbeiters.
     */
    public BigDecimal getGehalt() {
        return gehalt;
    }

    /**
     * Setzt das Gehalt des Mitarbeiters.
     *
     * @param gehalt Das neue Gehalt des Mitarbeiters.
     */
    public void setGehalt(final BigDecimal gehalt) {
        this.gehalt = gehalt;
    }

    /**
     * Vergleicht diesen Mitarbeiter mit einem anderen Objekt auf Gleichheit basierend auf der Mitarbeiter-ID.
     *
     * @param other Das Objekt, mit dem verglichen werden soll.
     * @return true, wenn die Mitarbeiter-IDs gleich sind, andernfalls false.
     */
    @Override
    public final boolean equals(final Object other) {
        return other instanceof Mitarbeiter mitarbeiter &&
            Objects.equals(id, mitarbeiter.id);
    }

    /**
     * Berechnet den Hashcode basierend auf der Mitarbeiter-ID.
     *
     * @return Der Hashcode des Mitarbeiters.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Gibt eine String-Darstellung des Mitarbeiters zurück, einschließlich aller relevanten Attribute.
     *
     * @return Eine String-Darstellung des Mitarbeiters.
     */
    @Override
    public String toString() {
        return "Mitarbeiter{" +
            "mitarbeiterId='" + id + '\'' +
            ", position='" + position + '\'' +
            ", gehalt=" + gehalt +
            ", name='" + name + '\'' +
            ", geburtsdatum=" + geburtsdatum +
            '}';
    }
}
