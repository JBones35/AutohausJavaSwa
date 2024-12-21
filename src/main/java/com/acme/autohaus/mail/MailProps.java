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
package com.acme.autohaus.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/// Spring-Konfiguration für Properties `app.mail.*`.
///
/// @author [Jürgen Zimmermann]()mailto:Juergen.Zimmermann@h-ka.de)
/// @param from Emailadresse des Absenders
/// @param sales Emailadresse des Vertriebs
@ConfigurationProperties(prefix = "app.mail")
public record MailProps(
    @DefaultValue("Jonas Kirschner <kirschner.jonas@gmx.de>")
    String from,

    @DefaultValue("Jonas Kirschner2 <jonas.kirschner05@gmail.com>")
    String sales
) {
}
