package com.lpa.app.domain.service;

import com.lpa.app.domain.dto.AuthUserDto;
import com.lpa.app.domain.dto.UserDto;
import com.lpa.app.domain.repository.IUserRepository;
import com.lpa.app.domain.useCase.IAuthUseCase;
import com.lpa.app.security.JwtUtilsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthUseCase {

    private final IUserRepository iUserRepository;
    private final JwtUtilsProvider jwtUtilsProvider;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String sigIn(AuthUserDto authUserDto) {
        Optional<UserDto> userDto = iUserRepository.getUsername(authUserDto.getUsername());
        System.out.println("ESTOY AQUI!!! EN AUTHSERVICE");
        if(userDto.isEmpty()){
            throw new RuntimeException("El usuario no existe°!");
        }

        if (!passwordEncoder.matches(authUserDto.getPassword(), userDto.get().getPassword())) {
            throw new RuntimeException("USUARIO INCORRECTO O PASSWORD!!!");
        }
        return jwtUtilsProvider.createToken(userDto.get());

    }
}
