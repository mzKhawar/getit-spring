package dev.mzkhawar.getit.service;

import dev.mzkhawar.getit.model.dto.LoginRequestDto;
import dev.mzkhawar.getit.model.entities.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(long id);

    String verify(LoginRequestDto loginRequest);
}
