package com.lpa.app.model.repository;

import com.lpa.app.domain.dto.RoleDto;
import com.lpa.app.domain.dto.UserDto;
import com.lpa.app.domain.repository.IUserRepository;
import com.lpa.app.model.dao.IUserDao;
import com.lpa.app.model.entity.UserEntity;
import com.lpa.app.model.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserRepository implements IUserRepository {

    private final IUserMapper iUserMapper;
    private final IUserDao iUserDao;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        Set<RoleDto> roles = userDto.getRoles().stream()
                .map(roleDto -> RoleDto.builder().name(roleDto.getName()).build())
                .collect(Collectors.toSet());
        userDto.setRoles(roles);
        UserEntity userEntity = iUserMapper.toUserEntity(userDto);
        return iUserMapper.toUserDto(iUserDao.save(userEntity));
    }

    @Override
    @Transactional
    public Optional<UserDto> getUsername(String username) {
        return iUserDao.findByUsername(username)
                .map(iUserMapper::toUserDto);
    }
}
