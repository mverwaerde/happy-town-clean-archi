package com.happytown.dataproviders.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitantJpaRepository extends JpaRepository<HabitantJpa, String> {

    List<HabitantJpa> findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsNullAndDateAttributionCadeauIsNullOrderByDateArriveeCommune(LocalDate nowMinusOneYear);
}
