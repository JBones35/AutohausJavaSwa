package com.acme.autohaus.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Klasse Mitarbeiter repräsentiert einen Mitarbeiter im Autohaus und erweitert die abstrakte Klasse Mensch.
 * Sie enthält spezifische Eigenschaften eines Mitarbeiters wie Mitarbeiter-ID, Position, Abteilung,
 * E-Mail-Adresse und Telefonnummer.
 */
public class Mitarbeiter extends Mensch {
    private String mitarbeiterId;
    private String position;
    private String abteilung;
    private BigDecimal gehalt;
    private String email;
    private String telefonnummer;
    private Adresse adresse;

    /**
     * Konstruktor zur Initialisierung eines Mitarbeiters mit den Eigenschaften aus Mensch und den spezifischen Mitarbeiterattributen.
     *
     * @param name          Der Name des Mitarbeiters.
     * @param alter         Das Alter des Mitarbeiters.
     * @param geburtsdatum  Das Geburtsdatum des Mitarbeiters.
     * @param mitarbeiterId Die eindeutige Mitarbeiter-ID.
     * @param position      Die Position des Mitarbeiters im Autohaus.
     * @param abteilung     Die Abteilung, in der der Mitarbeiter arbeitet.
     * @param gehalt        Das Gehalt des Mitarbeiters.
     * @param email         Die E-Mail-Adresse des Mitarbeiters.
     * @param telefonnummer Die Telefonnummer des Mitarbeiters.
     * @param adresse       Die Adresse des Mitarbeiters.
     */
    public Mitarbeiter(final String name, final int alter, final LocalDate geburtsdatum,
                       final String mitarbeiterId, final String position, final String abteilung,
                       final BigDecimal gehalt, final String email, final String telefonnummer,
                       final Adresse adresse) {
        super(name, alter, geburtsdatum);
        this.mitarbeiterId = mitarbeiterId;
        this.position = position;
        this.abteilung = abteilung;
        this.gehalt = gehalt;
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Mitarbeiter mitarbeiter
            && Objects.equals(mitarbeiterId, mitarbeiter.mitarbeiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mitarbeiterId);
    }

    public String getMitarbeiterId() {
        return mitarbeiterId;
    }

    public void setMitarbeiterId(final String mitarbeiterId) {
        this.mitarbeiterId = mitarbeiterId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(final String abteilung) {
        this.abteilung = abteilung;
    }

    public BigDecimal getGehalt() {
        return gehalt;
    }

    public void setGehalt(final BigDecimal gehalt) {
        this.gehalt = gehalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(final String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(final Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
            "mitarbeiterId='" + mitarbeiterId + '\'' +
            ", position='" + position + '\'' +
            ", abteilung='" + abteilung + '\'' +
            ", gehalt=" + gehalt +
            ", email='" + email + '\'' +
            ", telefonnummer='" + telefonnummer + '\'' +
            ", adresse=" + adresse +
            ", name='" + getName() + '\'' +
            ", alter=" + getAlter() +
            ", geburtsdatum=" + getGeburtsdatum() +
            '}';
    }
}
