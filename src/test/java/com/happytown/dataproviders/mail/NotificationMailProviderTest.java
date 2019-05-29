package com.happytown.dataproviders.mail;

import com.github.sleroy.fakesmtp.core.ServerConfiguration;
import com.github.sleroy.fakesmtp.model.EmailModel;
import com.github.sleroy.junit.mail.server.test.FakeSmtpRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableRuleMigrationSupport
@TestPropertySource(properties = {"mail.smtp.host=localhost", "mail.smtp.port=9999"})
public class NotificationMailProviderTest {

    @Autowired
    private NotificationMailProvider mailProvider;

    @Rule
    public FakeSmtpRule smtpServer = new FakeSmtpRule(
            ServerConfiguration.create()
                    .bind("localhost")
                    .charset("UTF-8")
                    .port(9999)
                    .relayDomains("example.fr"));

    @Test
    public void shouldVerifyMail_whenMailWasSend() throws MessagingException, IOException{
        // Arrange
        String to = "mave@example.fr";
        String subject = "happy birthday in Happy Town!";
        String body = "Vous allez recevoir un cadeau";

        // Act
        mailProvider.notifier(to, subject, body);
        // Assert
        assertThat(smtpServer.isRunning()).isTrue();
        assertThat(smtpServer.rejectedMails()).isEmpty();
        EmailModel email = smtpServer.mailBox().get(0);

        assertThat(email.getFrom()).isEqualTo("mairie@happytown.com");
        assertThat(email.getTo()).isEqualTo(to);
        assertThat(email.getSubject()).isEqualTo(subject);
        assertThat(email.getEmailStr()).contains(body);
    }

}