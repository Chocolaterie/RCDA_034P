package fr.eni.enchere.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"loggedUser"})
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
