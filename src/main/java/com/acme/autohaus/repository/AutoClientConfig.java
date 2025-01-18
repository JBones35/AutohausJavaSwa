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
/*
 * Copyright (C) 2023 - present Juergen Zimmermann, Hochschule Karlsruhe
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
package com.acme.autohaus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.client.RestClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;

/// Beans für die _REST_-Schnittstelle zu _auto_ (`AutohausRepository`) und für die _GraphQL_-Schnittstelle zu _auto_
/// (`HttpGraphQlClient`).
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@SuppressWarnings("java:S1075")
public class AutoClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoClientConfig.class);

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    AutoClientConfig() {
    }

    /// Bean-Methode, um ein Objekt von `UriComponentsBuilder` für die URI für _Keycloak_ zu erstellen.
    ///
    /// @return Objekt von `UriComponentsBuilder` für die URI für _Keycloak_
    @Bean
    @SuppressWarnings("CallToSystemGetenv")
    UriComponentsBuilder uriComponentsBuilder() {
        final var autoDefaultPort = 8080;

        // Umgebungsvariable in Kubernetes, sonst: null
        final var autoSchemaEnv = System.getenv("KUNDE_SERVICE_SCHEMA");
        final var autoHostEnv = System.getenv("KUNDE_SERVICE_HOST");
        final var autoPortEnv = System.getenv("KUNDE_SERVICE_PORT");

        final var schema = autoSchemaEnv == null ? "https" : autoSchemaEnv;
        final var host = autoHostEnv == null ? "localhost" : autoHostEnv;
        final int port = autoPortEnv == null ? autoDefaultPort : Integer.parseInt(autoPortEnv);

        LOGGER.debug("auto: host={}, port={}", host, port);
        return UriComponentsBuilder.newInstance()
            .scheme(schema)
            .host(host)
            .port(port);
    }

    /// Bean-Methode, um ein Objekt von `AutohausRepository` für die _REST_-Schnittstelle von _auto_ zu erstellen.
    ///
    /// @param uriComponentsBuilder Injiziertes Objekt von `UriComponentsBuilder`
    /// @param restClientBuilder Injiziertes Objekt von `RestClient.Builder`
    /// @param restClientSsl Injiziertes Objekt von `RestClientSsl`
    /// @return Objekt von `AutohausRepository` für die _REST_-Schnittstelle von _auto_
    @Bean
    AutoRepository autoRepository(
        final UriComponentsBuilder uriComponentsBuilder,
        final RestClient.Builder restClientBuilder,
        final RestClientSsl restClientSsl
    ) {
        final var baseUrl = uriComponentsBuilder.build().toUriString();
        LOGGER.info("REST-Client: baseUrl={}", baseUrl);

        final var restClient = restClientBuilder
            .baseUrl(baseUrl)
            // siehe Property "spring.ssl.bundle.jks.microservice" in src\main\resources\application.yml
            // https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.ssl
            // org.springframework.boot.autoconfigure.web.client.AutoConfiguredRestClientSsl
            .apply(restClientSsl.fromBundle("microservice"))
            .build();
        final var clientAdapter = RestClientAdapter.create(restClient);
        final var proxyFactory = HttpServiceProxyFactory.builderFor(clientAdapter).build();
        return proxyFactory.createClient(AutoRepository.class);
    }
}
