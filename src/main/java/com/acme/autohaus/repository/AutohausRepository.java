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
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import static com.acme.autohaus.entity.Autohaus.ADRESSE_AUTOS_GRAPH;

/**
 * Repository-Interface für den Zugriff auf {@link Autohaus}-Entitäten.
 * Bietet Methoden für die CRUD-Operationen und zusätzliche Suchanfragen.
 */
@Repository
public interface AutohausRepository extends JpaRepository<Autohaus, UUID>, JpaSpecificationExecutor<Autohaus> {

    /**
     * Findet alle Autohaus-Entitäten mit einem definierten EntityGraph, um Adressen und Autos zu laden.
     *
     * @return Eine Liste aller {@link Autohaus}-Entitäten.
     */
    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    @Override
    List<Autohaus> findAll();

    /**
     * Findet ein Autohaus anhand der ID mit einem definierten EntityGraph.
     *
     * @param id Die eindeutige UUID des Autohauses.
     * @return Ein {@link Optional}, das das gefundene Autohaus oder {@code Optional.empty()} enthält.
     */
    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    @Override
    Optional<Autohaus> findById(UUID id);

    /**
     * Findet alle Autohäuser, die mit einer bestimmten Auto-ID verknüpft sind, unter Verwendung eines EntityGraphs.
     *
     * @param autohausId Die eindeutige UUID des Autos.
     * @return Eine Liste der gefundenen {@link Autohaus}-Entitäten.
     */
    @EntityGraph(ADRESSE_AUTOS_GRAPH)
    @SuppressWarnings("checkstyle:MethodName")
    List<Autohaus> findByAutoForeignKeys_Id(UUID autohausId);
}
