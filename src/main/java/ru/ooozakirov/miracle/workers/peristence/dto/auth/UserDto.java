package ru.ooozakirov.miracle.workers.peristence.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private Long id;
    private String login;
    private String token;
    private Role role;
}
