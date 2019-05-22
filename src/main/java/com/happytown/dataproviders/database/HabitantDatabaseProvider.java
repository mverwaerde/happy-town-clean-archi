package com.happytown.dataproviders.database;

import java.util.List;

public class HabitantDatabaseProvider {

    HabitantJpaRepository repository;

    public List<HabitantJpa> getAllHabitants() {
        return repository.findAll();

    }
}
