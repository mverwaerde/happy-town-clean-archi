package com.happytown.dataproviders.database;

import com.happytown.core.entities.Habitant;
import com.happytown.core.use_cases.HabitantProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HabitantDatabaseProvider implements HabitantProvider {

    HabitantJpaRepository repository;

    public List<Habitant> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toHabitant)
                .collect(Collectors.toList());

    }

    @Override
    public List<Habitant> getElligiblesCadeaux(LocalDate dateArriveeCommune) {
        return repository.findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsNullAndDateAttributionCadeauIsNullOrderByDateArriveeCommune(dateArriveeCommune)
                .stream()
                .map(this::toHabitant)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Habitant habitant) {
        repository.save(toHabitantJpa(habitant));
    }

    private HabitantJpa toHabitantJpa(Habitant habitant) {
        String id = habitant.getId();
        String nom = habitant.getNom();
        String prenom = habitant.getPrenom();
        String email = habitant.getEmail();
        String adressePostale = habitant.getAdressePostale();
        LocalDate dateNaissance = habitant.getDateNaissance();
        LocalDate dateArriveeCommune = habitant.getDateArriveeCommune();
        String cadeauOffert = habitant.getCadeauOffert();
        LocalDate dateAttributionCadeau = habitant.getDateAttributionCadeau();

        HabitantJpa habitantJpa = new HabitantJpa(id,nom,prenom,email,dateNaissance,dateArriveeCommune,adressePostale,cadeauOffert,dateAttributionCadeau);

        return habitantJpa;
    }

    private Habitant toHabitant(HabitantJpa habitantJpa) {
        String id = habitantJpa.getId();
        String nom = habitantJpa.getNom();
        String prenom = habitantJpa.getPrenom();
        String email = habitantJpa.getEmail();
        String adressePostale = habitantJpa.getAdressePostale();
        LocalDate dateNaissance = habitantJpa.getDateNaissance();
        LocalDate dateArriveeCommune = habitantJpa.getDateArriveeCommune();
        String cadeauOffert = habitantJpa.getCadeauOffert();
        LocalDate dateAttributionCadeau = habitantJpa.getDateAttributionCadeau();

        Habitant habitant = new Habitant(id,nom,prenom,email,dateNaissance,dateArriveeCommune,adressePostale,cadeauOffert,dateAttributionCadeau);

        return habitant;
    }
}
