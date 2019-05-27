package com.happytown.fixtures;

import com.happytown.dataproviders.database.HabitantJpa;

import java.time.LocalDate;
import java.util.UUID;

public class HabitantJpaFixture {

    public static HabitantJpa aHabitantJpaSansCadeau() {
        HabitantJpa habitantJpa = new HabitantJpa();
        habitantJpa.setId(UUID.randomUUID().toString());
        habitantJpa.setNom("Carin");
        habitantJpa.setPrenom("Marie");
        habitantJpa.setEmail("marie.carin@example.fr");
        habitantJpa.setDateNaissance(LocalDate.of(1980, 10, 8));
        habitantJpa.setDateArriveeCommune(LocalDate.of(2016, 12, 1));
        habitantJpa.setAdressePostale("12 rue des Lilas");
        return habitantJpa;
    }
}
