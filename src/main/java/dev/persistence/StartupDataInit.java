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

@Component
public class StartupDataInit {

    @Autowired
    CollegueRepository collRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        Collegue col = new Collegue(UUID.randomUUID().toString(), "Durand", "Amandine", "amandine@mail.fr",
                LocalDate.of(1991, 06, 21),
                "https://vignette.wikia.nocookie.net/jjba/images/7/7f/Spice_Girl_infobox.png/revision/latest/scale-to-width-down/310?cb=20180517101701&path-prefix=fr",
                "amande", passwordEncoder.encode("amande"), Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        collRepository.save(col);

        collRepository.save(new Collegue(UUID.randomUUID().toString(), "Bernard", "Clement", "clement@mail.fr",
                LocalDate.of(1992, 06, 11),
                "https://vignette.wikia.nocookie.net/jjba/images/9/9f/Crazy_Diamond_Manga.Infobox.png/revision/latest/scale-to-width-down/310?cb=20180622215653&path-prefix=fr",
                "clement", passwordEncoder.encode("clement"), Arrays.asList("ROLE_USER", "ROLE_ADMIN")));

        collRepository.save(new Collegue(UUID.randomUUID().toString(), "Leroy", "Yoann", "yoann@mail.fr",
                LocalDate.of(1992, 9, 11),
                "https://vignette.wikia.nocookie.net/jjba/images/1/14/Gold_Experience_color.png/revision/latest?cb=20180417125730&path-prefix=fr",
                "yoann", passwordEncoder.encode("yoann"), Arrays.asList("ROLE_USER")));
    }
}
