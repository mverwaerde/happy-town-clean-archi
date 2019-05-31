package com.happytown.core.use_cases;

import com.happytown.core.entities.Cadeau;
import com.happytown.fixtures.CadeauxFixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CadeauRandomTest {

    private CadeauRandom cadeauRandom = new CadeauRandom();

    @Test
    public void shouldReturnGourde_whenInCadeauTrancheAge30_40() {
        // Arrange
        List<Cadeau> cadeauxTrancheAge30_40 = CadeauxFixture.cadeauxTrancheAge30_40;

        // Act
        Cadeau cadeau = cadeauRandom.get(cadeauxTrancheAge30_40);
        // Assert

        assertThat(cadeau).isIn(cadeauxTrancheAge30_40);
    }

}