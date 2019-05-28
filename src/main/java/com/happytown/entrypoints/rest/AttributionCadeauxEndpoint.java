package com.happytown.entrypoints.rest;

import com.happytown.core.use_cases.AttribuerCadeaux;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/attributionCadeaux")
@Api(value = "API permettant d'attribuer un cadeau aléatoire aux habitants de Happy Town arrivés depuis plus de 1 an")
public class AttributionCadeauxEndpoint {

    private final AttribuerCadeaux attribuerCadeaux;

    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 2525;

    public AttributionCadeauxEndpoint(AttribuerCadeaux attribuerCadeaux) {
        this.attribuerCadeaux = attribuerCadeaux;
    }

    @PostMapping
    @ApiOperation("Permet d'attribuer un cadeau aléatoire aux habitants de Happy Town arrivés depuis plus de 1 an")
    public void attribuerCadeaux() throws IOException, MessagingException {
        String fileName = "src/main/resources/cadeaux.txt";
        LocalDate now = LocalDate.now();
        attribuerCadeaux.execute(fileName, now, SMTP_HOST, SMTP_PORT);
    }

}
