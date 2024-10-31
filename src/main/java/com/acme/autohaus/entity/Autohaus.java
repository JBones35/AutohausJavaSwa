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
    private final List<Kunde> kunden;

    /**
     * Konstruktor zur Initialisierung eines Autohauses mit spezifischen Eigenschaften und leeren Listen.
     *
     * @param name          Der Name des Autohauses.
     * @param standort      Der Standort des Autohauses.
     * @param telefonnummer  Die Telefonnummer des Autohauses.
     * @param uuid          Die UUID des Autohauses (kann null sein, eine neue UUID wird generiert).
     * @param autos         Die Liste der Autos (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     * @param mitarbeiter   Die Liste der Mitarbeiter (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     * @param kunden        Die Liste der Kunden (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     */
    public Autohaus(final String name, final String standort, final String telefonnummer, final UUID uuid,
                    final List<Auto> autos, final List<Mitarbeiter> mitarbeiter, final List<Kunde> kunden) {
        this.name = name;
        this.standort = standort;
        this.telefonnummer = telefonnummer;
        this.uuid = uuid != null ? uuid : UUID.randomUUID(); // UUID generieren, falls null
        this.autos = autos != null ? autos : new ArrayList<>(); // Leere Liste, falls null
        this.mitarbeiter = mitarbeiter != null ? mitarbeiter : new ArrayList<>(); // Leere Liste, falls null
        this.kunden = kunden != null ? kunden : new ArrayList<>(); // Leere Liste, falls null
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Autohaus autohaus && Objects.equals(uuid, autohaus.getUUID());
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
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Setzt die UUID des Autohauses.
     *
     * @param uuid Die UUID des Autohauses.
     */
    public void setUUID(final UUID uuid) {
        this.uuid = uuid;
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

    /**
     * Gibt die Liste der Kunden im Autohaus zurück.
     *
     * @return Die Liste der Kunden. Die Liste ist nicht veränderbar durch den Aufruf dieser Methode.
     */
    public List<Kunde> getKunden() {
        return kunden;
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
            ", kunden=" + kunden +
            '}';
    }
}
