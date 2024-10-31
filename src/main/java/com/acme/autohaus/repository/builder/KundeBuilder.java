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
    public KundeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setzt das Alter des Kunden.
     *
     * @param alter Das Alter des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setAlter(int alter) {
        this.alter = alter;
        return this;
    }

    /**
     * Setzt das Geburtsdatum des Kunden.
     *
     * @param geburtsdatum Das Geburtsdatum des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }

    /**
     * Setzt die Kundennummer.
     *
     * @param kundennummer Die Kundennummer des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setKundennummer(String kundennummer) {
        this.kundennummer = kundennummer;
        return this;
    }

    /**
     * Setzt die Adresse des Kunden.
     *
     * @param adresse Die Adresse des Kunden als Adresse-Objekt.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setAdresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    /**
     * Setzt die Telefonnummer des Kunden.
     *
     * @param telefonnummer Die Telefonnummer des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
        return this;
    }

    /**
     * Setzt die E-Mail-Adresse des Kunden.
     *
     * @param email Die E-Mail-Adresse des Kunden.
     * @return Der aktuelle KundeBuilder.
     */
    public KundeBuilder setEmail(String email) {
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
