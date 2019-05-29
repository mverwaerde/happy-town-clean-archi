package com.happytown.dataproviders.mail;

import com.github.sleroy.fakesmtp.core.ServerConfiguration;
import com.github.sleroy.junit.mail.server.test.FakeSmtpRule;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.mail.MessagingException;

@SpringBootTest
@EnableRuleMigrationSupport
@TestPropertySource(properties = {"mail.smtp.host=localhost", "mail.smtp.port=9999"})
public class NotificationExceptionTest {

    @Autowired
    private NotificationMailProvider mailProvider;
    private int wrongPort = 2323;

    @Rule
    public FakeSmtpRule smtpServer = new FakeSmtpRule(
            ServerConfiguration.create()
                    .bind("localhost")
                    .charset("UTF-8")
                    .port(wrongPort)
                    .relayDomains("example.fr"));


    @Test
    public void shouldThrowNotificationException_withFakePort() {
        String to = "mave@example.fr";
        String subject = "happy birthday in Happy Town!";
        String body = "Vous allez recevoir un cadeau";

        Assertions.assertThatThrownBy(() -> mailProvider.notifier(to, subject, body))
                .hasMessage("Une erreur a eu lieu lors de l'envoi du message a mave@example.fr")
                .hasCause(new MessagingException("Could not connect to SMTP host: localhost, port: 9999"));
    }

}