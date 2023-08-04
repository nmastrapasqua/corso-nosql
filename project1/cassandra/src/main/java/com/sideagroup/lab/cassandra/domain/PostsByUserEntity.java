package com.sideagroup.lab.cassandra.domain;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "posts_by_user")
public class PostsByUserEntity {

    @PrimaryKey
    private PostsByUserEntityKey key;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String room_id;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String text;

    public PostsByUserEntityKey getKey() {
        return key;
    }

    public void setKey(PostsByUserEntityKey key) {
        this.key = key;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
