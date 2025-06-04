package com.unimib.librarysystem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.unimib.librarysystem.DTO.LibraryMemberDTO;
import com.unimib.librarysystem.model.LibraryMember;

@Mapper(componentModel = "spring")
public interface LibraryMemberMapper {
    LibraryMemberMapper INSTANCE = Mappers.getMapper(LibraryMemberMapper.class);

    LibraryMemberDTO toDTO(LibraryMember member);

    LibraryMember toEntity(LibraryMemberDTO dto);

    List<LibraryMemberDTO> toDTOList(List<LibraryMember> members);

    List<LibraryMember> toEntityList(List<LibraryMemberDTO> dtos);
}
