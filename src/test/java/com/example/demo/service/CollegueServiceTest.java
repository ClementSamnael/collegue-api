package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.example.demo.entite.Collegue;
import com.example.demo.exception.CollegueInvalideException;
import com.example.demo.util.Constantes;

public class CollegueServiceTest {

    CollegueService lesCollegues = Constantes.COLLEGUE_SERVICE;
    Collegue collegue;

    @Test
    public void testNomCollegue() throws CollegueInvalideException {
        // if getNom().trim() < 2
        // nom a testé = r
        collegue = new Collegue("MatTest", "r", "Clement", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getNom() < 2
        // nom a testé = " "
        collegue = new Collegue("MatTest", "   ", "Clement", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getNom().trim() == 2
        // nom a testé = rb
        collegue = new Collegue("MatTest", "rb", "Clement", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertEquals(Collegue.class, lesCollegues.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    public void testPrenomCollegue() throws CollegueInvalideException {
        // if getPrenom().trim() < 2
        // prenom a testé = c
        collegue = new Collegue("MatTest", "Robert", "c", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getPrenom() < 2
        // prenom a testé = " "
        collegue = new Collegue("MatTest", "Robert", "   ", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getPrenom().trim() == 2
        // nom a testé = cl
        collegue = new Collegue("MatTest", "Robert", "cl", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertEquals(Collegue.class, lesCollegues.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    public void testEmailCollegue() throws CollegueInvalideException {
        // if getEmail() < 3
        // email a testé = @
        collegue = new Collegue("MatTest", "Robert", "Clement", "@", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getEmail() sans @
        // eamil a testé = " "
        collegue = new Collegue("MatTest", "Robert", "Clement", "clement", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getEmail() sans @
        // eamil a testé = " "
        collegue = new Collegue("MatTest", "Robert", "Clement", "  @  ", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getEmail() valid
        collegue = new Collegue("MatTest", "Robert", "Clement", "clement@societe.com", LocalDate.of(1970, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertEquals(Collegue.class, lesCollegues.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    public void testDateCollegue() throws CollegueInvalideException {
        // if getDateNaissance() < 18
        // date a testé =
        collegue = new Collegue("MatTest", "Robert", "Clement", "clement@societe.com", LocalDate.of(2017, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getDateNaissance() valid
        collegue = new Collegue("MatTest", "Robert", "Clement", "clement@societe.com", LocalDate.of(1993, 01, 01),
                "https://randomuser.me/api/portraits/men/76.jpg");
        assertEquals(Collegue.class, lesCollegues.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    public void testPhotoCollegue() throws CollegueInvalideException {
        // if getPhotoUrl() sans http au début
        // date a testé =
        collegue = new Collegue("MatTest", "Robert", "Clement", "clement@societe.com", LocalDate.of(2017, 01, 01),
                "photoTest.com");
        assertThrows(CollegueInvalideException.class, () -> lesCollegues.ajouterUnCollegue(collegue));

        // if getPhotoUrl() valid
        collegue = new Collegue("MatTest", "Robert", "Clement", "clement@societe.com", LocalDate.of(1993, 01, 01),
                "https://randomuser.me/api/portraits/men/50.jpg");
        assertEquals(Collegue.class, lesCollegues.ajouterUnCollegue(collegue).getClass());
    }

}
