/*
 * Diese Datei ist Teil von [Projektname].
 *
 * [Projektname] ist freie Software: Sie können es unter den Bedingungen der GNU General Public License,
 * wie von der Free Software Foundation veröffentlicht, entweder Version 3 der Lizenz oder (nach Ihrer Wahl)
 * jeder späteren Version weiterverbreiten und/oder modifizieren.
 *
 * [Projektname] wird in der Hoffnung verteilt, dass es nützlich sein wird,
 * jedoch ohne jegliche Garantie; ohne sogar die implizite Garantie der
 * MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK. Siehe die
 * GNU General Public License für mehr Details.
 *
 * Sie sollten eine Kopie der GNU General Public License zusammen mit [Projektname] erhalten haben.
 * Falls nicht, siehe <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Die Klasse Auto repräsentiert ein Fahrzeug im Autohaus und enthält typische Eigenschaften wie
 * die Fahrzeug-ID, Marke, Modell, Baujahr, Besitzer und Preis.
 */
public class Auto {
    /**
     * Eindeutige ID des Fahrzeugs (automatisch generiert als UUID).
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Die Marke des Fahrzeugs (z. B. BMW, Audi).
     */
    private String marke;

    /**
     * Das spezifische Modell des Fahrzeugs (z. B. A4, 3er).
     */
    private String modell;

    /**
     * Das Baujahr des Fahrzeugs.
     * Gibt an, wann das Fahrzeug produziert wurde.
     */
    private int baujahr;

    /**
     * Der Besitzer des Fahrzeugs.
     * Entweder ein Name oder ein Identifier, der den Besitzer repräsentiert.
     */
    private String besitzer;

    /**
     * Der Preis des Fahrzeugs in einer präzisen Währungseinheit.
     * Verwendet BigDecimal für exakte Berechnungen.
     */
    private BigDecimal preis;

    /**
     * Konstruktor zur Initialisierung eines Autos mit spezifischen Eigenschaften.
     *
     * @param id    Die eindeutige Fahrzeug-ID (darf nicht null sein).
     * @param marke     Die Marke des Autos (darf nicht null sein).
     * @param modell    Das Modell des Autos (darf nicht null sein).
     * @param baujahr   Das Baujahr des Autos (sollte positiv sein).
     * @param besitzer  Der Besitzer des Autos (darf nicht null sein).
     * @param preis     Der Preis des Autos (darf nicht negativ sein).
     */
    public Auto(final UUID id, final String marke, final String modell,
                final int baujahr, final String besitzer, final BigDecimal preis) {
        this.id = id;
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.besitzer = besitzer;
        this.preis = preis;
    }

    /**
     * Gibt die Fahrzeug-ID zurück.
     *
     * @return Die eindeutige Fahrzeug-ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setzt die Fahrzeug-ID.
     *
     * @param id Die Fahrzeug-ID (darf nicht null sein).
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public void setId(final UUID id) {
        this.id = id;
    }

    /**
     * Gibt die Marke des Autos zurück.
     *
     * @return Die Marke des Autos.
     */
    public String getMarke() {
        return marke;
    }

    /**
     * Setzt die Marke des Autos.
     *
     * @param marke Die Marke des Autos (darf nicht null sein).
     */
    public void setMarke(final String marke) {
        this.marke = marke;
    }

    /**
     * Gibt das Modell des Autos zurück.
     *
     * @return Das Modell des Autos.
     */
    public String getModell() {
        return modell;
    }

    /**
     * Setzt das Modell des Autos.
     *
     * @param modell Das Modell des Autos (darf nicht null sein).
     */
    public void setModell(final String modell) {
        this.modell = modell;
    }

    /**
     * Gibt das Baujahr des Autos zurück.
     *
     * @return Das Baujahr des Autos.
     */
    public int getBaujahr() {
        return baujahr;
    }

    /**
     * Setzt das Baujahr des Autos.
     *
     * @param baujahr Das Baujahr des Autos (sollte positiv sein).
     */
    public void setBaujahr(final int baujahr) {
        this.baujahr = baujahr;
    }

    /**
     * Gibt den Besitzer des Autos zurück.
     *
     * @return Der Besitzer des Autos.
     */
    public String getBesitzer() {
        return besitzer;
    }

    /**
     * Setzt den Besitzer des Autos.
     *
     * @param besitzer Der Besitzer des Autos (darf nicht null sein).
     */
    public void setBesitzer(final String besitzer) {
        this.besitzer = besitzer;
    }

    /**
     * Gibt den Preis des Autos zurück.
     *
     * @return Der Preis des Autos (darf nicht negativ sein).
     */
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * Setzt den Preis des Autos.
     *
     * @param preis Der Preis des Autos (darf nicht negativ sein).
     */
    public void setPreis(final BigDecimal preis) {
        this.preis = preis;
    }

    /**
     * Vergleicht dieses Auto mit einem anderen Objekt auf Gleichheit.
     * <p>
     * Zwei Autos gelten als gleich, wenn sie die gleiche Fahrzeug-ID haben.
     * </p>
     *
     * @param other Das Objekt, mit dem verglichen werden soll.
     * @return {@code true}, wenn die Objekte als gleich betrachtet werden, {@code false} andernfalls.
     */
    @Override
    public final boolean equals(final Object other) {
        return other instanceof Auto auto && Objects.equals(id, auto.getId());
    }

    /**
     * Gibt den Hashcode für dieses Auto zurück.
     *
     * @return Der Hashcode für das Auto.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Gibt eine String-Darstellung des Autos zurück.
     * <p>
     * Die String-Darstellung enthält alle Eigenschaften des Autos:
     * Fahrzeug-ID, Marke, Modell, Baujahr, Besitzer und Preis.
     * </p>
     *
     * @return Eine String-Darstellung des Autos.
     */
    @Override
    public String toString() {
        return "Auto{" +
            "id='" + id + '\'' +
            ", marke='" + marke + '\'' +
            ", modell='" + modell + '\'' +
            ", baujahr=" + baujahr +
            ", besitzer=" + besitzer +
            ", preis=" + preis +
            '}';
    }
}
