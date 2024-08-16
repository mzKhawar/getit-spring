package dev.mzkhawar.getit.mappers.impl;

import dev.mzkhawar.getit.domain.dto.WeightDto;
import dev.mzkhawar.getit.domain.entities.WeightEntity;
import dev.mzkhawar.getit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WeightMapperImpl implements Mapper<WeightEntity, WeightDto> {

    private ModelMapper modelMapper;

    public WeightMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WeightDto mapTo(WeightEntity weightEntity) {
        return modelMapper.map(weightEntity, WeightDto.class);
    }

    @Override
    public WeightEntity mapFrom(WeightDto weightDto) {
        return modelMapper.map(weightDto, WeightEntity.class);
    }
}
