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
package com.acme.autohaus.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

import static org.springframework.context.annotation.Bean.Bootstrap.BACKGROUND;

sealed interface LogSignatureAlgorithms permits DevConfig {
    /// Bean-Definition, um einen _Listener_ bereitzustellen, damit die im JDK vorhandenen Signature-Algorithmen
    /// aufgelistet werden.
    ///
    /// @return Listener für die Ausgabe der Signature-Algorithmen
    @Bean(bootstrap = BACKGROUND)
    @Profile("logSignature")
    @SuppressWarnings("LambdaBodyLength")
    default ApplicationListener<ApplicationReadyEvent> logSignatureAlgorithms() {
        final var log = LoggerFactory.getLogger(LogSignatureAlgorithms.class);
        return _ -> Arrays
            .stream(Security.getProviders())
            .forEach(provider -> logSignatureAlgorithms(provider, log));
    }

    private void logSignatureAlgorithms(final Provider provider, final Logger log) {
        provider
            .getServices()
            .forEach(service -> {
                if ("Signature".contentEquals(service.getType())) {
                    log.debug("{}", service.getAlgorithm());
                }
            });
    }
}
