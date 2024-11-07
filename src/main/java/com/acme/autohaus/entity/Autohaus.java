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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Die Klasse Autohaus repräsentiert ein Autohaus und enthält typische Eigenschaften sowie Listen
 * von Autos, Mitarbeitern und Kunden.
 */
public class Autohaus {
    private String name;
    private String standort;
    private String telefonnummer;
    private UUID uuid;
    private final List<Auto> autos;
    private final List<Mitarbeiter> mitarbeiter;

    /**
     * Konstruktor zur Initialisierung eines Autohauses mit spezifischen Eigenschaften und leeren Listen.
     *
     * @param name          Der Name des Autohauses.
     * @param standort      Der Standort des Autohauses.
     * @param telefonnummer  Die Telefonnummer des Autohauses.
     * @param uuid          Die UUID des Autohauses (kann null sein, eine neue UUID wird generiert).
     * @param autos         Die Liste der Autos (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     * @param mitarbeiter   Die Liste der Mitarbeiter (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     */
    public Autohaus(final String name, final String standort, final String telefonnummer, final UUID uuid,
                    final List<Auto> autos, final List<Mitarbeiter> mitarbeiter) {
        this.name = name;
        this.standort = standort;
        this.telefonnummer = telefonnummer;
        this.uuid = uuid != null ? uuid : UUID.randomUUID();
        this.autos = autos != null ? autos : new ArrayList<>();
        this.mitarbeiter = mitarbeiter != null ? mitarbeiter : new ArrayList<>();
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Autohaus autohaus && Objects.equals(uuid, autohaus.getUUId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    /**
     * Gibt den Namen des Autohauses zurück.
     *
     * @return Der Name des Autohauses.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Autohauses.
     *
     * @param name Der Name des Autohauses.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gibt den Standort des Autohauses zurück.
     *
     * @return Der Standort des Autohauses.
     */
    public String getStandort() {
        return standort;
    }

    /**
     * Setzt den Standort des Autohauses.
     *
     * @param standort Der Standort des Autohauses.
     */
    public void setStandort(final String standort) {
        this.standort = standort;
    }

    /**
     * Gibt die Telefonnummer des Autohauses zurück.
     *
     * @return Die Telefonnummer des Autohauses.
     */
    public String getTelefonnummer() {
        return telefonnummer;
    }

    /**
     * Setzt die Telefonnummer des Autohauses.
     *
     * @param telefonnummer Die Telefonnummer des Autohauses.
     */
    public void setTelefonnummer(final String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    /**
     * Gibt die UUID des Autohauses zurück.
     *
     * @return Die UUID des Autohauses.
     */
    public UUID getUUId() {
        return uuid;
    }

    /**
     * Setzt die UUID des Autohauses.
     *
     * @param id Die UUID des Autohauses.
     */
    public void setUUId(final UUID id) {
        this.uuid = id;
    }

    /**
     * Gibt die Liste der Autos im Autohaus zurück.
     *
     * @return Die Liste der Autos. Die Liste ist nicht veränderbar durch den Aufruf dieser Methode.
     */
    public List<Auto> getAutos() {
        return autos;
    }

    /**
     * Gibt die Liste der Mitarbeiter im Autohaus zurück.
     *
     * @return Die Liste der Mitarbeiter. Die Liste ist nicht veränderbar durch den Aufruf dieser Methode.
     */
    public List<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    @Override
    public String toString() {
        return "Autohaus{" +
            "name='" + name + '\'' +
            ", standort='" + standort + '\'' +
            ", telefonnummer='" + telefonnummer + '\'' +
            ", UUID='" + uuid + '\'' +
            ", autos=" + autos +
            ", mitarbeiter=" + mitarbeiter +
            '}';
    }
}
