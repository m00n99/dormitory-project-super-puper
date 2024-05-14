package ru.ooozakirov.miracle.workers.peristence.dto.auth;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    STUDENT,
    COMMANDANT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
