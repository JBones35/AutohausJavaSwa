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
public class MockDB {
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

    private static Autohaus createOptikAutomobile() {
        return AutohausBuilder.getBuilder()
            .setName("Optik Automobile")
            .setStandort("Mühlacker")
            .setUUID(UUID.randomUUID()          )
            .setTelefonnummer("015202744231")
            .setMitarbeiter(createMitarbeiterList("1", "Abdullah", "Office", "Chef", 19, "abdullah@gmail.com", "Mühlacker", "35", "75417"))
            .setAutos(createAutoList("Pf-JN-2535", "Audi", "1.9", "blau", 2020, 163434, 24345))
            .setKunden(createKundenList("Jonas", 19, "kirschner.jonas@gmx.de", LocalDate.of(2005, 3, 25), "02394934", "015202744231", "Mühlacker", "37", "75417"))
            .build();
    }

    private static Autohaus createPremiumCars() {
        return AutohausBuilder.getBuilder()
            .setName("Premium Cars")
            .setStandort("Stuttgart")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0711 123456")
            .setMitarbeiter(createMitarbeiterList("2", "Laura", "Verkauf", "Verkäuferin", 34, "laura@premiumcars.de", "Stuttgart", "37", "234252"))
            .setAutos(createAutoList("B1-MW-1234", "BMW", "M3", "rot", 2021, 5000, 65000))
            .setKunden(createKundenList("Michael", 28, "michael@example.com", LocalDate.of(1995, 1, 20), "12345678", "0711 112233", "Stuttgart", "22", "70173"))
            .build();
    }

    private static Autohaus createClassicMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Classic Motors")
            .setStandort("Karlsruhe")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0721 987654")
            .setMitarbeiter(createMitarbeiterList("3", "Felix", "Werkstatt", "Mechaniker", 39, "felix@classicmotors.de", "Karlsruhe", "37", "76133"))
            .setAutos(createAutoList("M1-BC-2345", "Mercedes", "C-Klasse", "schwarz", 2019, 30000, 35000))
            .setKunden(createKundenList("Sophie", 25, "sophie@classicmotors.de", LocalDate.of(1998, 11, 5), "87654321", "0721 654321", "Karlsruhe", "5", "76131"))
            .build();
    }

    private static Autohaus createUrbanAutos() {
        return AutohausBuilder.getBuilder()
            .setName("Urban Autos")
            .setStandort("Heidelberg")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("06221 987654")
            .setMitarbeiter(createMitarbeiterList("4", "Klara", "Kundenservice", "Kundenberaterin", 32, "klara@urbanautos.de", "Heidelberg", "10", "69117"))
            .setAutos(createAutoList("VW-GR-9876", "Volkswagen", "Golf", "grün", 2023, 1500, 30000))
            .setKunden(createKundenList("Anna", 30, "anna@example.com", LocalDate.of(1994, 8, 15), "01234567", "06221 876543", "Heidelberg", "4", "69115"))
            .build();
    }

    private static Autohaus createLuxuryMotors() {
        return AutohausBuilder.getBuilder()
            .setName("Luxury Motors")
            .setStandort("Freiburg")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0761 543210")
            .setMitarbeiter(createMitarbeiterList("5", "Thomas", "Verkauf", "Filialleiter", 43, "thomas@luxurymotors.de", "Freiburg", "22", "79098"))
            .setAutos(createAutoList("POR-SIL-4567", "Porsche", "911", "silber", 2022, 200, 100000))
            .setKunden(createKundenList("Sarah", 29, "sarah@example.com", LocalDate.of(1995, 2, 28), "76543210", "0761 123456", "Freiburg", "33", "79097"))
            .build();
    }

    private static Autohaus createEcoCars() {
        return AutohausBuilder.getBuilder()
            .setName("EcoCars")
            .setStandort("Karlsruhe")
            .setUUID(UUID.randomUUID())
            .setTelefonnummer("0721 987123")
            .setMitarbeiter(createMitarbeiterList("6", "Julia", "Marketing", "Marketing-Managerin", 36, "julia@ecocars.de", "Karlsruhe", "28", "76133"))
            .setAutos(createAutoList("TESL-WH-1234", "Tesla", "Model 3", "weiß", 2023, 0, 45000))
            .setKunden(createKundenList("Leo", 27, "leo@ecocars.de", LocalDate.of(1996, 5, 22), "12345678", "0721 654321", "Karlsruhe", "19", "76131"))
            .build();
    }

    private static List<Mitarbeiter> createMitarbeiterList(String id, String name, String department, String position, int age, String email, String city, String houseNumber, String postalCode) {
        return List.of(
            MitarbeiterBuilder.getBuilder()
                .setMitarbeiterId(id)
                .setGehalt(BigDecimal.ZERO)
                .setName(name)
                .setAbteilung(department)
                .setGeburtsdatum(LocalDate.of(2015,12,6))
                .setTelefonnummer("0176 12345678")
                .setPosition(position)
                .setAlter(age)
                .setEmail(email)
                .setAdresse(AdresseBuilder.getBuilder()
                    .setHausnummer(houseNumber)
                    .setStadt(city)
                    .setStrasse("Musterstraße") // Default street name
                    .setPlz(postalCode)
                    .build())
                .build()
        );
    }

    private static List<Auto> createAutoList(String vehicleId, String brand, String model, String color, int year, int mileage, double price) {
        return List.of(
            AutoBuilder.getBuilder()
                .setFahrzeugId(vehicleId)
                .setMarke(brand)
                .setModell(model)
                .setFarbe(color)
                .setBaujahr(year)
                .setKilometerstand(mileage)
                .setPreis(price)
                .build()
        );
    }

    private static List<Kunde> createKundenList(String name, int age, String email, LocalDate birthDate, String customerNumber, String phoneNumber, String city, String houseNumber, String postalCode) {
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
                    .setStrasse("Musterstraße") // Default street name
                    .setPlz(postalCode)
                    .build())
                .build());
    }
}
