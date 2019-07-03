package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import util.Constantes;

@RestController
@RequestMapping(path = "/collegues")
public class CollegueController {

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getRechercherCollegue(@RequestParam String nomCollegue) {

        List<String> collegues = new ArrayList<>();
        collegues = Constantes.COLLEGUE_SERVICE.rechercherParNom(nomCollegue).stream()
                .map(collegue -> collegue.getMatricule()).collect(Collectors.toList());
        return collegues;
    }
}
