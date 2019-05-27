package com.happytown.core.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CadeauTest {

    @Test
    void getDetail() {
        // Given
        TrancheAge trancheAge6_10 = new TrancheAge(6, 10);
        String reference = "dbe982da";
        String description = "Puissance 4 voyage";
        BigDecimal montant = BigDecimal.valueOf(7.99);

        Cadeau cadeau = new Cadeau(reference, description, montant, trancheAge6_10);
        // When
        String detail = cadeau.getDetail();

        // Then
        assertThat(detail).isEqualTo("Puissance 4 voyage (Montant : 7.99€ - Référence : dbe982da)");
    }
}
