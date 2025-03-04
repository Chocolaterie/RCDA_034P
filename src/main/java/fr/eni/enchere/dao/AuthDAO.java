package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Utilisateur getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT email, mot_de_passe FROM Utilisateurs WHERE email = ? AND mot_de_passe = ?";

        List<Utilisateur> users = jdbcTemplate.query(sql, new Object[]{email, password}, new RowMapper<Utilisateur>() {
            @Override
            public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
                Utilisateur user = new Utilisateur();
                user.setEmail(rs.getString("email"));
                return user;
            }
        });

        return users.isEmpty() ? null : users.get(0);
    }

}
