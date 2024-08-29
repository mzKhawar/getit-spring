package dev.mzkhawar.getit.controller;

import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.model.dto.LoginRequestDto;
import dev.mzkhawar.getit.model.dto.UserDto;
import dev.mzkhawar.getit.model.entities.User;
import dev.mzkhawar.getit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Mapper<User, UserDto> userMapper;

    public UserController(UserService userService, Mapper<User, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody final UserDto user) {
        User userEntity = userMapper.mapFrom(user);
        User savedUser = userService.save(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequest) {
        return userService.verify(loginRequest);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal) {
        return principal.getName();
    }
}
