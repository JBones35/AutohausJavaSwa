package com.acme.autohaus.repository;

import java.time.LocalDate;

/*
 * This file is part of JürgenZimmermanns Modul Softwarearchitektur.
 *
 * Autohaus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Autohaus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Autohaus.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Repräsentiert ein Auto mit Kennzeichen, Modell und Zulassungsdatum.
 * Diese Klasse ist ein {@code record}, der Datenfelder für ein Auto enthält.
 *
 * @param kennzeichen Das Kennzeichen des Autos.
 * @param modell Das Modell des Autos.
 * @param zulassungsdatum Das Datum der Zulassung des Autos.
 */
public record Auto(String kennzeichen, String modell, LocalDate zulassungsdatum) {
}
