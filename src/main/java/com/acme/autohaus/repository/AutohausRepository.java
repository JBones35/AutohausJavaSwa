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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Autohaus.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus.repository;

import com.acme.autohaus.entity.Autohaus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import static com.acme.autohaus.entity.Autohaus.ADRESSE_AUTOS_GRAPH;
import static com.acme.autohaus.entity.Autohaus.ADRESSE_GRAPH;

/// Repository Interface für den Mikroservice Autohaus
@Repository
public interface AutohausRepository extends JpaRepository<Autohaus, UUID>, JpaSpecificationExecutor<Autohaus> {
    @EntityGraph(ADRESSE_GRAPH)
    @Override
    List<Autohaus> findAll();

    @EntityGraph(ADRESSE_GRAPH)
    @Override
    List<Autohaus> findAll(@Nullable Specification<Autohaus> spec);

    @EntityGraph(ADRESSE_GRAPH)
    @Override
    Optional<Autohaus> findById(UUID id);

    /// Kunde einschließlich Umsätze anhand der ID suchen.
    ///
    /// @param id Kunde ID
    /// @return Gefundener Kunde
    @Query("""
        SELECT DISTINCT a
        FROM     #{#entityName} a
        WHERE    a.id = :id
        """)
    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    @NonNull
    Optional<Autohaus> findByIdFetchAutos(UUID id);

    /// Autohaus zu gegebener Emailadresse aus der DB ermitteln.
    ///
    /// @param email Emailadresse für die Suche
    /// @return Optional mit dem gefundenen Autohaus oder leeres Optional
    @Query("""
        SELECT a
        FROM   #{#entityName} a
        WHERE  lower(a.email) LIKE concat(lower(:email), '%')
        """)
    @EntityGraph(ADRESSE_GRAPH)
    Optional<Autohaus> findByEmail(String email);

    /// Abfrage, ob es ein Autohaus mit gegebener Emailadresse gibt.
    ///
    /// @param email Emailadresse für die Suche
    /// @return true, falls es ein solches Autohaus gibt, sonst false
    @SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
    boolean existsByEmail(String email);

    /// Autohäuser anhand des Names suchen.
    ///
    /// @param name Der (Teil-) Name des gesuchten Autohauses
    /// @return Die gefundenen Autohäuser oder eine leere Collection
    @Query("""
        SELECT   a
        FROM     #{#entityName} a
        WHERE    lower(a.name) LIKE concat('%', lower(:name), '%')
        ORDER BY a.name
        """)
    @EntityGraph(ADRESSE_GRAPH)
    List<Autohaus> findByName(CharSequence name);
}
