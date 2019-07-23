package dev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Collegue;
import dev.entite.Photo;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouverException;
import dev.service.CollegueService;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(path = "/collegues")
public class CollegueController {

    @Autowired
    private CollegueService lesCollegues;

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET)
    public List<String> getRechercherCollegue(@RequestParam String nomCollegue) {

        List<String> colleguesMa = new ArrayList<>();
        List<Collegue> collegues = lesCollegues.rechercherParNom(nomCollegue);
        for (Collegue c : collegues) {
            colleguesMa.add(c.getMatricule());
        }
        return colleguesMa;
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET, path = "/{matricule}")
    public Collegue rechercherCollegueParMatricule(@PathVariable String matricule) throws CollegueNonTrouverException {
        return lesCollegues.rechercherParMatricule(matricule);
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET, path = "/photos")
    public List<Photo> getPhoto() {
        return lesCollegues.getToutesLesPhotos();
    }
    
    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET, path = "/me")
    public Optional<Collegue> getMe(){
        return lesCollegues.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    // Role des Admins
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> ajouterCollegue(@RequestBody Collegue collegue) throws CollegueInvalideException {
        Collegue col = lesCollegues.ajouterUnCollegue(collegue);
        return ResponseEntity.status(HttpStatus.CREATED).body(col.getMatricule());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.PATCH, path = "/{matricule}")
    public Collegue modifierCollegue(@PathVariable String matricule, @RequestBody Collegue collegue) {
        if (collegue.getEmail() != null && !collegue.getEmail().equals("")) {
            lesCollegues.modifierEmail(matricule, collegue.getEmail());
        }
        if (collegue.getPhotoUrl() != null && !collegue.getPhotoUrl().equals("")) {
            lesCollegues.modifierPhotoUrl(matricule, collegue.getPhotoUrl());

        }
        return lesCollegues.rechercherParMatricule(matricule);
    }
    


}
