package com.unimib.lybrarysystem.mapper;

import com.unimib.lybrarysystem.DTO.AuthorDTO;
import com.unimib.lybrarysystem.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO dto);

    List<AuthorDTO> toDTOList(List<Author> authors);

    List<Author> toEntityList(List<AuthorDTO> dtos);
}