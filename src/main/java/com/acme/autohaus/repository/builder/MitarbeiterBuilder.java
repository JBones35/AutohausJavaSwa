/*
 * This file is part of [Projektname].
 *
 * [Projektname] is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * [Projektname] is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [Projektname].  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.repository.builder;

import com.acme.autohaus.entity.Mitarbeiter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Der MitarbeiterBuilder ermöglicht die schrittweise Erstellung eines Mitarbeiters.
 */
public final class MitarbeiterBuilder {
    private String name;

    private LocalDate geburtsdatum;

    private UUID id;

    private String position;

    private BigDecimal gehalt;

    /**
     * Privater Konstruktor, der verwendet wird, um einen neuen MitarbeiterBuilder zu erstellen.
     * Dieser Konstruktor verhindert die direkte Instanziierung der Klasse außerhalb des Builders.
     */
    private MitarbeiterBuilder() {
        // Der Konstruktor bleibt leer, um die Verwendung des Builders zu erzwingen.
    }

    /**
     * Erstellt einen neuen MitarbeiterBuilder.
     *
     * @return Das Builder-Objekt.
     */
    public static MitarbeiterBuilder getBuilder() {
        return new MitarbeiterBuilder();
    }

    /**
     * Setzt die Mitarbeiter-ID.
     *
     * @param id Die Mitarbeiter-ID des Mitarbeiters.
     * @return Der aktuelle MitarbeiterBuilder.
     */
    public MitarbeiterBuilder setId(final UUID id) {
        this.id = id;
        return this;
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
     * Baut eine Mitarbeiter-Instanz basierend auf den bisher festgelegten Werten.
     *
     * @return Eine neue Mitarbeiter-Instanz.
     */
    public Mitarbeiter build() {
        return new Mitarbeiter(name, geburtsdatum, id, position, gehalt);
    }
}
