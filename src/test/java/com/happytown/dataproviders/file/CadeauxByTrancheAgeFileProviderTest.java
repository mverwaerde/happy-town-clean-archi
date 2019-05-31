package com.happytown.dataproviders.file;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.TrancheAge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CadeauxByTrancheAgeFileProviderTest {

    @Autowired
    private CadeauxByTrancheAgeFileProvider fileProvider;

    private static TrancheAge TRANCHE_AGE_30_40 = new TrancheAge(30,40);

    private static List<Cadeau> cadeauxTrancheAge30_40 = new ArrayList<>() {{
        add(new Cadeau("aa23c026","Plateau pour canapé",BigDecimal.valueOf(24.90), TRANCHE_AGE_30_40));
        add(new Cadeau("6a64d9e7","Le mug qui touille tout seul",BigDecimal.valueOf(19.90), TRANCHE_AGE_30_40));
        add(new Cadeau("861d1d35","Gourde de voyage",BigDecimal.valueOf(9.99), TRANCHE_AGE_30_40));
        add(new Cadeau("37c88b3c","Porte stylo et bloc notes",BigDecimal.valueOf(12.60), TRANCHE_AGE_30_40));
        add(new Cadeau("d9b68019","Service à café", BigDecimal.valueOf(29.90), TRANCHE_AGE_30_40));
    }};


    @Test
    public void shouldReturnCadeauxByTrancheAge() {
        // Act
        Map<TrancheAge, List<Cadeau>> cadeauxbyTrancheAge = fileProvider.getCadeaux();
        List<Cadeau> results = cadeauxbyTrancheAge.get(TRANCHE_AGE_30_40);

        Assertions.assertThat(results).containsAll(cadeauxTrancheAge30_40);
    }

}