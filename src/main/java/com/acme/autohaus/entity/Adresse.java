/*
 * Diese Datei ist Teil von [Projektname].
 *
 * [Projektname] ist freie Software: Sie können es unter den Bedingungen der GNU General Public License,
 *  veröffentlicht von der Free Software Foundation, entweder Version 3 der Lizenz oder) einer späteren Version,
 * weiterverbreiten und/oder modifizieren.
 *
 * [Projektname] wird in der Hoffnung verbreitet, dass es nützlich ist, jedoch ohne jegliche Gewährleistung;
 * ohne sogar die stillschweigende Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
 * Siehe die GNU General Public License für weitere Details.
 *
 * Sie sollten eine Kopie der GNU General Public License zusammen mit [Projektname] erhalten haben.
 * Falls nicht, siehe <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;
import java.util.UUID;

/**
 * Die Adresse-Klasse stellt eine physische Adresse mit typischen Attributen wie Straße, Hausnummer,
 * Postleitzahl und Stadt dar. Sie kann in verschiedenen Kontexten verwendet werden, z. B. um die Adresse eines
 * Kunden im Autohaussystem zu speichern.
 */
@Entity
public class Adresse {
    /**
     * Eindeutige ID der Adresse (automatisch generiert als UUID).
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Die Straße der Adresse (z. B. "Musterstraße").
     */
    private String strasse;

    /**
     * Die Hausnummer der Adresse (z. B. "12a").
     */
    private String hausnummer;

    /**
     * Die Postleitzahl (PLZ) der Adresse (z. B. "12345").
     */
    private String plz;

    /**
     * Die Stadt der Adresse (z. B. "Berlin").
     */
    private String stadt;

    /// Konstruktor mit `package private`
    public Adresse() {
    //Standardkonstruktor
    }

    /**
     * Konstruktor für eine Adresse mit spezifischen Details.
     * <p>
     * Alle Felder müssen nicht null sein, damit die Adresse gültig ist.
     * </p>
     * @param id         Die Id der Adresse
     * @param strasse    Die Straße der Adresse (darf nicht null sein).
     * @param hausnummer Die Hausnummer der Adresse (darf nicht null sein).
     * @param plz        Die Postleitzahl der Adresse (darf nicht null sein).
     * @param stadt      Die Stadt der Adresse (darf nicht null sein).
     */
    public Adresse(final UUID id, final String strasse, final String hausnummer,
                   final String plz, final String stadt) {
        this.id = id;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.stadt = stadt;
    }

    /// ID ermitteln.
    ///
    /// @return Die ID.
    public UUID getId() {
        return id;
    }

    /// ID setzen.
    ///
    /// @param id Die ID.
    public void setId(final UUID id) {
        this.id = id;
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
    public final boolean equals(final Object other) {
        return other instanceof Adresse adresse && Objects.equals(id, adresse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Gibt eine Zeichenkettenrepräsentation der Adresse zurück.
     * <p>
     * Die Zeichenkette enthält alle Eigenschaften der Adresse: Straße, Hausnummer, Postleitzahl und Stadt.
     * </p>
     *
     * @return Eine Zeichenkette, die die Adresse darstellt.
     */
    @Override
    public String toString() {
        return "Adresse{" +
            "uuid=" + id +
            "strasse='" + strasse + '\'' +
            ", hausnummer='" + hausnummer + '\'' +
            ", plz='" + plz + '\'' +
            ", stadt='" + stadt + '\'' +
            '}';
    }
}
