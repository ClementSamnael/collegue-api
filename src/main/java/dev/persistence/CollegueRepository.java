package dev.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String>{

	public List<Collegue> findByNom(String nom);
	
	public Optional<Collegue> findByMatricule(String matriculeRecherche);

		
}
