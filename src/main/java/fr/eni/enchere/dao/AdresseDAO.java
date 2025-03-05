package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Adresse;
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
public class AdresseDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdresseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Adresse getAdresseById(int id) {
        String sql = "SELECT * FROM Adresses WHERE no_adresse = ?";

        List<Adresse> adresses = jdbcTemplate.query(sql, new Object[]{id}, new AdresseRowMapper());

        return adresses.isEmpty() ? null : adresses.get(0);
    }

    public Adresse insert(Adresse adresse) {
        String sql = "INSERT INTO ADRESSES (rue, code_postal, ville, adresse_eni) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, adresse.getRue());
            ps.setString(2, adresse.getCodePostal());
            ps.setString(3, adresse.getVille());
            ps.setBoolean(4, true);
            return ps;
        }, keyHolder);

        // hydrater la clé primaire générée
        adresse.setId(keyHolder.getKey().intValue());

        return adresse;
    }

}
