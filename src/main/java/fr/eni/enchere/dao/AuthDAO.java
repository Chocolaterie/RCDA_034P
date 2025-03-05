package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class AuthDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Utilisateur getUserByEmail(String email) {
        String sql = "SELECT * FROM Utilisateurs WHERE email = ?";

        List<Utilisateur> users = jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper());

        return users.isEmpty() ? null : users.get(0);
    }

    public Utilisateur getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM Utilisateurs WHERE email = ? AND mot_de_passe = ?";

        List<Utilisateur> users = jdbcTemplate.query(sql, new Object[]{email, password}, new UserRowMapper());

        return users.isEmpty() ? null : users.get(0);
    }

    public Utilisateur insert(Utilisateur user) {
        String sql = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, mot_de_passe, credit, administrateur, no_adresse) VALUES (?,?, ?, ?, ?, ?, ?, ?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getPseudo());
            ps.setString(2, user.getNom());
            ps.setString(3, user.getPrenom());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getTelephone());
            ps.setString(6, user.getMotDePasse());
            ps.setInt(7, user.getCredit());
            ps.setBoolean(8, user.isAdmin());
            ps.setInt(9, user.getAdresse().getId());
            return ps;
        }, keyHolder);

        return user;
    }

}
