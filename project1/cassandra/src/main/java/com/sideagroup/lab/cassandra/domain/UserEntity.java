package com.sideagroup.lab.cassandra.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "users")
public class UserEntity {

    @Id
    @PrimaryKeyColumn(name = "email", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String email;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String password;

    @CassandraType(type = CassandraType.Name.UUID)
    private UUID user_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
}
