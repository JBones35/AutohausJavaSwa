package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse für Leseoperationen auf Autohaus-Daten.
 */
@Service
public class AutohausReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausReadService.class);
    private final AutohausRepository autohausRepository;

    /**
     * Erstellt eine Instanz der AutohausReadService-Klasse.
     *
     * @param autohausRepository das Repository für Autohaus-Operationen
     */
    public AutohausReadService(AutohausRepository autohausRepository) {
        this.autohausRepository = autohausRepository;
    }

    /**
     * Sucht nach Autohaus-Objekten an einem bestimmten Standort.
     *
     * @param location der Standort, an dem gesucht wird
     * @return eine Liste der gefundenen Autohaus-Objekte
     * @throws IllegalArgumentException wenn kein Autohaus an diesem Standort gefunden wird
     */
    public @NonNull List<Autohaus> getByLocation(String location) {
        LOGGER.debug("Starte Standortsuche: {}", location);

        List<Autohaus> autohauser = autohausRepository.getByLocation(location);
        if (autohauser.isEmpty()) {
            LOGGER.warn("Keine Autohauser am Standort {} gefunden.", location);
            throw new IllegalArgumentException("Kein Autohaus für den angegebenen Standort gefunden.");
        }

        LOGGER.debug("Standortsuche beendet: {}", location);
        return autohauser;
    }

    /**
     * Gibt alle Autohaus-Objekte in der Datenbank zurück.
     *
     * @return eine Liste aller vorhandenen Autohauser
     * @throws IllegalArgumentException wenn keine Autohauser in der Datenbank vorhanden sind
     */
    public @NonNull List<Autohaus> getAll() {
        LOGGER.debug("Starte Abruf aller Autohauser");

        List<Autohaus> autohauser = autohausRepository.getAll();
        if (autohauser.isEmpty()) {
            throw new IllegalArgumentException("Keine Autohäuser in der Datenbank gefunden.");
        }

        return autohauser;
    }

    /**
     * Sucht ein Autohaus anhand seiner id.
     *
     * @param id die id des Autohauses
     * @return das gefundene Autohaus
     * @throws IllegalArgumentException wenn kein Autohaus mit dieser id gefunden wird
     */
    public @NonNull Autohaus getByID(String id) {
        LOGGER.debug("Starte Suche nach Autohaus mit id: {}", id);

        Autohaus autohaus = autohausRepository.getByID(id)
            .orElseThrow(() -> new IllegalArgumentException("Kein Autohaus für die angegebene id gefunden."));

        LOGGER.debug("Suche nach Autohaus mit id beendet");
        return autohaus;
    }
}
