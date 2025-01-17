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
    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    @Override
    List<Autohaus> findAll();

    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    @Override
    Optional<Autohaus> findById(UUID id);

    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    List<Autohaus> findByAutoId(UUID autoId);
}
