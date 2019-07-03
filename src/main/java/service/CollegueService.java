package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import entite.Collegue;

public class CollegueService {

    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {
        // TODO alimenter data avec des données fictives
        // Pour générer un matricule : `UUID.randomUUID().toString()`

        data.put("", new Collegue(UUID.randomUUID().toString(), "Durand", "Amandine", "amandine.durand@societe.com",
                LocalDate.of(1991, 9, 21), "photo.png"));
        data.put("", new Collegue(UUID.randomUUID().toString(), "Robert", "Clement", "clement.robert@societe.com",
                LocalDate.of(1992, 11, 11), "photo.png"));
        data.put("", new Collegue(UUID.randomUUID().toString(), "Leroy", "Yoann", "yoann.leroy@societe.com",
                LocalDate.of(1987, 1, 14), "photo.png"));
    }

    public List<Collegue> rechercherParNom(String nomRecherche) {
        // TODO retourner une liste de collègues dont le nom est fourni
        List<Collegue> collegues = new ArrayList<Collegue>();
        for (Map.Entry<String, Collegue> collegue : data.entrySet()) {
            if (nomRecherche.equals(collegue.getValue().getNom())){
                collegues.add(collegue.getValue());  
            }
        }
        return collegues;

    }
}
