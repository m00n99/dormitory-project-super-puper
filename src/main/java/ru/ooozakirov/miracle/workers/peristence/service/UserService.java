package ru.ooozakirov.miracle.workers.peristence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ooozakirov.miracle.workers.peristence.dto.auth.CredentialsDto;
import ru.ooozakirov.miracle.workers.peristence.dto.auth.UserDto;
import ru.ooozakirov.miracle.workers.peristence.exception.AppException;
import ru.ooozakirov.miracle.workers.peristence.mapper.UserMapper;
import ru.ooozakirov.miracle.workers.peristence.model.User;
import ru.ooozakirov.miracle.workers.peristence.repo.UserRepository;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.UNAUTHORIZED));

        return userMapper.toUserDto(user);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.UNAUTHORIZED));
        return userMapper.toUserDto(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
