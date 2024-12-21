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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.acme.autohaus.entity.Autohaus.ADRESSE_GRAPH;

@Repository
public interface AutohausRepository extends JpaRepository<Autohaus, UUID>, JpaSpecificationExecutor<Autohaus> {
    @EntityGraph(ADRESSE_GRAPH)
    @Override
    List<Autohaus> getAll();

    @EntityGraph(ADRESSE_GRAPH)
    @Override
    List<Autohaus> getAll(@Nullable Specification<Autohaus> spec);

    @EntityGraph(ADRESSE_GRAPH)
    @Override
    Optional<Autohaus> getById(UUID id);

    /// Abfrage, ob es einen Autohausn mit gegebener Emailadresse gibt.
    ///
    /// @param email Emailadresse für die Suche
    /// @return true, falls es einen solchen Autohausn gibt, sonst false
    @SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
    boolean existsByEmail(String email);

    /// Autohausn anhand des Nachnamens suchen.
    ///
    /// @param nachname Der (Teil-) Nachname der gesuchten Autohausn
    /// @return Die gefundenen Autohausn oder eine leere Collection
    @Query("""
        SELECT   k
        FROM     #{#entityName} k
        WHERE    lower(k.nachname) LIKE concat('%', lower(:nachname), '%')
        ORDER BY k.nachname
        """)
    @EntityGraph(ADRESSE_GRAPH)
    List<Autohaus> findByName(CharSequence name);

    /// Abfrage, welche Nachnamen es zu einem Präfix gibt.
    ///
    /// @param prefix Nachname-Präfix.
    /// @return Die passenden Nachnamen oder eine leere Collection.
    @Query("""
        SELECT DISTINCT k.nachname
        FROM     #{#entityName} k
        WHERE    lower(k.nachname) LIKE concat(lower(:prefix), '%')
        ORDER BY k.nachname
        """)
    List<String> findNachnamenByPrefix(String prefix);
}
