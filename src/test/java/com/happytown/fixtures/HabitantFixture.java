package com.happytown.fixtures;

import com.happytown.core.entities.Habitant;

import java.time.LocalDate;
import java.util.UUID;

public class HabitantFixture {

    public static Habitant aHabitant_sansCadeau() {
        String id = UUID.randomUUID().toString();
        String nom = "Carin";
        String prenom = "Marie";
        String adressePostale = "12 rue des Lilas";
        String email = "marie.carin@example.fr";
        LocalDate dateNaissance = LocalDate.of(1980, 10, 8);
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);

        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }

    public static Habitant aHabitant_avecCadeau() {
        String id = UUID.randomUUID().toString();
        String nom = "Carin";
        String prenom = "Marie";
        String adressePostale = "12 rue des Lilas";
        String email = "marie.carin@example.fr";
        String cadeauAttribue = "Plateau pour canapé (Montant : 24.90€ - Référence : aa23c026)";
        LocalDate dateNaissance = LocalDate.of(1980, 10, 8);
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);
        LocalDate dateAttributionCadeau = LocalDate.of(2016, 12, 1);

        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, cadeauAttribue, dateAttributionCadeau);
    }


}
