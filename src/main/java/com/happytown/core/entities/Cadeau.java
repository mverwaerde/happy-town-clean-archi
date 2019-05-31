package com.happytown.core.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Cadeau {


    private String reference;

    private String description;

    private BigDecimal montant;

    private TrancheAge trancheAge;

    public Cadeau(String reference, String description, BigDecimal montant, TrancheAge trancheAge) {
        this.reference = reference;
        this.description = description;
        this.montant = montant;
        this.trancheAge = trancheAge;
    }

    public String getDetail() {
        return this.description + " " +
                "(Montant : " + this.montant + "€ - " +
                "Référence : " + this.reference + ")";

    }

    @Override
    public String toString() {
        return "Cadeau{" +
                "reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                ", montant=" + montant +
                ", trancheAge=" + trancheAge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cadeau cadeau = (Cadeau) o;
        return Objects.equals(reference, cadeau.reference) &&
                Objects.equals(description, cadeau.description) &&
                Objects.equals(montant, cadeau.montant) &&
                Objects.equals(trancheAge, cadeau.trancheAge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, description, montant, trancheAge);
    }
}
