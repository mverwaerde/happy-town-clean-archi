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

    public static Habitant habitant_trancheAge0_3() {
        String id = UUID.randomUUID().toString();
        String nom = "Paron";
        String prenom = "Elise";
        String adressePostale = "48 faubourg de la Plage";
        String email = "elise.paron@example.fr";
        LocalDate dateNaissance = LocalDate.of(2018, 6, 22);
        LocalDate dateArriveeCommune = LocalDate.of(2017, 5, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }

    public static Habitant habitant_trancheAge3_6() {
        String id = UUID.randomUUID().toString();
        String nom = "Giron";
        String prenom = "Manon";
        String adressePostale = "2 rue des Apotres";
        String email = "manon.giron@example.fr";
        LocalDate dateNaissance = LocalDate.of(2012, 10, 2);
        LocalDate dateArriveeCommune = LocalDate.of(2017, 5, 1);

        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }

    public static Habitant habitant_trancheAge6_10() {
        String id = UUID.randomUUID().toString();
        String nom = "Perraud";
        String prenom = "Lucas";
        String adressePostale = "17 boulevard des Capucines";
        String email = "lucas.perraud@example.fr";
        LocalDate dateNaissance = LocalDate.of(2011, 4, 4);
        LocalDate dateArriveeCommune = LocalDate.of(2017, 9, 10);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }


    public static Habitant habitant_trancheAge10_15() {
        String id = UUID.randomUUID().toString();
        String nom = "Leduc";
        String prenom = "Etienne";
        String adressePostale = "28 square du Bois Fleuri";
        String email = "etienne.leduc@example.fr";
        LocalDate dateNaissance = LocalDate.of(2006, 5, 14);
        LocalDate dateArriveeCommune = LocalDate.of(2017, 9, 10);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }

    public static Habitant habitant_trancheAge15_20() {
        String id = UUID.randomUUID().toString();
        String nom = "Guilbaud";
        String prenom = "Elodie";
        String adressePostale = "1 impasse du Cheval Blanc";
        String email = "elodie.guilbaud@example.fr";
        LocalDate dateNaissance = LocalDate.of(1998, 10, 2);
        LocalDate dateArriveeCommune = LocalDate.of(2017, 10, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }

    public static Habitant habitant_trancheAge20_30() {
        String id = UUID.randomUUID().toString();
        String nom = "Newman";
        String prenom = "Paul";
        String adressePostale = "14 chemin Edmond Rostand";
        String email = "paul.newman@example.fr";
        LocalDate dateNaissance = LocalDate.of(1998, 10, 1);
        LocalDate dateArriveeCommune = LocalDate.of(2017, 10, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);
    }

    public static Habitant habitant_trancheAge30_40() {
        String id = UUID.randomUUID().toString();
        String nom = "Carin";
        String prenom = "Marie";
        String adressePostale = "12 rue des Lilas";
        String email = "marie.carin@example.fr";
        LocalDate dateNaissance = LocalDate.of(1980, 10, 8);
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);

    }

    public static Habitant habitant_trancheAge40_50() {
        String id = UUID.randomUUID().toString();
        String nom = "Dumond";
        String prenom = "Michel";
        String adressePostale = "18 square de Crusoe";
        String email = "michel.dumond@example.fr";
        LocalDate dateNaissance = LocalDate.of(1970, 10, 25);
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);

    }

    public static Habitant habitant_trancheAge50_60() {
        String id = UUID.randomUUID().toString();
        String nom = "Avro";
        String prenom = "Julien";
        String adressePostale = "15 rue Apigi";
        String email = "julien.avro@example.fr";
        LocalDate dateNaissance = LocalDate.of(1965, 6, 25);
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);

    }

    public static Habitant habitant_trancheAge60_150() {
        String id = UUID.randomUUID().toString();
        String nom = "Pascalin";
        String prenom = "Yvette";
        String adressePostale = "34 rue des Koali";
        String email = "yvette.pascalin@example.fr";
        LocalDate dateNaissance = LocalDate.of(1958, 2, 14);
        LocalDate dateArriveeCommune = LocalDate.of(2016, 12, 1);
        return new Habitant(id, nom, prenom, email, dateNaissance, dateArriveeCommune, adressePostale, null, null);

    }
}
