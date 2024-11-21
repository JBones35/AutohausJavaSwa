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
package com.acme.autohaus.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * "RuntimeHints" für GraalVM, damit die PEM- und CRT-Dateien zur Laufzeit gefunden werden.
 */
// https://stackoverflow.com/questions/...
// ...76287163/how-to-specify-the-location-of-a-keystore-file-with-spring-aot-processing
@ImportRuntimeHints(CertificateRuntimeHints.CertificateResourcesRegistrar.class)
public class CertificateRuntimeHints {
    /**
     * StandardKonstruktor
     */
    public CertificateRuntimeHints() {
        //Standardkonstruktor
    }
    /**
     * Registrierung der PEM- und CRT-Dateien für GraalVM.
     */
    static final class CertificateResourcesRegistrar implements RuntimeHintsRegistrar {
        /**
         * StandardKonstruktor
         */
        private CertificateResourcesRegistrar() {
            //Standardkonstruktor
        }

        @Override
        public void registerHints(final RuntimeHints hints, final ClassLoader classLoader) {
            hints.resources().registerPattern("*.pem");
            hints.resources().registerPattern("*.crt");
        }
    }

}
