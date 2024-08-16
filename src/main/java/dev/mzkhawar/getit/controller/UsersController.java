package dev.mzkhawar.getit.controller;

import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.model.dto.UsersDto;
import dev.mzkhawar.getit.model.entities.Users;
import dev.mzkhawar.getit.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    private final Mapper<Users, UsersDto> usersMapper;

    public UsersController(final UsersService usersService, final Mapper<Users, UsersDto> usersMapper) {
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @PostMapping("")
    public ResponseEntity<UsersDto> createUser(@RequestBody final UsersDto userDto) {
        Users userEntity = usersMapper.mapFrom(userDto);
        Users savedUser = usersService.save(userEntity);
        return new ResponseEntity<>(usersMapper.mapTo(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<UsersDto>> getUsers() {
        List<Users> users = usersService.findAll();
        return new ResponseEntity<>(users.stream().map(usersMapper::mapTo).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable final Long id) {
        Optional<Users> foundUser = usersService.findById(id);
        return foundUser.map(user -> {
            UsersDto userDto = usersMapper.mapTo(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




}
