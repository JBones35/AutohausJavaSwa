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
