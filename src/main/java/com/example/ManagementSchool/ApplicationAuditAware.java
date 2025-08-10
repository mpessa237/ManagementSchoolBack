package com.example.ManagementSchool;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }

        // Assurez-vous que votre objet User implémente l'interface UserDetails
        // ou a une méthode getId()
        var user = (com.example.ManagementSchool.entity.User) authentication.getPrincipal();
        return Optional.of(user.getUserId());
    }
}
