package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseRowMapper implements RowMapper<Adresse> {

    @Override
    public Adresse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Adresse adresse = new Adresse();

        adresse.setId(rs.getInt("no_adresse"));
        adresse.setRue(rs.getString("rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));

        return adresse;
    }
}
