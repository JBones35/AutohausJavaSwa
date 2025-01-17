/*
 * This file is part of [autohaus].
 *
 * [autohaus] is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * [autohaus] is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with [autohaus].  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.autohaus;

import com.acme.autohaus.dev.DevConfig;
import com.acme.autohaus.mail.MailProps;
import com.acme.autohaus.repository.AutoClientConfig;
import com.acme.autohaus.security.KeycloakProps;
import com.acme.autohaus.security.SecurityConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static com.acme.autohaus.Banner.TEXT;

/// Klasse mit der `main`-Methode für die Anwendung auf Basis von _Spring Boot_.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@SpringBootApplication(proxyBeanMethods = false)
@Import({SecurityConfig.class, AutoClientConfig.class, DevConfig.class})
@EnableConfigurationProperties(KeycloakProps.class)
@EnableJpaRepositories
@EnableWebSecurity
@EnableMethodSecurity
public final class Application {
    private Application() {
    }

    /// Hauptprogramm, um den Microservice zu starten.
    ///
    /// @param args Evtl. zusätzliche Argumente für den Start des Microservice
    public static void main(final String... args) {
        new SpringApplicationBuilder(Application.class)
            .banner((_, _, out) -> out.println(TEXT))
            .run(args);
    }
}
