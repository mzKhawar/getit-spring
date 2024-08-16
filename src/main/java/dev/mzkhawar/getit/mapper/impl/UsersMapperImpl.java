package dev.mzkhawar.getit.mapper.impl;

import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.model.dto.UsersDto;
import dev.mzkhawar.getit.model.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersMapperImpl implements Mapper<Users, UsersDto> {

    private final ModelMapper modelMapper;

    public UsersMapperImpl(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersDto mapTo(Users users) {
        return modelMapper.map(users, UsersDto.class);
    }

    @Override
    public Users mapFrom(UsersDto usersDto) {
        return modelMapper.map(usersDto, Users.class);
    }
}
