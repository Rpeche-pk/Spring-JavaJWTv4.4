package com.lpa.app.model.dao;

import com.lpa.app.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
