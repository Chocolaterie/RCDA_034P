package fr.eni.enchere.service;

import jakarta.validation.constraints.NotBlank;

public class ResetPasswordRequestDTO {

    @NotBlank
    public String motDePasse;

    @NotBlank
    public String newMotDePasse;

    @NotBlank
    public String newMotDePasseConfirmation;

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNewMotDePasse() {
        return newMotDePasse;
    }

    public void setNewMotDePasse(String newMotDePasse) {
        this.newMotDePasse = newMotDePasse;
    }

    public String getNewMotDePasseConfirmation() {
        return newMotDePasseConfirmation;
    }

    public void setNewMotDePasseConfirmation(String newMotDePasseConfirmation) {
        this.newMotDePasseConfirmation = newMotDePasseConfirmation;
    }
}
