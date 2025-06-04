package com.unimib.librarysystem.mapper;

import org.mapstruct.factory.Mappers;

import com.unimib.librarysystem.DTO.UserDTO;
import com.unimib.librarysystem.model.User;


public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserMapper toDTO(User user);

    UserMapper toEntity(UserDTO dto);

}
