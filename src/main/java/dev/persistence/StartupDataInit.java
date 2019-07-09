package dev.persistence;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.entite.Collegue;

@Component
public class StartupDataInit {

	@Autowired
	CollegueRepository collegueRepo;

	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		// TODO insérer des collègues en base de données

		int count = 0;
		String noms[] = { "LEROY", "DURAND", "ROBERT", "PETIT", "MOREAU", "DUBOIS", "DUPOND", "BERANRD", "CASSAN",
				"OLLIVIER" };
		String prenoms[] = { "Clement", "Yoann", "Erwan", "Julien", "Amandine", "Marion", "Margaux", "Martin", "Adrien",
				"Robin" };

		while (count != 10) {
			String matricule = UUID.randomUUID().toString();
			String nom = noms[(int) (Math.random() * (9 - 0))];
			String prenom = prenoms[(int) (Math.random() * (9 - 0))];
			String email = prenom.toLowerCase() + nom.toLowerCase() + "@mail.fr";
			int annee = (int) (1970 + Math.random() * (40));
			int mois = 1 + (int) (Math.random() * (12 - 1));
			int jour = 1 + (int) (Math.random() * (28 - 1));
			String photo = "https://upload.wikimedia.org/wikipedia/commons/0/09/Man_Silhouette.png";
			collegueRepo.save(new Collegue(matricule, nom, prenom, email, LocalDate.of(annee, mois, jour), photo));
			count++;
		}
	}
}
