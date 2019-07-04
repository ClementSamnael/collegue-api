package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entite.Collegue;
import com.example.demo.exception.CollegueInvalideException;
import com.example.demo.exception.CollegueNonTrouverException;
import com.example.demo.service.CollegueService;
import com.example.demo.util.Constantes;

@RestController
@RequestMapping(path = "/collegues")
public class CollegueController {

    private CollegueService lesCollegues = Constantes.COLLEGUE_SERVICE;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getRechercherCollegue(@RequestParam String nomCollegue) {

        List<String> colleguesMa = new ArrayList<>();
        List<Collegue> collegues = lesCollegues.rechercherParNom(nomCollegue);
        for (Collegue c : collegues) {
            colleguesMa.add(c.getMatricule());
        }
        return colleguesMa;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{matricule}")
    public Collegue rechercherCollegueParMatricule(@PathVariable String matricule) throws CollegueNonTrouverException {
        return lesCollegues.rechercherParMatricule(matricule);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Collegue ajouterCollegue(@RequestBody Collegue collegue) throws CollegueInvalideException{
        return lesCollegues.ajouterUnCollegue(collegue);
    }
    
}
