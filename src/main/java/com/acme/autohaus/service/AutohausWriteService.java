/*
 * Copyright (C) 20""16"" - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/// Anwendungslogik für Autohausen.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@Service
@Transactional(readOnly = true)
public class AutohausWriteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausWriteService.class);

    private final AutohausRepository repo;

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    ///
    /// @param repo Repository-Objekt für _Spring Data_.
    AutohausWriteService(final AutohausRepository repo) {
        this.repo = repo;
    }

    /// Eine neue Autohaus anlegen, falls keine Constraint-Verletzungen vorliegen.
    ///
    /// @param autohaus Das Objekt der neu anzulegenden Autohaus.
    /// @return Die neu angelegte Autohaus mit generierter ID.
    @Transactional
    public Autohaus create(final Autohaus autohaus) {
        LOGGER.debug("create: {}", autohaus);
        final var autohausDb = repo.save(autohaus);
        LOGGER.debug("create: {}", autohaus);
        return autohausDb;
    }
}
