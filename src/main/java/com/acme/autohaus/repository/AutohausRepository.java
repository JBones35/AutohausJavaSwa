package com.acme.autohaus.repository;

import com.acme.autohaus.entity.Autohaus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.acme.autohaus.repository.MockDB.AUTOHAUSER;

/**
 * Repository-Klasse für den Zugriff auf und die Verwaltung von Autohaus-Daten.
 */
@Repository
public class AutohausRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausRepository.class);

    /**
     * Erstellt eine Instanz der AutohausRepository-Klasse.
     */
    public AutohausRepository() {
    }

    /**
     * Gibt eine Liste von Autohaus-Objekten zurück, die einem bestimmten Standort entsprechen.
     *
     * @param location der Standort, nach dem gesucht wird
     * @return eine Liste der gefundenen Autohaus-Objekte
     */
    public @NonNull List<Autohaus> getByLocation(final String location) {
        LOGGER.debug("Suche nach Autohausern an Standort: {}", location);

        List<Autohaus> autohauser = AUTOHAUSER.stream()
            .filter(autohaus -> Objects.equals(autohaus.getStandort(), location))
            .toList();

        LOGGER.debug("Gefundene Autohauser für Standort {}: {}", location, autohauser);
        return autohauser;
    }

    /**
     * Gibt eine Liste aller Autohaus-Objekte zurück.
     *
     * @return eine Liste aller Autohauser
     */
    public @NonNull List<Autohaus> getAll() {
        return AUTOHAUSER;
    }

    /**
     * Sucht ein Autohaus-Objekt anhand seiner id.
     *
     * @param id die UUID des gesuchten Autohauses
     * @return das gefundene Autohaus, falls vorhanden
     */
    public @NonNull Optional<Autohaus> getByID(@NonNull final String id) {
        LOGGER.debug("Suche nach Autohaus mit id: {}", id);

        Optional<Autohaus> autohaus = AUTOHAUSER.stream()
            .filter(a -> Objects.equals(id, a.getUUID().toString()))
            .findFirst();

        LOGGER.debug("Ergebnis der Suche nach id {}: {}", id, autohaus);
        return autohaus;
    }
}
