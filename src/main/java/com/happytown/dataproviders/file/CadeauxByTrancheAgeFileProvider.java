package com.happytown.dataproviders.file;

import com.happytown.core.entities.Cadeau;
import com.happytown.core.entities.TrancheAge;
import com.happytown.core.entities.TrancheAgeComparator;
import com.happytown.core.use_cases.CadeauByTrancheAgeProvider;
import com.happytown.core.use_cases.CadeauxByTrancheAgeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class CadeauxByTrancheAgeFileProvider implements CadeauByTrancheAgeProvider {

    @Value(("${file.cadeauxByTrancheAge.path}"))
    private String filePath;


    @Override
    public Map<TrancheAge, List<Cadeau>> getCadeaux() throws CadeauxByTrancheAgeException {
        TrancheAgeComparator trancheAgeComparator = new TrancheAgeComparator();
        Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge = new TreeMap<>(trancheAgeComparator);
        try {
            Files.lines(Path.of(filePath))
                    .skip(1)
                    .forEach(line -> addLine(line, cadeauxByTrancheAge));
        } catch (IOException e) {
            throw new CadeauxByTrancheAgeException("Le chemin du fichier est errroné "+filePath, e);
        }

        return cadeauxByTrancheAge;
    }

    private void addLine(String line , Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge) {
        String[] cadeauData = line.split(",");
        String reference = cadeauData[0];
        String description = cadeauData[1];
        double montantDouble = Double.valueOf(cadeauData[2]);
        BigDecimal montant = BigDecimal.valueOf(montantDouble);
        String[] trancheAgeData = cadeauData[3].split("-");
        TrancheAge trancheAge = new TrancheAge(Integer.valueOf(trancheAgeData[0]), Integer.valueOf(trancheAgeData[1]));
        if (!cadeauxByTrancheAge.containsKey(trancheAge)) {
            cadeauxByTrancheAge.put(trancheAge, new ArrayList<>());
        }
        Cadeau cadeau = new Cadeau(reference, description, montant, trancheAge);
        cadeauxByTrancheAge.get(trancheAge).add(cadeau);
    }
}
