package com.lpa.app.domain.dto;

import com.lpa.app.model.entity.RoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
public class UserDto {

    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 30)
    private String username;
    @NotBlank
    private String password;
    private Set<RoleDto> roles;
}
