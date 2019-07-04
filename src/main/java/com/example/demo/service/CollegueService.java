package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.demo.entite.Collegue;
import com.example.demo.exception.CollegueInvalideException;
import com.example.demo.exception.CollegueNonTrouverException;

public class CollegueService {

    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {
        // TODO alimenter data avec des données fictives
        // Pour générer un matricule : `UUID.randomUUID().toString()`
        String mat1 = UUID.randomUUID().toString();
        String mat2 = UUID.randomUUID().toString();
        String mat3 = UUID.randomUUID().toString();
        data.put(mat1, new Collegue(mat1, "Durand", "Amandine", "amandine.durand@societe.com",
                LocalDate.of(1991, 9, 21), "https://randomuser.me/api/portraits/women/76.jpg"));
        data.put(mat2, new Collegue(mat2, "Robert", "Clement", "clement.robert@societe.com", LocalDate.of(1992, 11, 11),
                "https://randomuser.me/api/portraits/men/76.jpg"));
        data.put(mat3, new Collegue(mat3, "Leroy", "Yoann", "yoann.leroy@societe.com", LocalDate.of(1987, 1, 14),
                "https://randomuser.me/api/portraits/men/75.jpg"));
    }

    public List<Collegue> rechercherParNom(String nomRecherche) {
        // TODO retourner une liste de collègues dont le nom est fourni
        List<Collegue> collegues = new ArrayList<Collegue>();
        for (Map.Entry<String, Collegue> collegue : data.entrySet()) {
            if (nomRecherche.equals(collegue.getValue().getNom())) {
                collegues.add(collegue.getValue());
            }
        }
        return collegues;
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouverException {
        // TODO retourner le collègue dont le matricule est fourni
        if (!data.containsKey(matriculeRecherche))
            throw new CollegueNonTrouverException();

        // TODO retourner une exception `CollegueNonTrouveException` (à créer)
        // si le matricule ne correspond à aucun collègue

        return data.get(matriculeRecherche);
    }

    public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
        // TODO Vérifier que le nom et les prenoms ont chacun au moins 2 caractères
        // TODO Vérifier que l'email a au moins 3 caractères et contient `@`
        // TODO Vérifier que la photoUrl commence bien par `http`
        // TODO Vérifier que la date de naissance correspond à un age >= 18
        // TODO Si une des règles ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`.

        // TODO générer un matricule pour ce collègue (`UUID.randomUUID().toString()`)

        // TODO Sauvegarder le collègue
        if ((collegueAAjouter.getNom().trim().length() >= 2) && (collegueAAjouter.getPrenom().trim().length() >= 2)
                && (collegueAAjouter.getEmail().trim().length() >= 3 && collegueAAjouter.getEmail().contains("@"))
                && (collegueAAjouter.getPhotoUrl().startsWith("http"))
                && (collegueAAjouter.getDateDeNaissance().getYear() - LocalDate.now().getYear()) >= 18) {

            collegueAAjouter.setMatricule(UUID.randomUUID().toString());
            data.put(collegueAAjouter.getMatricule(), collegueAAjouter);
            return collegueAAjouter;

        }
        throw new CollegueInvalideException();
    }

}
