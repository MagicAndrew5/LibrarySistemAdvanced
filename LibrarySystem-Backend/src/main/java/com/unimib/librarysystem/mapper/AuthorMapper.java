package com.unimib.librarysystem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.unimib.librarysystem.DTO.AuthorDTO;
import com.unimib.librarysystem.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO dto);

    List<AuthorDTO> toDTOList(List<Author> authors);

    List<Author> toEntityList(List<AuthorDTO> dtos);
}
