package com.lpa.app.domain.useCase;

import com.lpa.app.domain.dto.AuthUserDto;

public interface IAuthUseCase {

    String sigIn(AuthUserDto authUserDto);
}
