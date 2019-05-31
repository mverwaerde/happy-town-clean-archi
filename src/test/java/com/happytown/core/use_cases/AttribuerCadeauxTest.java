package com.happytown.core.use_cases;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.Habitant;
import com.happytown.core.entities.TrancheAge;
import com.happytown.dataproviders.file.CadeauxByTrancheAgeFileProvider;
import com.happytown.fixtures.HabitantFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.happytown.fixtures.TrancheAgeFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttribuerCadeauxTest {

    @InjectMocks
    AttribuerCadeaux attribuerCadeaux;

    @Mock
    HabitantProvider habitantProvider;

    @Mock
    NotificationProvider notificationProvider;

    @Mock
    CadeauByTrancheAgeProvider cadeauByTrancheAgeProvider;

    @Mock
    CadeauRandom cadeauRandom;

    private static final String FILE_NAME = "src/main/resources/cadeaux.txt";
    private static Map<TrancheAge, List<Cadeau>> CADEAUX_BY_TRANCHE_AGE = new TreeMap<>();

    static {
        CadeauxByTrancheAgeFileProvider cadeauxByTrancheAgeFileProvider = new CadeauxByTrancheAgeFileProvider();
        try {
            Field pathFileCadeauxByTrancheAge = CadeauxByTrancheAgeFileProvider.class.getDeclaredField("filePath");
            pathFileCadeauxByTrancheAge.setAccessible(true);
            pathFileCadeauxByTrancheAge.set(cadeauxByTrancheAgeFileProvider, FILE_NAME);
            CADEAUX_BY_TRANCHE_AGE = cadeauxByTrancheAgeFileProvider.getCadeaux();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static List<Cadeau> CADEAU_TRANCHE_AGE_0_3 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_0_3);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_3_6 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_3_6);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_6_10 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_6_10);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_10_15 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_10_15);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_15_20 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_15_20);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_20_30 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_20_30);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_30_40 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_30_40);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_40_50 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_40_50);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_50_60 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_50_60);
    private static List<Cadeau> CADEAU_TRANCHE_AGE_60_150 = CADEAUX_BY_TRANCHE_AGE.get(TRANCHE_AGE_60_150);

    private static final LocalDate NOW = LocalDate.of(2018, 10, 1);
    private static final LocalDate NOW_MINUS_ONE_YEAR = LocalDate.of(2017, 10, 1);

    private static final String REF_CADEAUX_TRANCHE_AGE_0_3 = "70d73d02";
    private static final String REF_CADEAUX_TRANCHE_AGE_3_6 = "b3f83de3";
    private static final String REF_CADEAUX_TRANCHE_AGE_6_10 = "2ee03dac";
    private static final String REF_CADEAUX_TRANCHE_AGE_10_15 = "6890e222";
    private static final String REF_CADEAUX_TRANCHE_AGE_15_20 = "cae67bbb";
    private static final String REF_CADEAUX_TRANCHE_AGE_20_30 = "5bd74b84";
    private static final String REF_CADEAUX_TRANCHE_AGE_30_40 = "aa23c026";
    private static final String REF_CADEAUX_TRANCHE_AGE_40_50 = "dd1954e8";
    private static final String REF_CADEAUX_TRANCHE_AGE_50_60 = "f14f767d";
    private static final String REF_CADEAUX_TRANCHE_AGE_60_150 = "b9dcca0d";

    @BeforeEach
    public void setUp() {
        Clock clock = Clock.fixed(Instant.parse("2018-10-01T09:10:20.00Z"), ZoneId.of("Europe/Paris"));
        attribuerCadeaux.setClock(clock);
        doReturn(CADEAUX_BY_TRANCHE_AGE)
                .when(cadeauByTrancheAgeProvider)
                .getCadeaux();
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge0_3() {

        Habitant habitant = HabitantFixture.habitant_trancheAge0_3();

        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_0_3);


        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("elise.paron@example.fr", "Elise Paron", REF_CADEAUX_TRANCHE_AGE_0_3);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_0_3);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge3_6() {
        // Given
        Habitant habitant = HabitantFixture.habitant_trancheAge3_6();

        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_3_6);
        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("manon.giron@example.fr", "Manon Giron", REF_CADEAUX_TRANCHE_AGE_3_6);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_3_6);
    }


    @Test
    void attribuerCadeaux_habitantTrancheAge6_10() {
        Habitant habitant = HabitantFixture.habitant_trancheAge6_10();

        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_6_10);

        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("lucas.perraud@example.fr", "Lucas Perraud", REF_CADEAUX_TRANCHE_AGE_6_10);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_6_10);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge10_15() {
        // Given
        Habitant habitant = HabitantFixture.habitant_trancheAge10_15();
        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_10_15);
        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("etienne.leduc@example.fr", "Etienne Leduc", REF_CADEAUX_TRANCHE_AGE_10_15);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_10_15);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge15_20() {
        // Given
        Habitant habitant = HabitantFixture.habitant_trancheAge15_20();

        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_15_20);
        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("elodie.guilbaud@example.fr", "Elodie Guilbaud", REF_CADEAUX_TRANCHE_AGE_15_20);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_15_20);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge20_30() {

        Habitant habitant = HabitantFixture.habitant_trancheAge20_30();
        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_20_30);
        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("paul.newman@example.fr", "Paul Newman", REF_CADEAUX_TRANCHE_AGE_20_30);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_20_30);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge30_40() {

        Habitant habitant = HabitantFixture.habitant_trancheAge30_40();
        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_30_40);

        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("marie.carin@example.fr", "Marie Carin", REF_CADEAUX_TRANCHE_AGE_30_40);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_30_40);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge40_50() {


        Habitant habitant = HabitantFixture.habitant_trancheAge40_50();
        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_40_50);

        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("michel.dumond@example.fr", "Michel Dumond", REF_CADEAUX_TRANCHE_AGE_40_50);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_40_50);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge50_60() {

        Habitant habitant = HabitantFixture.habitant_trancheAge50_60();
        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_50_60);

        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("julien.avro@example.fr", "Julien Avro", REF_CADEAUX_TRANCHE_AGE_50_60);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_50_60);
    }

    @Test
    void attribuerCadeaux_habitantTrancheAge60_150() {

        Habitant habitant = HabitantFixture.habitant_trancheAge60_150();

        mockCadeaux(habitant, CADEAU_TRANCHE_AGE_60_150);

        // When
        attribuerCadeaux.execute();

        // Then
        verifyEmailsSent("yvette.pascalin@example.fr", "Yvette Pascalin", REF_CADEAUX_TRANCHE_AGE_60_150);
        verifyHabitantSaved(REF_CADEAUX_TRANCHE_AGE_60_150);
    }

    private void verifyEmailsSent(String destinataireHabitant, String nom, String refCadeau) {

        ArgumentCaptor<String> emailToCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailSubjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailBodyCaptor = ArgumentCaptor.forClass(String.class);


        verify(notificationProvider, times(2)).notifier(emailToCaptor.capture(), emailSubjectCaptor.capture(), emailBodyCaptor.capture());

        assertThat(emailToCaptor.getAllValues().get(0)).isEqualTo(destinataireHabitant);
        assertThat(emailSubjectCaptor.getAllValues().get(0)).isEqualTo("Happy Birthday in HappyTown!");
        assertThat(emailBodyCaptor.getAllValues().get(0)).contains(nom);
        assertThat(emailBodyCaptor.getAllValues().get(0)).contains(refCadeau);

        assertThat(emailToCaptor.getAllValues().get(1)).isEqualTo("mairie+service-cadeau@happytown.com");
        assertThat(emailSubjectCaptor.getAllValues().get(1)).isEqualTo("01/10/2018 - Synthese des cadeaux pour envoi");
        assertThat(emailBodyCaptor.getAllValues().get(1)).contains(nom);
        assertThat(emailBodyCaptor.getAllValues().get(1)).contains(refCadeau);
    }

    private void verifyHabitantSaved(String refCadeau) {
        ArgumentCaptor<Habitant> habitantArgumentCaptor = ArgumentCaptor.forClass(Habitant.class);
        verify(habitantProvider).save(habitantArgumentCaptor.capture());
        Habitant habitantSaved = habitantArgumentCaptor.getValue();
        assertThat(habitantSaved.getCadeauOffert()).containsPattern(refCadeau);
        assertThat(habitantSaved.getDateAttributionCadeau()).isEqualTo(NOW);
    }

    private void mockCadeaux(Habitant habitant, List<Cadeau> cadeauByTrancheAge) {
        doReturn(newArrayList(habitant))
                .when(habitantProvider)
                .getElligiblesCadeaux(NOW_MINUS_ONE_YEAR);

        doReturn(cadeauByTrancheAge.get(0))
                .when(cadeauRandom)
                .get(cadeauByTrancheAge);
    }
}

