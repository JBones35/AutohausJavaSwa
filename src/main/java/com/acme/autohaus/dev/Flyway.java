package com.acme.autohaus.dev;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

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
sealed interface Flyway permits DevConfig {
    /// Bean-Definition, um eine Migrationsstrategie für _Flyway_ im Profile `dev` bereitzustellen, so dass zuerst alle
    /// Tabellen, Indexe etc. gelöscht und dann neu aufgebaut werden.
    ///
    /// @return `FlywayMigrationStrategy`
    @Bean
    default FlywayMigrationStrategy flywayMigrationStrategy() {
        // https://www.javadoc.io/doc/org.flywaydb/flyway-core/latest/org/flywaydb/core/Flyway.html
        return flyway -> {
            // Loeschen aller DB-Objekte im Schema: Tabellen, Indexe, Stored Procedures, Trigger, Views, ...
            // insbesondere die Tabelle flyway_schema_history
            flyway.clean();
            // Start der DB-Migration
            flyway.migrate();
        };
    }
}
