package com.lpa.app.domain.useCase;

import com.lpa.app.domain.dto.UserDto;

import java.util.Optional;

public interface IUserUseCase {

    UserDto createUser(UserDto userDto);

    Optional<UserDto> getUsername(String username);
}
