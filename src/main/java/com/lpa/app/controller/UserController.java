package com.lpa.app.controller;

import com.lpa.app.domain.dto.UserDto;
import com.lpa.app.domain.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody UserDto userDto, BindingResult result) {

        HashMap<String, Object> response = new HashMap<>();
        UserDto user= null;
        System.out.println(userDto.toString());
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);

            return ResponseEntity.badRequest().body(response);
        }

        try {
             String pass=passwordEncoder.encode(userDto.getPassword());
             userDto.setPassword(pass);

             user = userService.createUser(userDto);

        } catch (DataAccessException e) {
            response.put("message", "Oops, Ha ocurrido un error , mira abajo el detalle");
            response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("USUARIO", user);
        response.put("Success", "Se creó correctamente");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

}
