package com.happytown.core.entities;

import java.math.BigDecimal;

public class Cadeau {


    private String reference;

    private String description;

    private BigDecimal montant;

    private TrancheAge trancheAge;

    public String getDetail() {
        return this.description + " " +
                "(Montant : " + this.montant + "€ - " +
                "Référence : " + this.reference + ")";

    }

}
