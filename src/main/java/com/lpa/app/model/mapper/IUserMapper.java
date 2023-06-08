package com.lpa.app.model.mapper;

import com.lpa.app.domain.dto.RoleDto;
import com.lpa.app.domain.dto.UserDto;
import com.lpa.app.model.entity.ERole;
import com.lpa.app.model.entity.RoleEntity;
import com.lpa.app.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
//@Component
public interface IUserMapper {


    @Mapping(target = "id" ,ignore = true)
    UserEntity toUserEntity(UserDto userDto);

    //@Mapping(target = "id" ,ignore = true)
    UserDto toUserDto(UserEntity userEntity);

    /*Set<RoleEntity> toRolesEntity(Set<RoleDto> roleDto);

    default RoleEntity mapRole(RoleDto roleDto){
        Set<RoleDto> roles = userDto.getRoles().stream()
                .map(roleDto -> RoleDto.builder().name(roleDto.getName()).build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roles(roles)
                .username(userDto.getUsername())
                .build();
        userDto.setRoles(roles);
    }*/

}
