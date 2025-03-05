package fr.eni.enchere.security;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.service.AuthService;
import fr.eni.enchere.service.ServiceResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final AppUserDetailService userDetailsService;
    private final AuthService authService;

    public AppAuthenticationProvider(AppUserDetailService userDetailsService, AuthService authService) {
        this.userDetailsService = userDetailsService;
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        ServiceResponse<Utilisateur> response = authService.login(email, rawPassword);

        if (!"200".equals(response.getCode())) {
            throw new UsernameNotFoundException("Authentification échouée : " + response.getMessage());
        }

        UserDetails userDetails = new AppUserDetails(response.getData());
        return new UsernamePasswordAuthenticationToken(userDetails, rawPassword, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
