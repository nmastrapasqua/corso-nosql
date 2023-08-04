package com.sideagroup.lab.cassandra.mappers;

import com.sideagroup.lab.cassandra.domain.UserEntity;
import com.sideagroup.lab.cassandra.dtos.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User dto) {
        UserEntity entity = new UserEntity();
        if (dto.getUserId() != null)
            entity.setUser_id(dto.getUserId());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public User toDto(UserEntity entity) {
        User dto = new User();
        dto.setUserId(entity.getUser_id());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
