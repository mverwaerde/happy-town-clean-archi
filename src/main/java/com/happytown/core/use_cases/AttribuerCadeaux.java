package com.happytown.core.use_cases;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.Habitant;
import com.happytown.core.entities.TrancheAge;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Component
public class AttribuerCadeaux {

    private final HabitantProvider habitantProvider;
    private final Random random;
    private final NotificationProvider notificationProvider;
    private final CadeauByTrancheAgeProvider cadeauByTrancheAgeProvider;

    public AttribuerCadeaux(HabitantProvider habitantProvider, NotificationProvider notificationProvider, CadeauByTrancheAgeProvider cadeauByTrancheAgeProvider) {
        this.habitantProvider = habitantProvider;
        this.notificationProvider = notificationProvider;
        this.cadeauByTrancheAgeProvider = cadeauByTrancheAgeProvider;
        random = new Random();
    }

    public void execute(String fileName, LocalDate dateCourante) throws MessagingException {

        Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge = cadeauByTrancheAgeProvider.getCadeaux();
        List<Habitant> habitantsEligibles = habitantProvider.getElligiblesCadeaux(dateCourante.minusYears(1));
        List<Habitant> habitantsAttributionCadeau = new ArrayList<>();

        for (Habitant habitant : habitantsEligibles) {
            Optional<TrancheAge> trancheAge = getTrancheAgeCadeau(dateCourante, habitant, cadeauxByTrancheAge.keySet());
            if (trancheAge.isPresent()) {
                List<Cadeau> cadeauxPossibles = cadeauxByTrancheAge.get(trancheAge.get());
                Cadeau randomCadeau = cadeauxPossibles.get(random.nextInt(cadeauxPossibles.size()));
                envoiMessage(habitant, randomCadeau);
                habitant.setCadeauOffert(randomCadeau.getDetail());
                habitant.setDateAttributionCadeau(dateCourante);
                habitantProvider.save(habitant);
                habitantsAttributionCadeau.add(habitant);
            }
        }
        envoiMessageSyntheseCadeauxJournee(habitantsAttributionCadeau, dateCourante);
    }

    private Optional<TrancheAge> getTrancheAgeCadeau(LocalDate dateCourante, Habitant habitant, Set<TrancheAge> trancheAges) {
        Optional<TrancheAge> optTrancheAge = Optional.empty();
        Integer ageHabitant = Period.between(habitant.getDateNaissance(), dateCourante).getYears();
        for (TrancheAge trancheAge : trancheAges) {
            if (ageHabitant >= trancheAge.getAgeMin() && ageHabitant < trancheAge.getAgeMax()) {
                optTrancheAge = Optional.of(trancheAge);
            }
        }
        return optTrancheAge;
    }

    private void envoiMessage(Habitant habitant, Cadeau randomCadeau) throws MessagingException {
        String subject = "Happy Birthday in HappyTown!";
        String beneficiaire = habitant.getEmail();
        String body = createBodyMessageHabitant(habitant, randomCadeau);
        envoiMail(subject, beneficiaire, body);
    }

    private String createBodyMessageHabitant(Habitant habitant, Cadeau randomCadeau) {
        String body = "Bonjour " + habitant.getPrenom() + " " + habitant.getNom() + ",";
        body += "\n\nFélicitations, pour fêter votre premier anniversaire dans notre merveilleuse ville HappyTown, la mairie a le plaisir de vous offrir un cadeau de bienvenue.";
        body += "\n\nVotre cadeau est : " + randomCadeau.getDetail();
        body += "\n\nL'équipe HappyTown";
        return body;
    }

    private void envoiMessageSyntheseCadeauxJournee(List<Habitant> habitantsAttributionCadeau, LocalDate dateCourante) throws MessagingException {
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

    private void envoiMail(String subject, String beneficiaire, String body) throws MessagingException {
        notificationProvider.notifier(beneficiaire, subject, body);
    }

}
