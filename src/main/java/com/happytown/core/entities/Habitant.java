package com.happytown.core.entities;

import java.time.LocalDate;

public class Habitant {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    private LocalDate dateArriveeCommune;
    private String adressePostale;
    private String cadeauOffert;
    private LocalDate dateAttributionCadeau;

    public Habitant(String id, String nom, String prenom, String email, LocalDate dateNaissance, LocalDate dateArriveeCommune, String adressePostale, String cadeauOffert, LocalDate dateAttributionCadeau) {
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

    public String getCadeauOffert() {
        return cadeauOffert;
    }

    public LocalDate getDateAttributionCadeau() {
        return dateAttributionCadeau;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setCadeauOffert(String cadeauOffert) {
        this.cadeauOffert = cadeauOffert;
    }

    public void setDateAttributionCadeau(LocalDate dateAttributionCadeau) {
        this.dateAttributionCadeau = dateAttributionCadeau;
    }

    @Override
    public String toString() {
        return "Habitant{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", dateArriveeCommune=" + dateArriveeCommune +
                ", adressePostale='" + adressePostale + '\'' +
                ", cadeauOffert='" + cadeauOffert + '\'' +
                ", dateAttributionCadeau=" + dateAttributionCadeau +
                '}';
    }

    public String getId() {
        return id;
    }

    public LocalDate getDateArriveeCommune() {
        return dateArriveeCommune;
    }

    public String getAdressePostale() {
        return adressePostale;
    }
}
