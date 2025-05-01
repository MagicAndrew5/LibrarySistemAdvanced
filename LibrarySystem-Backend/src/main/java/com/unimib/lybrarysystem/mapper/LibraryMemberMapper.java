package com.unimib.lybrarysystem.mapper;

import com.unimib.lybrarysystem.DTO.LibraryMemberDTO;
import com.unimib.lybrarysystem.model.LibraryMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibraryMemberMapper {
    LibraryMemberMapper INSTANCE = Mappers.getMapper(LibraryMemberMapper.class);

    LibraryMemberDTO toDTO(LibraryMember member);
    LibraryMember toEntity(LibraryMemberDTO dto);

    List<LibraryMemberDTO> toDTOList(List<LibraryMember> members);
    List<LibraryMember> toEntityList(List<LibraryMemberDTO> dtos);
}