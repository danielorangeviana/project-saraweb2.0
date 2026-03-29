package com.br.saraweb.mappers;

import com.br.saraweb.dto.UserDTO;
import com.br.saraweb.dto.UserInsertDTO;
import com.br.saraweb.dto.UserUpdateDTO;
import com.br.saraweb.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", source = "roles")
    UserDTO toDTO(User entity);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO dto);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserInsertDTO dto);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserUpdateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDTO(UserDTO dto, @MappingTarget User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDTO(UserUpdateDTO dto, @MappingTarget User entity);
}