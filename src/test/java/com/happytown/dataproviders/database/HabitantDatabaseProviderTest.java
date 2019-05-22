package com.happytown.dataproviders.database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HabitantDatabaseProviderTest {

    @InjectMocks
    private HabitantDatabaseProvider provider;

    @Mock
    private HabitantJpaRepository repository;

    @Test
    public void shouldReturnAllHabitants(){
        HabitantJpa habitantJpa = new HabitantJpa(
                "id",
                "Verwaerde",
                "Marine",
                "mave@octo.com",
                LocalDate.of(1990,10,22),
                LocalDate.of(2019,01,02),
                "15 rue quelque chose",
                null,
                null
        );
        List<HabitantJpa> habitants = List.of(habitantJpa);

        Mockito.doReturn(habitants).when(repository).findAll();

        List<HabitantJpa> allHabitants = provider.getAllHabitants();

        Assertions.assertThat(habitants).containsExactly(habitantJpa);

    }

}