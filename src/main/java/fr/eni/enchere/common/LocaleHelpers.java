package fr.eni.enchere.common;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleHelpers {

    private static MessageSource messageSource;

    // Injection automatique de MessageSource
    public LocaleHelpers(MessageSource messageSource) {
        LocaleHelpers.messageSource = messageSource;
    }

    // Méthode pour récupérer une traduction
    public String i18n(String key) {
        Locale locale = LocaleContextHolder.getLocale(); // Récupère la langue actuelle
        return messageSource.getMessage(key, null, locale);
    }

    // Méthode avec paramètres (pour des valeurs dynamiques dans le message)
    public String i18n(String key, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, params, locale);
    }
}
