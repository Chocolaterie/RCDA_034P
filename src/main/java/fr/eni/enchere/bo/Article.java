package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Article {

    private int id;

    private String nomArticle;
    private String description;

    private LocalDate dateDebutEncheres;

    private LocalDate dateFinEncheres;

    private int miseAPrix;
    private int prixVente;

    private String statut;

    private Utilisateur utilisateur;

    private Categorie categorie;

    private Adresse adresse;

    private int derniereEnchere = 0;
    private int plusGrosseEnchere = 0;
    private Utilisateur topEncherisseur = null;


    public Article(int id, String nomArticle, String description, LocalDate dateDebutEncheres,
                   LocalDate dateFinEncheres, int miseAPrix, int prixVente, String statut, Utilisateur utilisateur,
                   Categorie categorie, Adresse adresse) {
        this.setId(id);
        this.setNomArticle(nomArticle);
        this.setDescription(description);
        this.setDateDebutEncheres(dateDebutEncheres);
        this.setDateFinEncheres(dateFinEncheres);
        this.setMiseAPrix(miseAPrix);
        this.setPrixVente(prixVente);
        this.setStatut(statut);
        this.setUser(utilisateur);
        this.setCategorie(categorie);
        this.setAdresse(adresse);
    }

    public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
                   int miseAPrix, int prixVente, String statut, Utilisateur utilisateur, Categorie categorie, Adresse adresse) {
        this.setNomArticle(nomArticle);
        this.setDescription(description);
        this.setDateDebutEncheres(dateDebutEncheres);
        this.setDateFinEncheres(dateFinEncheres);
        this.setMiseAPrix(miseAPrix);
        this.setPrixVente(prixVente);
        this.setStatut(statut);
        this.setUser(utilisateur);
        this.setCategorie(categorie);
        this.setAdresse(adresse);
    }

    public Article() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomArticle() {
        return nomArticle;
    }
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }
    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }
    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }
    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }
    public int getMiseAPrix() {
        return miseAPrix;
    }
    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }
    public int getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public Utilisateur getUser() {
        return utilisateur;
    }
    public void setUser(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Adresse getAdresse() {
        return adresse;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    public int getDerniereEnchere() { return derniereEnchere; }
    public void setDerniereEnchere(int derniereEnchere) { this.derniereEnchere = derniereEnchere; }
    public int getPlusGrosseEnchere() { return plusGrosseEnchere; }
    public void setPlusGrosseEnchere(int plusGrosseEnchere) { this.plusGrosseEnchere = plusGrosseEnchere; }
    public Utilisateur getTopEncherisseur() { return topEncherisseur; }
    public void setTopEncherisseur(Utilisateur topEncherisseur) { this.topEncherisseur = topEncherisseur; }


}