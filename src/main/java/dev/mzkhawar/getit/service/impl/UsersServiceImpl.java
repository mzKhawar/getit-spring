package dev.mzkhawar.getit.service.impl;

import dev.mzkhawar.getit.model.entities.Users;
import dev.mzkhawar.getit.repository.UsersRepository;
import dev.mzkhawar.getit.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users save(final Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return StreamSupport.stream(usersRepository
                                .findAll()
                                .spliterator(),
                        false)
                .toList();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

}
