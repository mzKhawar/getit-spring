package dev.mzkhawar.getit.service.impl;

import dev.mzkhawar.getit.model.dto.LoginRequestDto;
import dev.mzkhawar.getit.model.entities.User;
import dev.mzkhawar.getit.repository.UserRepository;
import dev.mzkhawar.getit.service.JWTService;
import dev.mzkhawar.getit.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public String verify(LoginRequestDto loginRequest) {
       Authentication authentication =
               authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(loginRequest.getUsername());

        return "Failure";

    }

}
