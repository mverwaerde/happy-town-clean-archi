package com.happytown.entrypoints.rest;

import com.happytown.core.entities.Habitant;
import com.happytown.core.use_cases.GetAllHabitants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/habitants")
@Api(value = "API de gestion des habitants de Happy Town")
public class HabitantEndpoint {

    private final GetAllHabitants getAllHabitants;

    public HabitantEndpoint(GetAllHabitants getAllHabitants) {
        this.getAllHabitants = getAllHabitants;
    }

    @GetMapping
    @ApiOperation("Retourne la liste des habitants de Happy Town")
    public List<HabitantApi> getAllHabitants(){
        return getAllHabitants.execute()
                .stream()
                .map(this::toHabitantApi)
                .collect(Collectors.toList());
    }

    private HabitantApi toHabitantApi(Habitant habitant){
        String id = habitant.getId();
        String nom = habitant.getNom();
        String prenom = habitant.getPrenom();
        String email = habitant.getEmail();
        String adressePostale = habitant.getAdressePostale();
        LocalDate dateNaissance = habitant.getDateNaissance();
        LocalDate dateArriveeCommune = habitant.getDateArriveeCommune();
        String cadeauOffert = habitant.getCadeauOffert();
        LocalDate dateAttributionCadeau = habitant.getDateAttributionCadeau();

        HabitantApi habitantApi = new HabitantApi(id,nom,prenom,email,dateNaissance,dateArriveeCommune,adressePostale,cadeauOffert,dateAttributionCadeau);

        return habitantApi;
    }

}
