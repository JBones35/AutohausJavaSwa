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

import com.acme.autohaus.entity.Adresse;
import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.builder.AutoBuilder;
import com.acme.autohaus.repository.builder.AutohausBuilder;
import java.math.BigDecimal;
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
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("015202744231")
            .setEmail("optik@gmx.de")
            .setAdresse(createAdresse())
            .setAutos(createAutoList(UUID.randomUUID(), "Audi", "1.9", 2012, "Jonas", new BigDecimal(3435)))
            .build();
    }

    private static Autohaus createPremiumCars() {
        return AutohausBuilder.getBuilder()
            .setName("Premium Cars")
            .setUUID(UUID.randomUUID())
            .setAdresse(createAdresse())
            .setTelefonnummer("0711 123456")
            .setEmail("premium@gmx.de")
            .setAutos(createAutoList(UUID.randomUUID(), "BMW", "M3", 2005, "Jonas", new BigDecimal(435345)))
            .build();
    }

    private static Autohaus createClassicMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Classic Motors")
            .setUUID(UUID.randomUUID())
            .setAdresse(createAdresse())
            .setTelefonnummer("0721 987654")
            .setEmail("classic@motors.de")
            .setAutos(createAutoList(UUID.randomUUID(), "Mercedes", "C-Klasse", 2019, "Mika", new BigDecimal(54543)))
            .build();
    }

    private static Autohaus createUrbanAutos() {
        return AutohausBuilder.getBuilder()
            .setName("Urban Autos")
            .setUUID(UUID.randomUUID())
            .setAdresse(createAdresse())
            .setTelefonnummer("06221 987654")
            .setEmail("urban@gmx.de")
            .setAutos(createAutoList(UUID.randomUUID(), "Volkswagen", "Golf", 2003, "Goat", new BigDecimal(3445)))
            .build();
    }

    private static Autohaus createLuxuryMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Luxury Motors")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0761 543210")
            .setAdresse(createAdresse())
            .setEmail("luxury@motors.de")
            .setAutos(createAutoList(UUID.randomUUID(), "Porsche", "911", 2005, "Joans", new BigDecimal(34535)))
            .build();
    }

    private static Autohaus createEcoCars() {
        return AutohausBuilder.getBuilder()
            .setName("EcoCars")
            .setUUID(UUID.randomUUID())
            .setAdresse(createAdresse())
            .setTelefonnummer("0721 987123")
            .setEmail("eco@cars.des")
            .setAutos(createAutoList(UUID.randomUUID(),  "Tesla",  "Model 3",  2023, "Kenan", new BigDecimal(4335)))
            .build();
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

    private static Adresse createAdresse() {
        return  new Adresse("Albert","35","74533", "Wunderland");
    }
}
