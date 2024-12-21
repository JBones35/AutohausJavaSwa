/*
 * Diese Datei ist Teil von [Projektname].
 *
 * [Projektname] ist freie Software: Sie können es unter den Bedingungen der GNU General Public License,
 * veröffentlicht von
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/// Daten eines Autohauses. In DDD ist Autohaus ein Aggregate Root.
/// ![Klassendiagramm](../../../../../asciidoc/Autohaus.svg)
///
@Entity
@NamedEntityGraph(name = Autohaus.ADRESSE_GRAPH, attributeNodes = @NamedAttributeNode("adresse"))
@NamedEntityGraph(name = Autohaus.ADRESSE_AUTOS_GRAPH, attributeNodes = {
    @NamedAttributeNode("adresse"), @NamedAttributeNode("autos")
})
@SuppressWarnings({
    "ClassFanOutComplexity",
    "RequireEmptyLineBeforeBlockTagGroup",
    "DeclarationOrder",
    "JavadocDeclaration",
    "MissingSummary",
    "RedundantSuppression", "com.intellij.jpb.LombokEqualsAndHashCodeInspection"})

public class Autohaus {
    /// NamedEntityGraph für das Attribut "adresse".
    public static final String ADRESSE_GRAPH = "Autohaus.adresse";

    /// NamedEntityGraph für die Attribute "adresse" und "umsaetze".
    public static final String ADRESSE_AUTOS_GRAPH = "Kunde.adresseAutos";

    /** Eindeutige ID des Autohauses (automatisch generiert als UUID). */
    @Id
    @GeneratedValue
    private UUID id;

    /** Version für Autohaus */
    @Version
    private int version;

    /** Der Name des Autohauses. */
    private String name;

    /** Telefonnummer des Autohauses. */
    private String telefonnummer;

    /** E-Mail-Adresse des Autohauses. */
    private String email;

    /** Die Adresse des Autohauses. */
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY, orphanRemoval = true)
    private Adresse adresse;

    /** Liste der im Autohaus verfügbaren Autos. */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "autohaus_id")
    @OrderColumn(name = "idx", nullable = false)
    @JsonIgnore
    private List<Auto> autos;

    /** Benutzername des Autohauses (z. B. für ein Login-System). */
    private String username;

    /** Zeitstempel der Erstellung des Eintrags. */
    @CreationTimestamp
    private LocalDateTime erzeugt;

    /** Zeitstempel der letzten Aktualisierung des Eintrags. */
    @UpdateTimestamp
    private LocalDateTime aktualisiert;

    /**
     * Standardkonstruktor für Jakarta Persistence.
     */
    public Autohaus() {
    }

    /**
     * Konstruktor zum Initialisieren aller Attribute des Autohauses.
     *
     * @param id            die eindeutige ID des Autohauses.
     * @param version       die Version des Eintrags.
     * @param name          der Name des Autohauses.
     * @param telefonnummer die Telefonnummer des Autohauses.
     * @param email         die E-Mail-Adresse des Autohauses.
     * @param adresse       die Adresse des Autohauses.
     * @param autos         die Liste der im Autohaus verfügbaren Autos.
     * @param username      der Benutzername des Autohauses.
     * @param erzeugt       der Erstellungszeitpunkt des Eintrags.
     * @param aktualisiert  der letzte Aktualisierungszeitpunkt des Eintrags.
     */
    @SuppressWarnings("ParameterNumber")
    public Autohaus(final UUID id, final int version, final String name, final String telefonnummer, final String email,
                    final Adresse adresse, final List<Auto> autos,
                    final String username,
                    final LocalDateTime erzeugt, final LocalDateTime aktualisiert) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.adresse = adresse;
        this.autos = autos;
        this.username = username;
        this.erzeugt = erzeugt;
        this.aktualisiert = aktualisiert;
    }

    /**
     * Autohausdaten überschreiben
     *
     * @param autohaus das Autohaus-Objekt mit den neuen Attributen.
     */
    public void set(final Autohaus autohaus) {
        this.name = autohaus.name;
        this.telefonnummer = autohaus.telefonnummer;
        this.email = autohaus.email;
    }

    /**
     * Gibt die UUID des Autohauses zurück.
     *
     * @return Die UUID des Autohauses.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setzt die UUID des Autohauses.
     *
     * @param id Die UUID des Autohauses.
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public void setId(final UUID id) {
        this.id = id;
    }

    /**
     * Gibt die Version des Eintrags zurück.
     *
     * @return die Version des Eintrags.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setzt die Version des Eintrags.
     *
     * @param version die neue Version des Eintrags.
     */
    public void setVersion(final int version) {
        this.version = version;
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
     * Gibt den Standort des Autohauses zurück.
     *
     * @return Der Standort des Autohauses.
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Setzt den Standort des Autohauses.
     *
     * @param adresse Der Standort des Autohauses.
     */
    public void setAdresse(final Adresse adresse) {
        this.adresse = adresse;
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
     * Setzt die Liste der Autos im Autohaus.
     *
     * @param autos Die Liste der Autos.
     */
    public void setAutos(final List<Auto> autos) {
        this.autos = autos;
    }

    /**
     * Gibt den Benutzernamen des Eintrags zurück.
     *
     * @return der Benutzername.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setzt den Benutzernamen des Eintrags.
     *
     * @param username der neue Benutzername.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gibt den Erstellungszeitpunkt des Eintrags zurück.
     *
     * @return der Zeitstempel der Erstellung.
     */
    public LocalDateTime getErzeugt() {
        return erzeugt;
    }

    /**
     * Setzt den Erstellungszeitpunkt des Eintrags.
     *
     * @param erzeugt der neue Erstellungszeitpunkt.
     */
    public void setErzeugt(final LocalDateTime erzeugt) {
        this.erzeugt = erzeugt;
    }

    /**
     * Gibt den letzten Aktualisierungszeitpunkt des Eintrags zurück.
     *
     * @return der Zeitstempel der letzten Aktualisierung.
     */
    public LocalDateTime getAktualisiert() {
        return aktualisiert;
    }

    /**
     * Setzt den letzten Aktualisierungszeitpunkt des Eintrags.
     *
     * @param aktualisiert der neue Aktualisierungszeitpunkt.
     */
    public void setAktualisiert(final LocalDateTime aktualisiert) {
        this.aktualisiert = aktualisiert;
    }

    @Override
    public final boolean equals(final Object other) {
        return other instanceof Autohaus autohaus && Objects.equals(id, autohaus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Autohaus{" +
            "name='" + name + '\'' +
            ", telefonnummer='" + telefonnummer + '\'' +
            ", UUID='" + id + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
