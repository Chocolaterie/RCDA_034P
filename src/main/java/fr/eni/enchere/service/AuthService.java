package fr.eni.enchere.service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.AuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthDAO authDAO;

    /**
     * Version 1 pas pro pour effectuer une authentification dans le Service (BLL, Manager, comme bon vous semble)
     * @param email
     * @param password
     * @return L'user retrouvé ou pas
     */
    public Utilisateur login(String email, String password) {

        Utilisateur user = authDAO.getUserByEmailAndPassword(email, password);

        // Si null alors echec auth
        if (user == null) {
            // TODO : Normalement dans le cadre d'u nservice pro et réutilisable dans les tests ilfaut retourner
            // une réponse métier et non juste la donnée User "null ou pas"
            // Pour le moment première version pour expliquer les choses par étapes
            return null;
        }

        // Succès
        return user;
    }
}
