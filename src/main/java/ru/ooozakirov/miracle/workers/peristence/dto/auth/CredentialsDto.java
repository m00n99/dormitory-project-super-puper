package ru.ooozakirov.miracle.workers.peristence.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CredentialsDto {
    private String login;
    private char[] password;
}
