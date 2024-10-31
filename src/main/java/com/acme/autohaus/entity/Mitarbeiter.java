package com.acme.autohaus.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Klasse Mitarbeiter repräsentiert einen Mitarbeiter im Autohaus und erweitert die abstrakte Klasse Mensch.
 * Sie enthält spezifische Eigenschaften eines Mitarbeiters wie Mitarbeiter-ID, Position, Gehalt und Adresse.
 */
public class Mitarbeiter extends AbstractMensch {
    private String mitarbeiterId;
    private String position;
    private BigDecimal gehalt;
    private Adresse adresse;

    /**
     * Konstruktor zur Initialisierung eines Mitarbeiters.
     *
     * @param name          Der Name des Mitarbeiters.
     * @param alter         Das Alter des Mitarbeiters.
     * @param geburtsdatum  Das Geburtsdatum des Mitarbeiters.
     * @param mitarbeiterId Die eindeutige Mitarbeiter-ID.
     * @param position      Die Position des Mitarbeiters im Autohaus.
     * @param gehalt        Das Gehalt des Mitarbeiters.
     * @param adresse       Die Adresse des Mitarbeiters.
     */
    public Mitarbeiter(final String name, final int alter, final LocalDate geburtsdatum,
                       final String mitarbeiterId, final String position, final BigDecimal gehalt,
                       final Adresse adresse) {
        super(name, alter, geburtsdatum);
        this.mitarbeiterId = mitarbeiterId;
        this.position = position;
        this.gehalt = gehalt;
        this.adresse = adresse;
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
            Objects.equals(mitarbeiterId, mitarbeiter.mitarbeiterId);
    }

    /**
     * Berechnet den Hashcode basierend auf der Mitarbeiter-ID.
     *
     * @return Der Hashcode des Mitarbeiters.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(mitarbeiterId);
    }

    /**
     * Gibt die eindeutige Mitarbeiter-ID zurück.
     *
     * @return Die Mitarbeiter-ID.
     */
    public String getMitarbeiterId() {
        return mitarbeiterId;
    }

    /**
     * Setzt die Mitarbeiter-ID.
     *
     * @param mitarbeiterId Die neue Mitarbeiter-ID.
     */
    public void setMitarbeiterId(final String mitarbeiterId) {
        this.mitarbeiterId = mitarbeiterId;
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
     * Gibt die Adresse des Mitarbeiters zurück.
     *
     * @return Die Adresse des Mitarbeiters.
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Setzt die Adresse des Mitarbeiters.
     *
     * @param adresse Die neue Adresse des Mitarbeiters.
     */
    public void setAdresse(final Adresse adresse) {
        this.adresse = adresse;
    }

    /**
     * Gibt eine String-Darstellung des Mitarbeiters zurück, einschließlich aller relevanten Attribute.
     *
     * @return Eine String-Darstellung des Mitarbeiters.
     */
    @Override
    public String toString() {
        return "Mitarbeiter{" +
            "mitarbeiterId='" + mitarbeiterId + '\'' +
            ", position='" + position + '\'' +
            ", gehalt=" + gehalt +
            ", adresse=" + adresse +
            ", name='" + getName() + '\'' +
            ", alter=" + getAlter() +
            ", geburtsdatum=" + getGeburtsdatum() +
            '}';
    }
}
