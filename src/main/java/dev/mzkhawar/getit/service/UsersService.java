package dev.mzkhawar.getit.service;

import dev.mzkhawar.getit.model.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Users save(Users users);

    List<Users> findAll();

    Optional<Users> findById(Long id);
}
