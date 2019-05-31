package com.happytown.core.use_cases;

import com.happytown.core.entities.Cadeau;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CadeauRandom {

    public Cadeau get(List<Cadeau> cadeauxTrancheAge) {
        Random random = new Random();
        return cadeauxTrancheAge.get(random.nextInt(cadeauxTrancheAge.size()));
    }
}
