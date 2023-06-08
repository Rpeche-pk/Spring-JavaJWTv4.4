package com.lpa.app.domain.repository;

import com.lpa.app.domain.dto.UserDto;

import java.util.Optional;

public interface IUserRepository {

    UserDto createUser(UserDto userDto);

    Optional<UserDto> getUsername(String username);
}
