package com.unimib.librarysystem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.unimib.librarysystem.DTO.GenreDTO;
import com.unimib.librarysystem.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO toDTO(Genre genre);

    Genre toEntity(GenreDTO dto);

    List<GenreDTO> toDTOList(List<Genre> genres);

    List<Genre> toEntityList(List<GenreDTO> dtos);
}
