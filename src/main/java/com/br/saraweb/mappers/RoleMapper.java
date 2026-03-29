package com.br.saraweb.mappers;

import com.br.saraweb.dto.RoleDTO;
import com.br.saraweb.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role entity);

    @Mapping(target = "users", ignore = true)
    Role toEntity(RoleDTO dto);
}