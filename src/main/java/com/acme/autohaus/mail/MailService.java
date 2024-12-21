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
package com.acme.autohaus.mail;

import com.acme.autohaus.entity.Autohaus;
import jakarta.mail.internet.InternetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import static jakarta.mail.Message.RecipientType.TO;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

/// Mail-Client.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@Service
@SuppressWarnings("ClassNamePrefixedWithPackageName")
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    /// Objekt für _Jakarta Mail_, um Emails zu verschicken
    private final JavaMailSender mailSender;

    /// Injizierte Properties für _Spring Mail_.
    private final MailProps mailProps;

    /// Mailserver
    @Value("${spring.mail.host}")
    private String mailhost;

    /// Konstruktor mit `package private` für _Constructor Injection_ bei _Spring_.
    ///
    /// @param mailSender Injiziertes Objekt für _Spring Mail_.
    /// @param mailProps Injiziertes Property-Objekt für _Spring Mail_.
    MailService(final JavaMailSender mailSender, final MailProps mailProps) {
        this.mailSender = mailSender;
        this.mailProps = mailProps;
    }

    /// Email senden, dass es einen neuen autohausn gibt.
    ///
    /// @param neuesAutohaus Das Objekt des neuen autohausn.
    @Async
    public void send(final Autohaus neuesAutohaus) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setFrom(new InternetAddress(mailProps.from()));
            mimeMessage.setRecipient(TO, new InternetAddress(mailProps.sales()));
            mimeMessage.setSubject("Neues autohaus " + neuesAutohaus.getId());
            final var body = "<strong>Neues autohaus:</strong> <em>" + neuesAutohaus.getName() + "</em>";
            mimeMessage.setText(body);
            mimeMessage.setHeader(CONTENT_TYPE, TEXT_HTML_VALUE);

            LOGGER.trace("send: Thread-ID={}, mailhost={}, body={}", Thread.currentThread().threadId(), mailhost, body);
        };

        try {
            mailSender.send(preparator);
        } catch (MailSendException | MailAuthenticationException _) {
            LOGGER.warn("Email nicht gesendet: Ist der Mailserver {} erreichbar?", mailhost);
        }
    }
}
