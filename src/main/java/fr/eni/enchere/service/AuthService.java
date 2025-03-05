package fr.eni.enchere.service;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.AdresseDAO;
import fr.eni.enchere.dao.AuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final AdresseDAO adresseDAO;
    private final AuthDAO authDAO;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthDAO authDAO,  AdresseDAO adresseDAO, @Lazy PasswordEncoder passwordEncoder) {
        this.authDAO = authDAO;
        this.adresseDAO = adresseDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public ServiceResponse<Utilisateur> login(String email, String password) {

        Utilisateur user = authDAO.getUserByEmail(email);

        // Si null
        if (user == null) {
            return ServiceHelpers.buildResponse("701", "Erreur couple email / mot de passe");
        }

        // vérifier le mot de passe avec BCrypt
        String passwordExtract = user.getMotDePasse().replace("{bcrypt}", "");
        if (!passwordEncoder.matches(password, passwordExtract)) {
            return ServiceHelpers.buildResponse("701", "Erreur couple email / mot de passe");
        }

        // get adresse
        Adresse adresse = adresseDAO.getAdresseById(user.getAdresseId());
        user.setAdresse(adresse);

        // Succès
        return ServiceHelpers.buildResponse("200", "Authentifié(e) avec succès", user);
    }

    public ServiceResponse<Utilisateur> signUp(Utilisateur utilisateur) {

        // Erreur : Ne peut pas creer un compte avec le meme email
        Utilisateur userWithEmail = authDAO.getUserByEmail(utilisateur.getEmail());
        if (userWithEmail != null) {
            // TODO : Attention normalement on dois pas informer quel l'emai lest déjà utilisé pour des règles de sécurité
            return ServiceHelpers.buildResponse("702", "Email déjà utilisé");
        }

        // Erreur : Pseudo existant
        Utilisateur userWithPseudo = authDAO.getUserByPseudo(utilisateur.getPseudo());
        if (userWithPseudo != null) {
            return ServiceHelpers.buildResponse("702", "Pseudo déjà utilisé");
        }

        // Essayer de creer l'adresse en base
        Adresse insertedAdresse = adresseDAO.insert(utilisateur.getAdresse());

        // Relier la nouvelle adresse hydratée depuis la base dans l'utilisateur (l'id généré)
        utilisateur.setAdresse(insertedAdresse);

        // Générer le mot de passe haché
        String newHashedPassword = "{bcrypt}" + passwordEncoder.encode(utilisateur.getMotDePasse());
        utilisateur.setMotDePasse(newHashedPassword);

        // Appel DAO pour insert de l'user
        Utilisateur insertedUser = authDAO.insert(utilisateur);

        // Si null alors echec inscription
        if (insertedUser == null) {
            return ServiceHelpers.buildResponse("701", "Erreur lors de l'inscription");
        }

        // Succès
        return ServiceHelpers.buildResponse("200", "Inscription effectué", insertedUser);
    }
}
