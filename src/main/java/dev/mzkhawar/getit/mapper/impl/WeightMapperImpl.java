package dev.mzkhawar.getit.mapper.impl;

import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.Weight;
import dev.mzkhawar.getit.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeightMapperImpl implements Mapper<Weight, WeightDto> {

    private final ModelMapper modelMapper;

    @Override
    public WeightDto mapTo(Weight weight) {
        return modelMapper.map(weight, WeightDto.class);
    }

    @Override
    public Weight mapFrom(WeightDto weightDto) {
        return modelMapper.map(weightDto, Weight.class);
    }
}
