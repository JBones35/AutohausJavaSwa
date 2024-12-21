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

package com.acme.autohaus.repository;

import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.entity.Mitarbeiter;
import com.acme.autohaus.repository.builder.AdresseBuilder;
import com.acme.autohaus.repository.builder.AutoBuilder;
import com.acme.autohaus.repository.builder.AutohausBuilder;
import com.acme.autohaus.repository.builder.MitarbeiterBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mock-Datenbank-Klasse für die Simulation von Autohaus-Daten.
 * Diese Klasse enthält eine statische Liste von Autohaus-Objekten,
 * die zu Testzwecken verwendet werden kann.
 */
@SuppressWarnings({"checkstyle:ParameterNumber", "UtilityClassCanBeEnum",
    "UtilityClass", "MagicNumber", "RedundantSuppression", "java:S1192"})
public final class MockDB {
    /**
     * Liste der Autohäuser
     */
    @SuppressWarnings("StaticCollection")
    static final List<Autohaus> AUTOHAEUSER;

    static {
        AUTOHAEUSER = Stream.of(
            createOptikAutomobile(),
            createPremiumCars(),
            createClassicMotors(),
            createUrbanAutos(),
            createLuxuryMotors(),
            createEcoCars()
        ).collect(Collectors.toList());
    }

    private MockDB() {
    }

    private static Autohaus createOptikAutomobile() {
        return AutohausBuilder.getBuilder()
            .setName("Optik Automobile")
            .setAdresse(AdresseBuilder.getBuilder()
                .setHausnummer("34")
                .setStadt("München")
                .setStrasse("Musterstraße")
                .setPlz("34545")
                .setId(UUID.randomUUID())
                .build())
            .setId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            .setTelefonnummer("015202744231")
            .setEmail("optik@gmx.de")
            .setMitarbeiter(createMitarbeiterList(UUID.randomUUID(), "Abdullah", "Office"))
            .setAutos(createAutoList(UUID.randomUUID(), "Audi", "1.9", 2012, "Jonas", new BigDecimal(3435)))
            .setUsername("jonas")
            .setErzeugt(LocalDateTime.now())
            .setAktualisiert(LocalDateTime.now())
            .build();
    }

    private static Autohaus createPremiumCars() {
        return AutohausBuilder.getBuilder()
            .setName("Premium Cars")
            .setAdresse(AdresseBuilder.getBuilder()
                .setHausnummer("32")
                .setStadt("Darmstadt")
                .setStrasse("Musterstraße")
                .setPlz("34953")
                .setId(UUID.randomUUID())
                .build())
            .setId(UUID.randomUUID())
            .setTelefonnummer("0711 123456")
            .setEmail("premium@gmx.de")
            .setMitarbeiter(createMitarbeiterList(UUID.randomUUID(), "Laura", "Verkauf"))
            .setAutos(createAutoList(UUID.randomUUID(), "BMW", "M3", 2005, "Jonas", new BigDecimal(435345)))
            .setUsername("jonas")
            .setErzeugt(LocalDateTime.now())
            .setAktualisiert(LocalDateTime.now())
            .build();
    }

    private static Autohaus createClassicMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Classic Motors")
            .setAdresse(AdresseBuilder.getBuilder()
                .setHausnummer("35")
                .setStadt("Laumersheim")
                .setStrasse("Musterstraße")
                .setPlz("45345")
                .build())
            .setId(UUID.randomUUID())
            .setTelefonnummer("0721 987654")
            .setEmail("classic@motors.de")
            .setMitarbeiter(createMitarbeiterList(UUID.randomUUID(), "Felix", "Werkstatt"))
            .setAutos(createAutoList(UUID.randomUUID(), "Mercedes", "C-Klasse", 2019, "Mika", new BigDecimal(54543)))
            .setUsername("jonas")
            .setErzeugt(LocalDateTime.now())
            .setAktualisiert(LocalDateTime.now())
            .build();
    }

    private static Autohaus createUrbanAutos() {
        return AutohausBuilder.getBuilder()
            .setName("Urban Autos")
            .setAdresse(AdresseBuilder.getBuilder()
                .setHausnummer("1")
                .setStadt("Karlsruhe")
                .setStrasse("Musterstraße")
                .setPlz("25353")
                .setId(UUID.randomUUID())
                .build())
            .setId(UUID.randomUUID())
            .setTelefonnummer("06221 987654")
            .setEmail("urban@gmx.de")
            .setMitarbeiter(createMitarbeiterList(
                UUID.randomUUID(), "Klara", "Kundenservice"))
            .setAutos(createAutoList(UUID.randomUUID(), "Volkswagen", "Golf", 2003, "Goat", new BigDecimal(3445)))
            .setUsername("jonas")
            .setErzeugt(LocalDateTime.now())
            .setAktualisiert(LocalDateTime.now())
            .build();
    }

    private static Autohaus createLuxuryMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Luxury Motors")
            .setAdresse(AdresseBuilder.getBuilder()
                .setHausnummer("37")
                .setStadt("Mühlacker")
                .setStrasse("Musterstraße")
                .setPlz("25636")
                .setId(UUID.randomUUID())
                .build())
            .setId(UUID.randomUUID())
            .setTelefonnummer("0761 543210")
            .setEmail("luxury@motors.de")
            .setMitarbeiter(createMitarbeiterList(UUID.randomUUID(), "Thomas", "Verkauf"))
            .setAutos(createAutoList(UUID.randomUUID(), "Porsche", "911", 2005, "Joans", new BigDecimal(34535)))
            .setUsername("jonas")
            .setErzeugt(LocalDateTime.now())
            .setAktualisiert(LocalDateTime.now())
            .build();
    }

    private static Autohaus createEcoCars() {
        return AutohausBuilder.getBuilder()
            .setName("EcoCars")
            .setAdresse(AdresseBuilder.getBuilder()
                .setHausnummer("3")
                .setStadt("Stuttgart")
                .setStrasse("Musterstraße")
                .setPlz("12345")
                .setId(UUID.randomUUID())
                .build())
            .setId(UUID.randomUUID())
            .setTelefonnummer("0721 987123")
            .setEmail("eco@cars.des")
            .setMitarbeiter(createMitarbeiterList(UUID.randomUUID(),  "Julia",
                "Marketing-Managerin"))
            .setAutos(createAutoList(UUID.randomUUID(),  "Tesla",  "Model 3",  2023, "Kenan", new BigDecimal(4335)))
            .setUsername("jonas")
            .setErzeugt(LocalDateTime.now())
            .setAktualisiert(LocalDateTime.now())
            .build();
    }

    private static List<Mitarbeiter> createMitarbeiterList(final UUID id, final String name,
                                                           final String position) {
        return Stream.of(
            MitarbeiterBuilder.getBuilder()
                .setId(id)
                .setGehalt(BigDecimal.ZERO)
                .setName(name)
                .setGeburtsdatum(LocalDate.of(2015, 12, 6))
                .setPosition(position)
                .build()).collect(Collectors.toList());
    }

    private static List<Auto> createAutoList(final UUID id, final String brand, final String model,
                                             final int year, final String besitzer, final BigDecimal preis) {
        return Stream.of(
            AutoBuilder.getBuilder()
                .setId(id)
                .setMarke(brand)
                .setModell(model)
                .setBaujahr(year)
                .setBesitzer(besitzer)
                .setPreis(preis)
                .build()).collect(Collectors.toList());
    }
}
