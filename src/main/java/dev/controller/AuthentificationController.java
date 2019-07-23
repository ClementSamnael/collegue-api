package dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.security.InfosAuthentification;
import dev.service.CollegueService;
import io.jsonwebtoken.Jwts;

@CrossOrigin(allowCredentials = "true")
@RestController
public class AuthentificationController {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private CollegueService lesCollegues;
    
    private PasswordEncoder passwordEncoder;

    public AuthentificationController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody InfosAuthentification infos) {
        return this.lesCollegues.findByLogin(infos.getLogin())
                .filter(login -> passwordEncoder.matches(infos.getMotDePasse(), login.getMotDePasse()))
                .map(login -> {
                    Map<String, Object> infoSupplementaireToken = new HashMap<>();
                    infoSupplementaireToken.put("roles", login.getRoles());

                    String jetonJWT = Jwts.builder()
                            .setSubject(login.getLogin())
                            .addClaims(infoSupplementaireToken)
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN * 1000)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();

                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }

}
