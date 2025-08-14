package com.br.saraweb20.mappers;

import com.br.saraweb20.dto.UserDTO;
import com.br.saraweb20.dto.UserInsertDTO;
import com.br.saraweb20.dto.UserUpdateDTO;
import com.br.saraweb20.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles")
    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);

    User toEntity(UserInsertDTO dto);

    User toEntity(UserUpdateDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(UserDTO dto, @MappingTarget User entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(UserUpdateDTO dto, @MappingTarget User entity);
}
