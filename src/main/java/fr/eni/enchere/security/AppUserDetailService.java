package fr.eni.enchere.security;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.service.AuthService;
import fr.eni.enchere.service.ServiceResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final AuthService authService;

    public AppUserDetailService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ServiceResponse<Utilisateur> response = authService.login(email, ""); // Password géré plus tard

        if (!"200".equals(response.getCode())) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        Utilisateur user = response.getData();
        return new AppUserDetails(user);
    }
}
