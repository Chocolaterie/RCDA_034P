package fr.eni.enchere.service;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;

public class SignUpRequestDTO {
    private int id;

    private String pseudo;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String motDePasse;

    private String motDePasseConfirmation;

    private int credit;

    private boolean admin;

    private Adresse adresse;

    public Utilisateur convertToUtilisateur() {
        Utilisateur user = new Utilisateur();
        return user;
    }

}
