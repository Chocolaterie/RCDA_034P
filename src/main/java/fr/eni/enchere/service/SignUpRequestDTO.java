package fr.eni.enchere.service;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequestDTO {

    @NotBlank
    private String pseudo;

    @NotBlank
    @Size(min=4, max=100)
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String email;

    private String telephone;

    @NotBlank
    private String rue;

    @NotBlank
    private String codePostal;

    @NotBlank
    private String ville;

    @NotBlank
    private String motDePasse;

    @NotBlank
    private String motDePasseConfirmation;

    public Utilisateur convertToUtilisateur() {
        // Convert adresse
        Adresse adresse = new Adresse(rue, codePostal, ville);

        Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, adresse, motDePasse, 0);

        return user;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasseConfirmation() {
        return motDePasseConfirmation;
    }

    public void setMotDePasseConfirmation(String motDePasseConfirmation) {
        this.motDePasseConfirmation = motDePasseConfirmation;
    }
}
