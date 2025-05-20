package com.unimib.lybrarysystem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.unimib.lybrarysystem.DTO.GenreDTO;
import com.unimib.lybrarysystem.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO toDTO(Genre genre);

    Genre toEntity(GenreDTO dto);

    List<GenreDTO> toDTOList(List<Genre> genres);

    List<Genre> toEntityList(List<GenreDTO> dtos);
}
