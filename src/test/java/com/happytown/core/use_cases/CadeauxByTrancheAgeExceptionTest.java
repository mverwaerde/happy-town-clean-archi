package com.happytown.core.use_cases;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.TrancheAge;
import com.happytown.dataproviders.file.CadeauxByTrancheAgeFileProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"file.cadeauxByTrancheAge.path=wrong_path.txt"})
class CadeauxByTrancheAgeFileProviderExceptionTest {

    @Autowired
    CadeauxByTrancheAgeFileProvider cadeauxByTrancheAgeFileProvider;

    @Test
    void get_shouldThrowCadeauxByTrancheAgeException() {
        Map<TrancheAge, List<Cadeau>> results = new TreeMap<>();
        try {
            // When
            results = cadeauxByTrancheAgeFileProvider.getCadeaux();
        } catch (CadeauxByTrancheAgeException e) {
            // Then
            assertThat(results).isEmpty();
            assertThat(e.getMessage()).isEqualTo("Le chemin du fichier est errroné wrong_path.txt");
            assertThat(e.getCause().toString()).isEqualTo("java.nio.file.NoSuchFileException: wrong_path.txt");
        }
    }
}