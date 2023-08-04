package com.sideagroup.lab.cassandra.mappers;

import com.sideagroup.lab.cassandra.domain.PostsByUserEntity;
import com.sideagroup.lab.cassandra.domain.PostsByUserEntityKey;
import com.sideagroup.lab.cassandra.dtos.PostsByUser;
import org.springframework.stereotype.Component;

@Component
public class PostsByUserMapper {

    public PostsByUserEntity toEntity(PostsByUser dto) {
        PostsByUserEntityKey key = new PostsByUserEntityKey();
        key.setUserId(dto.getUserId());
        if (dto.getPostId() != null)
            key.setPostId(dto.getPostId());

        PostsByUserEntity entity = new PostsByUserEntity();
        entity.setKey(key);
        entity.setText(dto.getText());
        entity.setRoom_id(dto.getRoomId());

        return entity;
    }

    public PostsByUser toDto(PostsByUserEntity entity) {
        PostsByUser dto = new PostsByUser();
        dto.setUserId(entity.getKey().getUserId());
        dto.setPostId(entity.getKey().getPostId());
        dto.setText(entity.getText());
        dto.setRoomId(entity.getRoom_id());
        return dto;
    }
}
