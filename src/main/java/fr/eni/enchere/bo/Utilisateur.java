package fr.eni.enchere.bo;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String pseudo;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String motDePasse;

    private int credit;

    private boolean admin;

    private Adresse adresse;

    private int adresseId;

    public Utilisateur() {
    }

    public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone, Adresse adresse, String motDePasse, int credit) {
        this.id = id;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
        this.credit = credit;
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, Adresse adresse, String motDePasse, int credit) {
        this(-1, pseudo, nom, prenom, email, telephone, adresse, motDePasse, credit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(int adresseId) {
        this.adresseId = adresseId;
    }
}
