package com.happytown.entrypoints.job;

import com.happytown.core.use_cases.AttribuerCadeaux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;

@Configuration
@EnableScheduling
public class AttributionCadeauJob {

    private final AttribuerCadeaux attribuerCadeaux;

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributionCadeauJob.class);

    public AttributionCadeauJob(AttribuerCadeaux attribuerCadeaux) {
        this.attribuerCadeaux = attribuerCadeaux;
    }

    @Scheduled(cron = "0 0/2 * * * *")
    public void execute() throws MessagingException {
        LOGGER.info("Start Task execute");
        attribuerCadeaux.execute();
        LOGGER.info("End Task execute");
    }
}
