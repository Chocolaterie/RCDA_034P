package fr.eni.enchere.service;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.AdresseDAO;
import fr.eni.enchere.dao.AuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthDAO authDAO;

    @Autowired
    AdresseDAO adresseDAO;

    public ServiceResponse<Utilisateur> login(String email, String password) {

        Utilisateur user = authDAO.getUserByEmailAndPassword(email, password);

        // Si null alors echec auth
        if (user == null) {
            return ServiceHelpers.buildResponse("701", "Erreur couple email / mot de passe");
        }

        // Succès
        return ServiceHelpers.buildResponse("200", "Authentifié(e) avec succès", user);
    }

    public ServiceResponse<Utilisateur> signUp(Utilisateur utilisateur) {

        // Ne peut pas creer un compte avec le meme email
        Utilisateur userWithEmail = authDAO.getUserByEmail(utilisateur.getEmail());
        if (userWithEmail != null) {
            // TODO : Attention normalement on dois pas informer quel l'emai lest déjà utilisé pour des règles de sécurité
            return ServiceHelpers.buildResponse("702", "Email déjà utilisé");
        }

        // Essayer de creer l'adresse en base
        Adresse insertedAdresse = adresseDAO.insert(utilisateur.getAdresse());

        // Relier la nouvelle adresse hydratée depuis la base dans l'utilisateur (l'id généré)
        utilisateur.setAdresse(insertedAdresse);

        Utilisateur insertedUser = authDAO.insert(utilisateur);

        // Si null alors echec inscription
        if (insertedUser == null) {
            return ServiceHelpers.buildResponse("701", "Erreur lors de l'inscription");
        }

        // Succès
        return ServiceHelpers.buildResponse("200", "Inscription effectué", insertedUser);
    }
}
