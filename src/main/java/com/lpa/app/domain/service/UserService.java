package com.lpa.app.domain.service;

import com.lpa.app.domain.dto.UserDto;
import com.lpa.app.domain.repository.IUserRepository;
import com.lpa.app.domain.useCase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserUseCase {

    private final IUserRepository iUserRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        return iUserRepository.createUser(userDto);
    }

    @Override
    public Optional<UserDto> getUsername(String username) {

      return Optional.ofNullable(iUserRepository.getUsername(username))

              .orElseThrow(()->new RuntimeException("NO SE ENCONTRÓ EL USUARIO"));

    }
}
