package dev.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

@Entity
public class Collegue {

    @Id
    public String matricule; // Identifiant de connexion
    public String nom;
    public String prenom;
    public String email;
    @Column(name = "Date_de_Naissance")
    public LocalDate dateDeNaissance;
    @Column(name = "Photo")
    public String photoUrl;

    @Column
    private String login;// login
    @Column
    private String motDePasse;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Collegue() {
    }

    public Collegue(String matricule, String nom, String prenom, String email, LocalDate dateDeNaissance,
            String photoUrl) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
    }

    public Collegue(String matricule, String nom, String prenom, String email, LocalDate dateDeNaissance,
            String photoUrl, String login, String motDePasse, List<String> roles) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
        this.login = login;
        this.motDePasse = motDePasse;
        this.roles = roles;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Collegue [matricule=");
        builder.append(matricule);
        builder.append(", nom=");
        builder.append(nom);
        builder.append(", prenom=");
        builder.append(prenom);
        builder.append(", email=");
        builder.append(email);
        builder.append(", dateDeNaissance=");
        builder.append(dateDeNaissance);
        builder.append(", photoUrl=");
        builder.append(photoUrl);
        builder.append("]");
        return builder.toString();
    }

}
