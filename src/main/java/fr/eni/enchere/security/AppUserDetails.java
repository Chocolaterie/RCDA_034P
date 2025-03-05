package fr.eni.enchere.security;


import fr.eni.enchere.bo.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppUserDetails implements UserDetails {

    private final Utilisateur user;

    public AppUserDetails(Utilisateur user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Ajouter des rôles si besoin
    }

    @Override
    public String getPassword() {
        return user.getMotDePasse(); // Doit être haché
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Utilisateur getUser() {
        return user;
    }
}
