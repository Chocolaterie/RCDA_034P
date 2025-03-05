package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Utilisateur> {

    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utilisateur user = new Utilisateur();
        user.setPseudo(rs.getString("pseudo"));
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setEmail(rs.getString("email"));
        user.setMotDePasse(rs.getString("mot_de_passe"));
        user.setTelephone(rs.getString("telephone"));
        user.setCredit(rs.getInt("credit"));
        user.setAdmin(rs.getBoolean("administrateur"));
        // Todo : Whole object parsing ?
        user.setAdresseId(rs.getInt("no_adresse"));

        return user;
    }
}
