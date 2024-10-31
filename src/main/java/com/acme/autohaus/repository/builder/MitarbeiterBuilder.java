package com.acme.autohaus.repository.builder;

import com.acme.autohaus.entity.Adresse;
import com.acme.autohaus.entity.Mitarbeiter;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Der MitarbeiterBuilder ermöglicht die schrittweise Erstellung eines Mitarbeiters.
 */
public class MitarbeiterBuilder {
    private String name;
    private int alter;
    private LocalDate geburtsdatum;
    private String mitarbeiterId;
    private String position;
    private BigDecimal gehalt;
    private Adresse adresse;

    /**
     * Erstellt einen neuen MitarbeiterBuilder.
     *
     * @return Das Builder-Objekt.
     */
    public static MitarbeiterBuilder getBuilder() {
        return new MitarbeiterBuilder();
    }

    /**
     * Setzt den Namen des Mitarbeiters.
     *
     * @param name Der Name des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Setzt das Alter des Mitarbeiters.
     *
     * @param alter Das Alter des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setAlter(final int alter) {
        this.alter = alter;
        return this;
    }

    /**
     * Setzt das Geburtsdatum des Mitarbeiters.
     *
     * @param geburtsdatum Das Geburtsdatum des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }

    /**
     * Setzt die Mitarbeiter-ID.
     *
     * @param mitarbeiterId Die Mitarbeiter-ID des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setMitarbeiterId(final String mitarbeiterId) {
        this.mitarbeiterId = mitarbeiterId;
        return this;
    }

    /**
     * Setzt die Position des Mitarbeiters.
     *
     * @param position Die Position des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setPosition(final String position) {
        this.position = position;
        return this;
    }

    /**
     * Setzt das Gehalt des Mitarbeiters.
     *
     * @param gehalt Das Gehalt des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setGehalt(final BigDecimal gehalt) {
        this.gehalt = gehalt;
        return this;
    }

    /**
     * Setzt die Adresse des Mitarbeiters.
     *
     * @param adresse Die Adresse des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setAdresse(final Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    /**
     * Baut eine Mitarbeiter-Instanz basierend auf den bisher festgelegten Werten.
     *
     * @return Eine neue Mitarbeiter-Instanz.
     */
    public Mitarbeiter build() {
        return new Mitarbeiter(name, alter, geburtsdatum, mitarbeiterId, position, gehalt, adresse);
    }
}
