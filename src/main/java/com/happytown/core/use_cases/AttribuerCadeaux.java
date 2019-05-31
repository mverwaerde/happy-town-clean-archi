package com.happytown.core.use_cases;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.Habitant;
import com.happytown.core.entities.TrancheAge;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AttribuerCadeaux {

    private final HabitantProvider habitantProvider;
    private final NotificationProvider notificationProvider;
    private final CadeauByTrancheAgeProvider cadeauByTrancheAgeProvider;
    private final CadeauRandom cadeauRandom;

    private Clock clock;

    public AttribuerCadeaux(HabitantProvider habitantProvider, NotificationProvider notificationProvider, CadeauByTrancheAgeProvider cadeauByTrancheAgeProvider, Clock clock, CadeauRandom cadeauRandom) {
        this.habitantProvider = habitantProvider;
        this.notificationProvider = notificationProvider;
        this.cadeauByTrancheAgeProvider = cadeauByTrancheAgeProvider;
        this.clock = clock;
        this.cadeauRandom = cadeauRandom;
    }

    public void execute() {

        Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge = cadeauByTrancheAgeProvider.getCadeaux();
        LocalDate dateCourante = LocalDate.now(clock);
        List<Habitant> habitantsEligibles = habitantProvider.getElligiblesCadeaux(dateCourante.minusYears(1));

        habitantsEligibles.stream()
                .forEach(habitant -> attributionCadeau(cadeauxByTrancheAge, dateCourante, habitant));

        List<Habitant> habitantsAttributionCadeau =
                habitantsEligibles.stream()
                        .filter(Habitant::hasCadeau)
                        .collect(Collectors.toList());

        habitantsAttributionCadeau.forEach(habitant -> {
            envoiMessage(habitant);
            habitantProvider.save(habitant);
        });

        envoiMessageSyntheseCadeauxJournee(habitantsAttributionCadeau, dateCourante);
    }

    private void attributionCadeau(Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge, LocalDate dateCourante, Habitant habitant) {
        Optional<TrancheAge> trancheAge = getTrancheAgeHabitant(dateCourante, habitant, cadeauxByTrancheAge.keySet());
        List<Cadeau> cadeauxPossibles = cadeauxByTrancheAge.get(trancheAge.get());
        Cadeau randomCadeau = cadeauRandom.get(cadeauxPossibles);
        habitant.attribuerCadeau(randomCadeau.getDetail(), dateCourante);
    }

    private Optional<TrancheAge> getTrancheAgeHabitant(LocalDate dateCourante, Habitant habitant, Set<TrancheAge> trancheAges) {
        Integer ageHabitant = Period.between(habitant.getDateNaissance(), dateCourante).getYears();
        return trancheAges.stream().filter(trancheAge -> ageHabitant >= trancheAge.getAgeMin() && ageHabitant < trancheAge.getAgeMax())
                .findFirst();

    }

    private void envoiMessage(Habitant habitant) {
        String subject = "Happy Birthday in HappyTown!";
        String beneficiaire = habitant.getEmail();
        String body = createBodyMessageHabitant(habitant, habitant.getCadeauOffert());
        envoiMail(subject, beneficiaire, body);
    }

    private String createBodyMessageHabitant(Habitant habitant, String cadeauOffert) {
        String body = "Bonjour " + habitant.getPrenom() + " " + habitant.getNom() + ",";
        body += "\n\nFélicitations, pour fêter votre premier anniversaire dans notre merveilleuse ville HappyTown, la mairie a le plaisir de vous offrir un cadeau de bienvenue.";
        body += "\n\nVotre cadeau est : " + cadeauOffert;
        body += "\n\nL'équipe HappyTown";
        return body;
    }

    private void envoiMessageSyntheseCadeauxJournee(List<Habitant> habitantsAttributionCadeau, LocalDate dateCourante) {
        if (!habitantsAttributionCadeau.isEmpty()) {
            String subject = String.format("%1$td/%1$tm/%1$tY", dateCourante) + " - Synthese des cadeaux pour envoi";
            String beneficiaire = "mairie+service-cadeau@happytown.com";
            String body = createMessageBodySyntheseCadeauxJournee(habitantsAttributionCadeau);
            envoiMail(subject, beneficiaire, body);
        }
    }

    private String createMessageBodySyntheseCadeauxJournee(List<Habitant> habitantsAttributionCadeau) {
        String body = "Bonjour,";
        body += "\n\nVoici la liste récapitulative des cadeaux du jour : ";
        for (Habitant habitantAttributionCadeau : habitantsAttributionCadeau) {
            body += " \n* " + habitantAttributionCadeau.getPrenom() + " " + habitantAttributionCadeau.getNom() + " : " + habitantAttributionCadeau.getCadeauOffert();
        }
        body += "\n\nMerci!";
        return body;
    }

    private void envoiMail(String subject, String beneficiaire, String body) {
        notificationProvider.notifier(beneficiaire, subject, body);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

}
