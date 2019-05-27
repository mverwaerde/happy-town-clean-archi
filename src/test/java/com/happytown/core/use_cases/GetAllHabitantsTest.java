package com.happytown.core.use_cases;

import com.happytown.core.entities.Habitant;
import com.happytown.fixtures.HabitantFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllHabitantsTest {

    @InjectMocks
    private GetAllHabitants getAllHabitants;

    @Mock
    private HabitantProvider provider;

    @Test
    public void shouldreturnAllHabitant() {
        // Arrange
        Habitant habitant = HabitantFixture.aHabitant();
        List<Habitant> habitants = List.of(habitant);
        Mockito.doReturn(habitants).when(provider.getAll());

        // Act
        List<Habitant> results = getAllHabitants.execute();
        // Assert
        Assertions.assertThat(results).containsExactly(habitant);
    }

}