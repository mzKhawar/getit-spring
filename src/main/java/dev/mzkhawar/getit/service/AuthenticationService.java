package dev.mzkhawar.getit.service;

import dev.mzkhawar.getit.model.dto.AuthenticationRequest;
import dev.mzkhawar.getit.model.dto.AuthenticationResponse;
import dev.mzkhawar.getit.model.dto.RegisterRequest;
import dev.mzkhawar.getit.model.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);

    void refreshToken(HttpServletRequest request,
                      HttpServletResponse response) throws IOException;
}
