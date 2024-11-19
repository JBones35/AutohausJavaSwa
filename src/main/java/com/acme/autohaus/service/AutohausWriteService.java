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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Autohaus. If not, see <http://www.gnu.org/licenses/>.
 */

package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import jakarta.validation.Valid;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * Service-Klasse für Schreiboperationen auf Autohaus-Daten.
 */
@Service
public class AutohausWriteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausWriteService.class);

    private final AutohausRepository autohausRepository;

    /**
     * Konstruktor zur Initialisierung des AutohausWriteService mit dem AutohausRepository.
     *
     * @param autohausRepository das Repository für Autohaus-Daten.
     */
    public AutohausWriteService(final AutohausRepository autohausRepository) {
        this.autohausRepository = autohausRepository;
    }

    /**
     * Erstellt ein neues Autohaus und speichert es in der Datenbank.
     *
     * @param autohaus das zu erstellende Autohaus-Objekt.
     * @return das erstellte Autohaus-Objekt.
     * @throws EmailExistsException wenn die E-Mail-Adresse bereits existiert.
     */
    public Autohaus create(@Valid final Autohaus autohaus) {
        LOGGER.debug("create: {}", autohaus);

        // Überprüfen, ob die E-Mail-Adresse bereits existiert
        if (autohausRepository.isEmailExisting(autohaus.getEmail())) {
            throw new EmailExistsException(autohaus.getEmail());
        }

        // Neues Autohaus-Objekt erstellen und speichern
        final var autohausDB = autohausRepository.create(autohaus);
        LOGGER.debug("create: {}", autohausDB);
        return autohausDB;
    }

    /**
     * Aktualisiert ein vorhandenes Autohaus-Objekt anhand der ID.
     *
     * @param autohaus das zu aktualisierende Autohaus-Objekt.
     * @param id       die UUID des zu aktualisierenden Autohauses.
     * @throws NotFoundException    wenn kein Autohaus mit der angegebenen ID gefunden wird.
     * @throws EmailExistsException wenn die neue E-Mail-Adresse bereits existiert.
     */
    public void update(@Valid final Autohaus autohaus, final UUID id) {
        LOGGER.debug("update: {}", autohaus);
        LOGGER.debug("update: id={}", id);

        // E-Mail des Autohaus-Objekts abrufen
        final var email = autohaus.getEmail();

        // Autohaus aus der Datenbank anhand der ID abrufen
        final var autohausDb = autohausRepository
            .getByID(id.toString())
            .orElseThrow(() -> new NotFoundException(id.toString()));

        // Überprüfen, ob die neue E-Mail-Adresse bereits existiert
        if (!Objects.equals(email, autohausDb.getEmail()) && autohausRepository.isEmailExisting(email)) {
            LOGGER.debug("update: email {} existiert", email);
            throw new EmailExistsException(email);
        }

        // UUID setzen und Autohaus-Objekt aktualisieren
        autohaus.setUUId(id);
        autohausRepository.update(autohaus);
    }
}
