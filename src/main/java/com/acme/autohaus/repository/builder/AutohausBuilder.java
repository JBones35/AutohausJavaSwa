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

import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.entity.Kunde;
import com.acme.autohaus.entity.Mitarbeiter;
import java.util.List;
import java.util.UUID;

public class AutohausBuilder {
    private String name;
    private String standort;
    private String telefonnummer;
    private UUID uuid;
    private List<Auto> autos;
    private List<Mitarbeiter> mitarbeiter;
    private List<Kunde> kunden;

    public static AutohausBuilder getBuilder() {
        return new AutohausBuilder();
    }

    public AutohausBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public AutohausBuilder setStandort(final String standort) {
        this.standort = standort;
        return this;
    }

    public AutohausBuilder setTelefonnummer(final String telefonnummer) {
        this.telefonnummer = telefonnummer;
        return this;
    }

    public AutohausBuilder setUUID(final UUID id) {
        this.uuid = id;
        return this;
    }

    public AutohausBuilder setAutos(final List<Auto> autos) {
        this.autos = autos;
        return this;
    }

    public AutohausBuilder setMitarbeiter(final List<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
        return this;
    }

    public AutohausBuilder setKunden(final List<Kunde> kunden) {
        this.kunden = kunden;
        return this;
    }

    public Autohaus build() {
        return new Autohaus(name, standort, telefonnummer, uuid, autos, mitarbeiter, kunden);
    }
}
