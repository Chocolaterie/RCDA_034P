package fr.eni.enchere.ihm;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.service.AuthRequestDTO;
import fr.eni.enchere.service.AuthService;
import fr.eni.enchere.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static fr.eni.enchere.ihm.FlashMessage.FLASH_ERROR;
import static fr.eni.enchere.ihm.FlashMessage.FLASH_SUCCESS;

@Controller
@SessionAttributes({"loggedUser"})
public class AuthController {

    @Autowired
    private AuthService authServiceV2;

    @GetMapping("/auth")
    public String auth(Model model) {
        if (!model.containsAttribute("authRequest")) {
            model.addAttribute("authRequest", new AuthRequestDTO());
        }
        return "auth/login";
    }

    @PostMapping("/auth")
    public String authPost(@ModelAttribute("authRequest") AuthRequestDTO authRequest, Model model, RedirectAttributes redirectAttributes) {
        // Controle métier
        ServiceResponse<Utilisateur> serviceResponse = authServiceV2.login(authRequest.email, authRequest.password);

        // Si ok alors session
        if (serviceResponse.code.equals("200")){

            // L'user qui viens de resultat du service
            Utilisateur utilisateur = serviceResponse.data;

            // Ajouter l'user connecté en session
            model.addAttribute("loggedUser", utilisateur);

            // Flash message success
            redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(FLASH_SUCCESS, serviceResponse.message));

            // Redirection à la page d'accueil
            return "redirect:/";
        }

        // Sinon retourne page login avec erreurs
        // Flash message pour l'erreur
        redirectAttributes.addFlashAttribute("authRequest", authRequest);
        redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(FLASH_ERROR, serviceResponse.message));

        // Redirige sur la page auth en GET
        return "redirect:/auth";
    }
}
