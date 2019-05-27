package com.happytown.dataproviders.database;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "habitant")
public class HabitantJpa {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    private LocalDate dateArriveeCommune;
    private String adressePostale;
    private String cadeauOffert;
    private LocalDate dateAttributionCadeau;

    public HabitantJpa(String id, String nom, String prenom, String email, LocalDate dateNaissance, LocalDate dateArriveeCommune, String adressePostale, String cadeauOffert, LocalDate dateAttributionCadeau) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.dateArriveeCommune = dateArriveeCommune;
        this.adressePostale = adressePostale;
        this.cadeauOffert = cadeauOffert;
        this.dateAttributionCadeau = dateAttributionCadeau;
    }

    public HabitantJpa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateArriveeCommune() {
        return dateArriveeCommune;
    }

    public void setDateArriveeCommune(LocalDate dateArriveeCommune) {
        this.dateArriveeCommune = dateArriveeCommune;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getCadeauOffert() {
        return cadeauOffert;
    }

    public void setCadeauOffert(String cadeauOffert) {
        this.cadeauOffert = cadeauOffert;
    }

    public LocalDate getDateAttributionCadeau() {
        return dateAttributionCadeau;
    }

    public void setDateAttributionCadeau(LocalDate dateAttributionCadeau) {
        this.dateAttributionCadeau = dateAttributionCadeau;
    }
}
