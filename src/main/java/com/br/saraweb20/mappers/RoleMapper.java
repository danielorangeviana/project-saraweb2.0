package com.br.saraweb20.mappers;

import com.br.saraweb20.dto.RoleDTO;
import com.br.saraweb20.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role entity);

    Role toEntity(RoleDTO dto);
}
