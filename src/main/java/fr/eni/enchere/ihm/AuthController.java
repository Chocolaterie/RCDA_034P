package fr.eni.enchere.ihm;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.common.LocaleHelpers;
import fr.eni.enchere.service.AuthRequestDTO;
import fr.eni.enchere.service.AuthService;
import fr.eni.enchere.service.ServiceResponse;
import fr.eni.enchere.service.SignUpRequestDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static fr.eni.enchere.ihm.FlashMessage.*;

@Controller
@SessionAttributes({"loggedUser"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    LocaleHelpers localeHelpers;

    @GetMapping("/sign-up")
    public String signup(Model model, RedirectAttributes redirectAttributes) {

        // Middleware à la main
        if (model.containsAttribute("loggedUser")) {

            redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(FLASH_WARNING, localeHelpers.i18n("app.auth.message.already_logged")));

            return "redirect:/";
        }

        if (!model.containsAttribute("signUpRequest")) {
            model.addAttribute("signUpRequest", new SignUpRequestDTO());
        }
        return "auth/signup";
    }

    @PostMapping("sign-up")
    public String signUpPost(@Valid @ModelAttribute("signUpRequest") SignUpRequestDTO signUpRequest, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        // 1 :: Controle de surface (formulaire)
        // -- A la main ajouter confirmation de mot de passe
        if (!signUpRequest.getMotDePasse().equals(signUpRequest.getMotDePasseConfirmation())) {
            bindingResult.rejectValue("motDePasse", "app.error.password_confirm", "Les mot de passes doivent être identiques");
        }

        // Si erreur de validation detecté
        // Si il y'a au moins une erreurs dans le binding result
        if (bindingResult.hasErrors()) {
            // Stop on affiche le formulaire
            return "auth/signup";
        }

        // 2 :: Metier - Si le traitement n'est pas bon (not equals attention !)
        // Bonus : Etant donnée que j'utilise un DTO pour les formulaire, je dois récupérer la version BO pour le service
        // Juste du code à la main pour mapper un dto en BO (instancier un utilisateur depuis la saisie de la DTO/pojo)
        Utilisateur utilisateur = signUpRequest.convertToUtilisateur();

        // Appeler le service inscrire
        ServiceResponse<Utilisateur> serviceResponse = authService.signUp(utilisateur);

        // Si erreur : Afficher erreur
        if (!serviceResponse.code.equals("200")){
            // pas de redirect donc j'utilise le model pour envoyer un "flashMessage" et non le redirectAttributes
            model.addAttribute("flashMessage", new FlashMessage(FLASH_ERROR, serviceResponse.message));

            return "auth/signup";
        }

        // -- Sinon succès : Rediriger sur l'accueil avec flash message succès
        redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(FLASH_SUCCESS, serviceResponse.message));
        return "redirect:/";
    }

    @GetMapping("/auth")
    public String auth(Model model, RedirectAttributes redirectAttributes) {

        // Middleware à la main
        if (model.containsAttribute("loggedUser")) {

            redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(FLASH_WARNING, localeHelpers.i18n("app.auth.message.already_logged")));

            return "redirect:/";
        }

        if (!model.containsAttribute("authRequest")) {
            model.addAttribute("authRequest", new AuthRequestDTO());
        }
        return "auth/login";
    }

    @PostMapping("/auth")
    public String authPost(@ModelAttribute("authRequest") AuthRequestDTO authRequest, Model model, RedirectAttributes redirectAttributes) {
        // Controle métier
        ServiceResponse<Utilisateur> serviceResponse = authService.login(authRequest.email, authRequest.password);

        // Si ok alors session
        if (serviceResponse.code.equals("200")) {

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

    @GetMapping("/logout")
    public String logout(Model model, RedirectAttributes redirectAttributes, HttpSession session, SessionStatus sessionStatus) {

        // Middleware à la main
        if (!model.containsAttribute("loggedUser")) {
            return "redirect:/";
        }

        // Déconnexion et flash message
        session.removeAttribute("loggedUser");
        session.invalidate();
        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(FLASH_SUCCESS, localeHelpers.i18n("app.auth.logout.success")));

        return "redirect:/";
    }
}
