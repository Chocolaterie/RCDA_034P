package fr.eni.enchere.bo;


import java.io.Serializable;

public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String rue;

    private String codePostal;

    private String ville;

    public Adresse() {
    }

    public Adresse(int id, String rue, String codePostal, String ville) {
        super();
        this.id = id;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Adresse(String rue, String codePostal, String ville) {
        this(-1, rue, codePostal, ville);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
