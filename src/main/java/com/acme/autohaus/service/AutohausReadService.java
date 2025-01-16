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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [Projektname]. If not, see <http://www.gnu.org/licenses/>.
 */

package com.acme.autohaus.service;

import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.repository.AutohausRepository;
import com.acme.autohaus.repository.SpecificationBuilder;
import com.acme.autohaus.security.RolleType;
import io.micrometer.observation.annotation.Observed;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.acme.autohaus.security.RolleType.ADMIN;

/// Anwendungslogik für Autohäuser
/// ![Klassendiagramm](../../../../../asciidoc/AutohausReadService.svg)
@Service
@Transactional(readOnly = true)
public class AutohausReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutohausReadService.class);

    private final AutohausRepository repo;

    private final SpecificationBuilder specificationBuilder;

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    ///
    /// @param repo Injiziertes Repository für _Spring Data_.
    /// @param specificationBuilder Builder-Pattern für _Specification_s bei _Spring Data_.
    AutohausReadService(final AutohausRepository repo, final SpecificationBuilder specificationBuilder) {
        this.repo = repo;
        this.specificationBuilder = specificationBuilder;
    }

    /// Ein Autohaus anhand seiner ID suchen.
    ///
    /// @param id Die Id des gesuchten Autohauses
    /// @param fetchAutos boolean für fetch-autos
    /// @param username Username
    /// @param rollen Die Rollen des Users
    /// @return das gefundene Autohaus
    /// @throws NotFoundException Falls kein Autohaus gefunden wurde
    @Observed(name = "find-by-id")
    public @NonNull Autohaus findById(
        final UUID id,
        final String username,
        final List<RolleType> rollen,
        final boolean fetchAutos
    ) {
        LOGGER.debug("findById: id={}", id);

        final var autohausOptional = fetchAutos ? repo.findByIdFetchAutos(id) : repo.findById(id);
        final var autohaus = autohausOptional.orElse(null);
        LOGGER.trace("findById: Autohaus={}", autohaus);

        if (autohaus != null && autohaus.getUsername().contentEquals(username)) {
            return autohaus;
        }

        if (!rollen.contains(ADMIN)) {
            throw new AuthorizationDeniedException(rollen.toString());
        }

        if (autohaus == null) {
            throw new NotFoundException(id);
        }
        LOGGER.debug("findById: autohaus={}, autos={}", autohaus, fetchAutos ? autohaus.getAutos() : "N/A");
        return autohaus;
    }

    /// Autohäuser anhand von Suchkriterien als List suchen.
    ///
    /// @param suchkriterien Die Suchkriterien
    /// @return Die gefundenen Autohäuser oder eine leere Liste
    /// @throws NotFoundException Falls keine Autohäuser gefunden wurden
    @SuppressWarnings("ReturnCount")
    public @NonNull List<Autohaus> find(@NonNull final Map<String, List<String>> suchkriterien) {
        LOGGER.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return repo.findAll();
        }

        if (suchkriterien.size() == 1) {
            final var name = suchkriterien.get("name");
            if (name != null && name.size() == 1) {
                return findByName(name.getFirst(), suchkriterien);
            }

            final var emails = suchkriterien.get("email");
            if (emails != null && emails.size() == 1) {
                return findByEmail(emails.getFirst(), suchkriterien);
            }
        }

        final var specification = specificationBuilder
            .build(suchkriterien)
            .orElseThrow(() -> new NotFoundException(suchkriterien));
        final var autohaeuser = repo.findAll(specification);
        if (autohaeuser.isEmpty()) {
            throw new NotFoundException(suchkriterien);
        }
        LOGGER.debug("find: {}", autohaeuser);
        return autohaeuser;
    }

    private List<Autohaus> findByName(final String name, final Map<String, List<String>> suchkriterien) {
        LOGGER.trace("findByName: {}", name);
        final var autohaeuser = repo.findByName(name);
        if (autohaeuser.isEmpty()) {
            throw new NotFoundException(suchkriterien);
        }
        LOGGER.debug("findByName: {}", autohaeuser);
        return autohaeuser;
    }

    private List<Autohaus> findByEmail(final String email, final Map<String, List<String>> suchkriterien) {
        LOGGER.trace("findByEmail: {}", email);
        final var autohaus = repo
            .findByEmail(email)
            .orElseThrow(() -> new NotFoundException(suchkriterien));
        final var autohaeuser = List.of(autohaus);
        LOGGER.debug("findByEmail: {}", autohaeuser);
        return autohaeuser;
    }
}
