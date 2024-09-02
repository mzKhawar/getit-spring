package dev.mzkhawar.getit.mapper.impl;

import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.model.dto.UserDto;
import dev.mzkhawar.getit.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements Mapper<User, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto mapTo(User user) {
       return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User mapFrom(UserDto userDto) {
       return modelMapper.map(userDto, User.class);
    }


}
