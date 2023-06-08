package com.lpa.app.controller;

import com.lpa.app.domain.dto.AuthUserDto;
import com.lpa.app.domain.useCase.IAuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final IAuthUseCase iAuthUseCase;


    @PostMapping(path = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthUserDto authUserDto) {
        System.out.println("ESTOY AQUI!!! EN CONTROLLER");
        HashMap<String, Object> response = new HashMap<>();
        String token= iAuthUseCase.sigIn(authUserDto);
        response.put("TOKEN",token);
        response.put("message", "Token generado correctamente");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesAdmin(){
        return "Hello hello hellooo, has accedido con el rol ADMIN";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/accessUser")
    public String accesUser(){
        return "Hello hello hellooo, has accedido con el rol USER";
    }
    @PreAuthorize("hasRole('INVITED')")
    @GetMapping("/accessInvited")
    public String accesInvited(){
        return "Hello hello hellooo, has accedido con el rol INIVTED";
    }

}
