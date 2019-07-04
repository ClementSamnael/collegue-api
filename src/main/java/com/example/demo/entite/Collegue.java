package com.example.demo.entite;

import java.time.LocalDate;

public class Collegue {

    public String matricule;
    public String nom;
    public String prenom;
    public String email;
    public LocalDate dateDeNaissance;
    public String photoUrl;

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

    
    
    public Collegue(String nom, String prenom, String email, LocalDate dateDeNaissance, String photoUrl) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
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
