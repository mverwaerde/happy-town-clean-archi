package com.happytown.fixtures;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.TrancheAge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CadeauxFixture {

    private static TrancheAge TRANCHE_AGE_30_40 = new TrancheAge(30,40);


    public static List<Cadeau> cadeauxTrancheAge30_40 = new ArrayList<>() {{
        add(new Cadeau("aa23c026","Plateau pour canapé", BigDecimal.valueOf(24.90), TRANCHE_AGE_30_40));
        add(new Cadeau("6a64d9e7","Le mug qui touille tout seul",BigDecimal.valueOf(19.90), TRANCHE_AGE_30_40));
        add(new Cadeau("861d1d35","Gourde de voyage",BigDecimal.valueOf(9.99), TRANCHE_AGE_30_40));
        add(new Cadeau("37c88b3c","Porte stylo et bloc notes",BigDecimal.valueOf(12.60), TRANCHE_AGE_30_40));
        add(new Cadeau("d9b68019","Service à café", BigDecimal.valueOf(29.90), TRANCHE_AGE_30_40));
    }};
}
