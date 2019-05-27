package com.happytown.dataproviders.database;

import com.happytown.core.entities.Habitant;
import com.happytown.fixtures.HabitantJpaFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
                "",
                null
        );

        List<HabitantJpa> habitants = List.of(habitantJpa);

        BDDMockito.doReturn(habitants).when(repository).findAll();

        List<Habitant> allHabitants = provider.getAll();

        Habitant expectedHabitant = new Habitant("id",
                "Verwaerde",
                "Marine",
                "mave@octo.com",
                LocalDate.of(1990,10,22),
                LocalDate.of(2019,01,02),
                "15 rue quelque chose",
                "",
                null);

        assertThat(allHabitants.get(0)).isEqualToComparingFieldByField(expectedHabitant);

    }

    @Test
    public void shouldReturnHabitantWithCadeau_whenHabitantHaveNotYetAcadeau() {
        // Arrange
        HabitantJpa habitantJpaSansCadeau = HabitantJpaFixture.aHabitantJpaSansCadeau();
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);
        BDDMockito.doReturn(List.of(habitantJpaSansCadeau)).when(repository).findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsNullAndDateAttributionCadeauIsNullOrderByDateArriveeCommune(dateArriveeCommune);

        // Act
        List<Habitant> elligiblesCadeaux = provider.getElligiblesCadeaux(dateArriveeCommune);
        // Assert

        assertThat(elligiblesCadeaux.get(0)).isEqualToComparingFieldByField(habitantJpaSansCadeau);

    }
}