package com.happytown.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HabitantServiceTest {

    @InjectMocks
    HabitantService habitantService;
/*
    @Mock
    HabitantRepository habitantRepository;

    @Test
    void getAll_shouldReturnAllHabitants() {
        // Given
        List<Habitant> habitants = Lists.newArrayList(HabitantFixture.aHabitant());
        BDDMockito.doReturn(habitants).when(habitantRepository).findAll();

        // When
        List<Habitant> results = habitantService.getAll();

        // Then
        assertThat(results).containsExactlyElementsOf(habitants);
    }
*/
}