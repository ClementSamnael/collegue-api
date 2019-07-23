package dev.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entite.Collegue;
import dev.entite.Photo;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouverException;
import dev.persistence.CollegueRepository;


@Service
public class CollegueService {

	@Autowired
	private CollegueRepository collRepository;
	
	private static final int TAILLE_EMAIL_MINIMUM = 3;
	private static final int TAILLE_PRENOM_MINIMUM = 2;
	private static final int TAILLE_NOM_MINIMUM = 2;
	private static final int AGE_MINIMUM = 18;

	public CollegueService() {

	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		return collRepository.findByNom(nomRecherche);
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouverException {
		Optional<Collegue> collegueOpt = collRepository.findByMatricule(matriculeRecherche);
		return collegueOpt.orElseThrow(() -> new CollegueNonTrouverException("Collegue non trouvé"));
	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
		if (collegueAAjouter.getNom().length() < TAILLE_NOM_MINIMUM
				|| collegueAAjouter.getPrenom().length() < TAILLE_PRENOM_MINIMUM
				|| collegueAAjouter.getEmail().length() < TAILLE_EMAIL_MINIMUM
				|| !collegueAAjouter.getEmail().contains("@") || !collegueAAjouter.getPhotoUrl().startsWith("http")
				|| Period.between(collegueAAjouter.getDateDeNaissance(), LocalDate.now()).getYears() < AGE_MINIMUM) {
			throw new CollegueInvalideException("Collegue invalide !");
		}
		collegueAAjouter.setMatricule(UUID.randomUUID().toString());
		return collRepository.save(collegueAAjouter);
	}

	public Collegue modifierEmail(String matricule, String email)
			throws CollegueInvalideException, CollegueNonTrouverException {
		Optional<Collegue> collegueOpt = collRepository.findByMatricule(matricule);
		Collegue collegue = collegueOpt.orElseThrow(() -> new CollegueNonTrouverException("Collegue non trouvé"));
		if (email.length() > TAILLE_EMAIL_MINIMUM || email.contains("@")) {
			collegue.setEmail(email);
			collRepository.save(collegue);
		} else {
			throw new CollegueInvalideException("Email invalide");
		}
		return collegue;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl)
			throws CollegueNonTrouverException, CollegueInvalideException {
		Optional<Collegue> collegueOpt = collRepository.findByMatricule(matricule);
		Collegue collegue = collegueOpt.orElseThrow(() -> new CollegueNonTrouverException("Collegue non trouvé"));
		if (photoUrl.startsWith("http")) {
			collegue.setPhotoUrl(photoUrl);
			collRepository.save(collegue);

		} else {
			throw new CollegueInvalideException("Photo invalide");
		}
		return collegue;
	}
	
	public List<Photo> getToutesLesPhotos(){
		List<Collegue> collegue = collRepository.findAll();
		return collegue.stream()
				.map(collegues -> new Photo(collegues.getMatricule(), collegues.getPhotoUrl()))
				.collect(Collectors.toList());
	}

	public Optional<Collegue> findByLogin(String login){
	    return collRepository.findByLogin(login);
	}
	
}
