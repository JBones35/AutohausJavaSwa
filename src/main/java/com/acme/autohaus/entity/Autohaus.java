/*
 * Diese Datei ist Teil von [Projektname].
 *
 * [Projektname] ist freie Software: Sie können es unter den Bedingungen der GNU General Public License, veröffentlicht von
 * der Free Software Foundation, entweder Version 3 der Lizenz oder (nach Ihrer Wahl) einer späteren Version,
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/// Daten eines Autohauses. In DDD ist Autohaus ein Aggregate Root.
/// ![Klassendiagramm](../../../../../asciidoc/Autohaus.svg)
///
public class Autohaus {
    private String name;
    private String standort;
    private String telefonnummer;
    private String email;
    private UUID autohausId;
    private final List<Auto> autos;
    private final List<Mitarbeiter> mitarbeiter;

    /**
     * Konstruktor zur Initialisierung eines Autohauses mit spezifischen Eigenschaften und leeren Listen.
     *
     * @param name          Der Name des Autohauses.
     * @param standort      Der Standort des Autohauses.
     * @param telefonnummer  Die Telefonnummer des Autohauses.
     * @param autohausId          Die UUID des Autohauses (kann null sein, eine neue UUID wird generiert).
     * @param email         Die E-Mail-Adresse des Autohauses.
     * @param autos         Die Liste der Autos (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     * @param mitarbeiter   Die Liste der Mitarbeiter (kann null sein, in diesem Fall wird eine leere Liste erstellt).
     */
    public Autohaus(final String name, final String standort, final String telefonnummer, final UUID autohausId,
                    final String email, final List<Auto> autos, final List<Mitarbeiter> mitarbeiter) {
        this.name = name;
        this.standort = standort;
        this.telefonnummer = telefonnummer;
        this.autohausId = autohausId != null ? autohausId : UUID.randomUUID();
        this.email = email;
        this.autos = autos != null ? autos : new ArrayList<>();
        this.mitarbeiter = mitarbeiter != null ? mitarbeiter : new ArrayList<>();
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Autohaus autohaus && Objects.equals(autohausId, autohaus.getUUId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(autohausId);
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
        return autohausId;
    }

    /**
     * Setzt die UUID des Autohauses.
     *
     * @param id Die UUID des Autohauses.
     */
    public void setUUId(final UUID id) {
        this.autohausId = id;
    }

    /**
     * Gibt die E-Mail-Adresse des Autohauses zurück.
     *
     * @return Die E-Mail-Adresse des Autohauses.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Autohauses.
     *
     * @param email Die E-Mail-Adresse des Autohauses.
     */
    public void setEmail(final String email) {
        this.email = email;
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
            ", UUID='" + autohausId + '\'' +
            ", email='" + email + '\'' +
            ", autos=" + autos +
            ", mitarbeiter=" + mitarbeiter +
            '}';
    }
}
