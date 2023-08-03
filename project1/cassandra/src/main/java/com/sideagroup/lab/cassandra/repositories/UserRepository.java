package com.sideagroup.lab.cassandra.repositories;

import com.sideagroup.lab.cassandra.domain.UserEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<UserEntity, String> {
}
