package com.internationalization.login;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class UrlLocaleResolver implements LocaleResolver {

    private static final String LOCALE = "LOCALE";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // ==> /ContextPath/en/...
        // ==> /ContextPath/fr/...
        // ==> /SomeContextPath/WEB-INF/pages/...
        String uri = request.getRequestURI();

        String languageEN = request.getServletContext().getContextPath() + "/en/";
        String languageFR = request.getServletContext().getContextPath() + "/fr/";
        String languageDE = request.getServletContext().getContextPath() + "/de/";
        String languageES = request.getServletContext().getContextPath() + "/es/";

        Locale locale = null;

        if (uri.startsWith(languageEN)) {			// English
            locale = Locale.ENGLISH;
        } else if (uri.startsWith(languageDE)) {	// German
            locale = Locale.GERMAN;
        } else if (uri.startsWith(languageFR)) {	// French
            locale = Locale.FRENCH;
        } else if (uri.startsWith(languageES)) {	// Spanish
            locale = new Locale("es", "ES");
        }
        
        if (locale == null) {
            locale = (Locale) request.getSession().getAttribute(LOCALE);
            if (locale == null) {
                locale = Locale.ENGLISH;
            }
        } else {
            request.getSession().setAttribute(LOCALE, locale);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    	
    }
}