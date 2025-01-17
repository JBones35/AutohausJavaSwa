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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Die Klasse Auto repräsentiert ein Fahrzeug im Autohaus und enthält typische Eigenschaften wie
 * die Fahrzeug-ID, Marke, Modell, Baujahr, Besitzer und Preis.
 */
@Entity
public class Auto {
    /**
     * Eindeutige ID des Fahrzeugs (automatisch generiert als UUID).
     */
    @Id
    @GeneratedValue
    private UUID id;

    /// Konstruktor mit `package private`
    public Auto() {
        //StandardKonstruktor
    }

    /**
     * Konstruktor zur Initialisierung eines Autos mit spezifischen Eigenschaften.
     *
     * @param id    Die eindeutige Fahrzeug-ID (darf nicht null sein).
     */
    public Auto(final UUID id) {
        this.id = id;
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
            '}';
    }
}
