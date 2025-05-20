package com.unimib.lybrarysystem.mapper;

import org.mapstruct.factory.Mappers;

import com.unimib.lybrarysystem.DTO.UserDTO;
import com.unimib.lybrarysystem.model.User;


public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserMapper toDTO(User user);

    UserMapper toEntity(UserDTO dto);

}
