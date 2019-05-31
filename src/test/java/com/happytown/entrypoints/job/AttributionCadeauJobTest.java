package com.happytown.entrypoints.job;

import com.happytown.core.use_cases.AttribuerCadeaux;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class AttributionCadeauJobTest {

    @InjectMocks
    private AttributionCadeauJob attributionCadeauJob;

    @Mock
    private AttribuerCadeaux attribuerCadeaux;

    @Test
    public void shouldExecuteAttribuerCadeaux() throws IOException, MessagingException {
        // Act
        attributionCadeauJob.execute();
        // Assert
        Mockito.verify(attribuerCadeaux).execute();
    }
}