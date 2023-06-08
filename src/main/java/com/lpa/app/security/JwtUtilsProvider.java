package com.lpa.app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lpa.app.domain.dto.UserDto;
import com.lpa.app.domain.useCase.IUserUseCase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtilsProvider {

    @Value("${jwt.secret.key}")
    private String KEY_SECRET;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    private final IUserUseCase iUserUseCase;

    public JwtUtilsProvider(IUserUseCase iUserUseCase) {
        this.iUserUseCase = iUserUseCase;
    }

    @PostConstruct
    protected void init(){
        KEY_SECRET = Base64.getEncoder().encodeToString(KEY_SECRET.getBytes());
    }

    public String createToken(UserDto userDto){

        Date now= new Date();
        Date expiration = new Date(now.getTime()+ Long.parseLong(timeExpiration));
        Algorithm algorithm= Algorithm.HMAC256(KEY_SECRET);

        return JWT.create()
                .withIssuer(userDto.getUsername())
                .withClaim("Email",userDto.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    public Authentication validToken(String token){
        Algorithm algorithm= Algorithm.HMAC256(KEY_SECRET);

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        String username = decoded.getIssuer();

        Optional<UserDto> user = iUserUseCase.getUsername(username);

        Set<SimpleGrantedAuthority> roles = user.get().getRoles().stream()
                .map(roleDto -> new SimpleGrantedAuthority("ROLE_" + roleDto.getName()))
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(user,token,roles);
    }


}
