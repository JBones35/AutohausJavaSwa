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
import com.acme.autohaus.mail.MailService;
import com.acme.autohaus.repository.AutohausRepository;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service-Klasse für Schreiboperationen auf Autohaus-Daten.
 */
@Service
@Transactional(readOnly = true)
public class AutohausWriteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausWriteService.class);

    private final AutohausRepository repo;

    private final MailService mailService;

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    ///
    /// @param repo Injiziertes Repository für _Spring Data_.
    /// @param mailService Injiziertes Objekt für Mailing.
    AutohausWriteService(final AutohausRepository repo, final MailService mailService) {
        this.repo = repo;
        this.mailService = mailService;
    }

    /// Einen neues Autohaus anlegen.
    ///
    /// @param autohaus Das Objekt des neu anzulegenden Autohauses.
    /// @return Das neu angelegte Autohaus mit generierter ID
    /// @throws EmailExistsException Es gibt bereits ein Autohaus mit der Emailadresse.
    @Transactional
    @SuppressWarnings("TrailingComment")
    public Autohaus create(final Autohaus autohaus) {
        LOGGER.debug("create: autohaus={}", autohaus);
        LOGGER.debug("create: adresse={}", autohaus.getAdresse());
        LOGGER.debug("create: autos={}", autohaus.getAutos());

        if (repo.existsByEmail(autohaus.getEmail())) {
            throw new EmailExistsException(autohaus.getEmail());
        }

        autohaus.setUsername("user");
        final var autohausDB = repo.save(autohaus);

        LOGGER.trace("create: Thread-ID={}", Thread.currentThread().threadId());
        mailService.send(autohaus);

        LOGGER.debug("create: autohausDB={}", autohausDB);
        return autohausDB;
    }

    /// Ein vorhandenes Autohaus aktualisieren.
    ///
    /// @param autohaus Das Objekt mit den neuen Daten (ohne ID)
    /// @param id ID des zu aktualisierenden Autohauses
    /// @param version Die erforderliche Version
    /// @return Aktualisiertes Autohaus mit erhöhter Versionsnummer
    /// @throws NotFoundException Kein Autohaus zur ID vorhanden.
    /// @throws VersionOutdatedException Die Versionsnummer ist veraltet und nicht aktuell.
    /// @throws EmailExistsException Es gibt bereits ein Autoháus mit der Emailadresse.
    @Transactional
    public Autohaus update(final Autohaus autohaus, final UUID id, final int version) {
        LOGGER.debug("update: autohaus={}", autohaus);
        LOGGER.debug("update: id={}, version={}", id, version);

        var autohausDB = repo
            .findById(id)
            .orElseThrow(() -> new NotFoundException(id));
        LOGGER.trace("update: version={}, autohausDb={}", version, autohausDB);
        if (version != autohausDB.getVersion()) {
            throw new VersionOutdatedException(version);
        }

        final var email = autohaus.getEmail();
        if (!Objects.equals(email, autohausDB.getEmail()) && repo.existsByEmail(email)) {
            LOGGER.debug("update: email {} existiert", email);
            throw new EmailExistsException(email);
        }
        LOGGER.trace("update: Kein Konflikt mit der Emailadresse");

        autohausDB.set(autohaus);
        autohausDB = repo.save(autohausDB);

        LOGGER.debug("update: {}", autohausDB);
        return autohausDB;
    }
}
