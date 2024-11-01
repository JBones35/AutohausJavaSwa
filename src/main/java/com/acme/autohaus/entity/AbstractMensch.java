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

package com.acme.autohaus.entity;

import java.time.LocalDate;

/**
 * Die abstrakte Klasse Mensch stellt die grundlegenden Eigenschaften eines Menschen dar,
 * die in einem Autohaus-Kontext benötigt werden, wie Name, Alter und Geburtsdatum.
 */
public abstract class AbstractMensch {
    private String name;
    private int alter;
    private LocalDate geburtsdatum;

    /**
     * Konstruktor zur Initialisierung der Eigenschaften eines Menschen.
     *
     * @param name          Der Name der Person.
     * @param alter         Das Alter der Person.
     * @param geburtsdatum  Das Geburtsdatum der Person.
     */
    public AbstractMensch(final String name, final int alter, final LocalDate geburtsdatum) {
        this.name = name;
        this.alter = alter;
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * Gibt den Namen der Person zurück.
     *
     * @return Der Name der Person.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Person.
     *
     * @param name Der Name der Person.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gibt das Alter der Person zurück.
     *
     * @return Das Alter der Person.
     */
    public int getAlter() {
        return alter;
    }

    /**
     * Setzt das Alter der Person.
     *
     * @param alter Das Alter der Person.
     */
    public void setAlter(final int alter) {
        this.alter = alter;
    }

    /**
     * Gibt das Geburtsdatum der Person zurück.
     *
     * @return Das Geburtsdatum der Person als LocalDate.
     */
    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Setzt das Geburtsdatum der Person.
     *
     * @param geburtsdatum Das Geburtsdatum der Person als LocalDate.
     */
    public void setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
