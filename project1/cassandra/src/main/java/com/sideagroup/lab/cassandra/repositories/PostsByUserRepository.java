package com.sideagroup.lab.cassandra.repositories;

import com.sideagroup.lab.cassandra.domain.PostsByUserEntity;
import com.sideagroup.lab.cassandra.domain.PostsByUserEntityKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostsByUserRepository extends CassandraRepository<PostsByUserEntity, PostsByUserEntityKey> {

    public List<PostsByUserEntity> findByKeyUserId(UUID userId);

}
