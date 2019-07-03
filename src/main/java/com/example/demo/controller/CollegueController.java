package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entite.Collegue;
import com.example.demo.service.CollegueService;
import com.example.demo.util.Constantes;

@RestController
public class CollegueController {
    
    private CollegueService lesCollegues = Constantes.COLLEGUE_SERVICE;

    @RequestMapping(method = RequestMethod.GET,
            path = "/collegues")
    public List<String> getRechercherCollegue(@RequestParam String nomCollegue) {

        List<String> colleguesMa = new ArrayList<>();
        List<Collegue> collegues = lesCollegues.rechercherParNom(nomCollegue);
        for(Collegue c : collegues) {
            colleguesMa.add(c.getMatricule());
        }
        return colleguesMa;
    }
}
