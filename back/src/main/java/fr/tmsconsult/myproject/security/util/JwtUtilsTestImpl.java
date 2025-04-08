package fr.tmsconsult.myproject.security.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class JwtUtilsTestImpl extends JwtUtils {
    @Override
    public String extractTokenFromRequest(HttpServletRequest request) {
        // Retourne un token fictif ou vide selon tes besoins pour les tests
        return "fake-token";
    }
}
