package fr.eni.enchere.service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.AuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthDAO authDAO;

    public ServiceResponse<Utilisateur> login(String email, String password) {

        Utilisateur user = authDAO.getUserByEmailAndPassword(email, password);

        // Si null alors echec auth
        if (user == null) {
            return ServiceHelpers.buildResponse("701", "Erreur couple email / mot de passe");
        }

        // Succès
        return ServiceHelpers.buildResponse("200", "Authentifié(e) avec succès", user);
    }

    public ServiceResponse<Utilisateur> signUp(SignUpRequestDTO signUpRequest) {

        Utilisateur userToInsert = signUpRequest.convertToUtilisateur();
        Utilisateur user = authDAO.insert(userToInsert);

        // Si null alors echec auth
        if (user == null) {
            // avant

            // Apres
            return ServiceHelpers.buildResponse("701", "Erreur couple email / mot de passe");
        }

        // Succès
        return ServiceHelpers.buildResponse("200", "Authentifié(e) avec succès", user);
    }
}
