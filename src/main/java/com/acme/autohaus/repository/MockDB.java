package com.acme.autohaus.repository;

import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.entity.Kunde;
import com.acme.autohaus.entity.Mitarbeiter;
import com.acme.autohaus.repository.builder.AdresseBuilder;
import com.acme.autohaus.repository.builder.AutoBuilder;
import com.acme.autohaus.repository.builder.AutohausBuilder;
import com.acme.autohaus.repository.builder.KundeBuilder;
import com.acme.autohaus.repository.builder.MitarbeiterBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Mock-Datenbank-Klasse für die Simulation von Autohaus-Daten.
 * Diese Klasse enthält eine statische Liste von Autohaus-Objekten,
 * die zu Testzwecken verwendet werden kann.
 */
@SuppressWarnings({"checkstyle:ParameterNumber", "UtilityClassCanBeEnum",
    "UtilityClass", "MagicNumber", "RedundantSuppression", "java:S1192"})
public final class MockDB {
    @SuppressWarnings("StaticCollection")
    static final List<Autohaus> AUTOHAUSER;

    static {
        AUTOHAUSER = Stream.of(
            createOptikAutomobile(),
            createPremiumCars(),
            createClassicMotors(),
            createUrbanAutos(),
            createLuxuryMotors(),
            createEcoCars()
        ).toList();
    }

    private MockDB() {
    }

    private static Autohaus createOptikAutomobile() {
        return AutohausBuilder.getBuilder()
            .setName("Optik Automobile")
            .setStandort("Mühlacker")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("015202744231")
            .setMitarbeiter(createMitarbeiterList("1", "Abdullah", "Office", 19, "Mühlacker", "35", "75417"))
            .setAutos(createAutoList("Pf-JN-2535", "Audi", "1.9", 2012, "Jonas", 163434))
            .setKunden(createKundenList("Jonas", 19, "kirschner.jonas@gmx.de",
                LocalDate.of(2005, 3, 25), "02394934", "015202744231", "Mühlacker", "37", "75417"))
            .build();
    }

    private static Autohaus createPremiumCars() {
        return AutohausBuilder.getBuilder()
            .setName("Premium Cars")
            .setStandort("Stuttgart")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0711 123456")
            .setMitarbeiter(createMitarbeiterList("2", "Laura", "Verkauf",
                34, "Stuttgart", "37", "234252"))
            .setAutos(createAutoList("B1-MW-1234", "BMW", "M3", 2005, "Jonas", 5000))
            .setKunden(createKundenList("Michael", 28, "michael@example.com",
                LocalDate.of(1995, 1, 20), "12345678", "0711 112233", "Stuttgart", "22", "70173"))
            .build();
    }

    private static Autohaus createClassicMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Classic Motors")
            .setStandort("Karlsruhe")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0721 987654")
            .setMitarbeiter(createMitarbeiterList("3", "Felix", "Werkstatt", 39, "Karlsruhe", "37", "76133"))
            .setAutos(createAutoList("M1-BC-2345", "Mercedes", "C-Klasse", 2019, "Mika", 35000))
            .setKunden(createKundenList("Sophie", 25, "sophie@classicmotors.de",
                LocalDate.of(1998, 11, 5), "87654321", "0721 654321", "Karlsruhe", "5", "76131"))
            .build();
    }

    private static Autohaus createUrbanAutos() {
        return AutohausBuilder.getBuilder()
            .setName("Urban Autos")
            .setStandort("Heidelberg")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("06221 987654")
            .setMitarbeiter(createMitarbeiterList("4", "Klara", "Kundenservice", 32, "Heidelberg", "10", "69117"))
            .setAutos(createAutoList("VW-GR-9876", "Volkswagen", "Golf", 2003, "Goat", 1500))
            .setKunden(createKundenList("Anna", 30, "anna@example.com",
                LocalDate.of(1994, 8, 15), "01234567", "06221 876543", "Heidelberg", "4", "69115"))
            .build();
    }

    private static Autohaus createLuxuryMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Luxury Motors")
            .setStandort("Freiburg")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0761 543210")
            .setMitarbeiter(createMitarbeiterList("5", "Thomas", "Verkauf", 43, "Freiburg", "22", "79098"))
            .setAutos(createAutoList("POR-SIL-4567", "Porsche", "911", 2005, "Joans", 200))
            .setKunden(createKundenList("Sarah", 29, "sarah@example.com",
                LocalDate.of(1995, 2, 28), "76543210", "0761 123456", "Freiburg", "33", "79097"))
            .build();
    }

    private static Autohaus createEcoCars() {
        return AutohausBuilder.getBuilder()
            .setName("EcoCars")
            .setStandort("Karlsruhe")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0721 987123")
            .setMitarbeiter(createMitarbeiterList("6",  "Julia",
                "Marketing-Managerin",  36,  "Karlsruhe",  "28", "76133"))
            .setAutos(createAutoList("TESL-WH-1234",  "Tesla",  "Model 3",  2023, "Kenan", 0))
            .setKunden(createKundenList("Leo", 27, "leo@ecocars.de",
                LocalDate.of(1996, 5, 22), "12345678", "0721 654321", "Karlsruhe", "19", "76131"))
            .build();
    }

    private static List<Mitarbeiter> createMitarbeiterList(final String id, final String name,
                                                           final String position, final int age, final String city,
                                                           final String houseNumber, final String postalCode) {
        return List.of(
            MitarbeiterBuilder.getBuilder()
                .setMitarbeiterId(id)
                .setGehalt(BigDecimal.ZERO)
                .setName(name)
                .setGeburtsdatum(LocalDate.of(2015, 12, 6))
                .setPosition(position)
                .setAlter(age)
                .setAdresse(AdresseBuilder.getBuilder()
                    .setHausnummer(houseNumber)
                    .setStadt(city)
                    .setStrasse("Musterstraße")
                    .setPlz(postalCode)
                    .build())
                .build()
        );
    }

    private static List<Auto> createAutoList(final String vehicleId, final String brand, final String model,
                                             final int year, final String besitzer, final double price) {
        return List.of(
            AutoBuilder.getBuilder()
                .setFahrzeugId(vehicleId)
                .setMarke(brand)
                .setModell(model)
                .setBaujahr(year)
                .setBesitzer(besitzer)
                .setPreis(price)
                .build()
        );
    }

    private static List<Kunde> createKundenList(final String name, final int age, final String email,
                                                final LocalDate birthDate, final String customerNumber,
                                                final String phoneNumber,
                                                final String city, final String houseNumber, final String postalCode) {
        return List.of(
            KundeBuilder.getBuilder()
                .setName(name)
                .setAlter(age)
                .setEmail(email)
                .setGeburtsdatum(birthDate)
                .setKundennummer(customerNumber)
                .setTelefonnummer(phoneNumber)
                .setAdresse(AdresseBuilder.getBuilder()
                    .setHausnummer(houseNumber)
                    .setStadt(city)
                    .setStrasse("Musterstraße")
                    .setPlz(postalCode)
                    .build())
                .build());
    }
}
