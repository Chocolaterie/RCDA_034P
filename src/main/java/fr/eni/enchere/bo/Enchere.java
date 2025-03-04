package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {

    private int id;

    private LocalDate date;

    private int montant;

    private Utilisateur utilisateur;

    private Article article;

    public Enchere() {
    }

    public Enchere(int id, LocalDate date,
                   int montant,
                   Utilisateur utilisateur,
                   Article article) {
        super();
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public Enchere(LocalDate date,
                   int montant,
                   Utilisateur utilisateur,
                   Article article) {
        super();
        this.date = date;
        this.montant = montant;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Utilisateur getUser() {
        return utilisateur;
    }

    public void setUser(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
