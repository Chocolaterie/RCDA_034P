package fr.eni.enchere.service.v2;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.AuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceV2 {

    @Autowired
    AuthDAO authDAO;

    public ServiceResponse<Utilisateur> login(String email, String password) {

        ServiceResponse<Utilisateur> response = new ServiceResponse();

        Utilisateur user = authDAO.getUserByEmailAndPassword(email, password);

        // Si null alors echec auth
        if (user == null) {
            response.code = "701";
            response.message = "Erreur couple email / mot de passe";
            return response;
        }

        // Succès
        response.code = "200";
        response.message = "Authentifié(e) avec succès";
        return response;
    }
}
