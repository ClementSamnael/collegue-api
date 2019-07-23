package dev.persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.entite.Collegue;
import dev.entite.Login;

@Component
public class StartupDataInit {

	@Autowired
	CollegueRepository collegueRepo;
	
	@Autowired
	LoginRepository loginRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		int count = 0;
		String noms[] = { "LEROY", "DURAND", "ROBERT", "PETIT", "MOREAU", "DUBOIS", "DUPOND", "BERNARD", "CASSAN",
				"OLLIVIER" };
		String prenoms[] = { "Clement", "Yoann", "Erwan", "Julien", "Amandine", "Marion", "Margaux", "Martin", "Adrien",
				"Robin" };

		while (count != 10) {
			String matricule = UUID.randomUUID().toString();
			String nom = noms[(int) (Math.random() * (9 - 0))];
			String prenom = prenoms[(int) (Math.random() * (9 - 0))];
			String email = prenom.toLowerCase() + nom.toLowerCase() + "@mail.fr";
			int annee = (int) (1970 + Math.random() * (30));
			int mois = 1 + (int) (Math.random() * (12 - 1));
			int jour = 1 + (int) (Math.random() * (28 - 1));
			String photo = "https://vignette.wikia.nocookie.net/jojo/images/6/6a/Star_Platinum_%28Stardust_Crusaders%2C_manga%29.png/revision/latest?cb=20180104204918&path-prefix=fr";
			collegueRepo.save(new Collegue(matricule, nom, prenom, email, LocalDate.of(annee, mois, jour), photo));
			count++;
		}
		
		//Login ADMIN
		Login logAdmin = new Login();
		logAdmin.setLogin("logUser");
		logAdmin.setMotDePasse(passwordEncoder.encode("passAdmin"));
		logAdmin.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
		
		loginRepo.save(logAdmin);
		
		Collegue collegue = new Collegue();

		collegue.setMatricule(UUID.randomUUID().toString());
		collegue.setNom("Admin");
		collegue.setPrenom("Admin");
		collegue.setEmail("admin@mail.fr");
		collegue.setDateDeNaissance(LocalDate.of(1992,06,11));
		collegue.setPhotoUrl("https://vignette.wikia.nocookie.net/jjba/images/7/7c/StarPlatinum.jpg/revision/latest/scale-to-width-down/310?cb=20130809012821&path-prefix=fr");
        
        //Login USER
        Login logUser = new Login();
        logUser.setLogin("logUser");
        logUser.setMotDePasse(passwordEncoder.encode("passUser"));
        logUser.setRoles(Arrays.asList("ROLE_USER"));
        
        collegueRepo.save(collegue);
        loginRepo.save(logUser);
	}
}
