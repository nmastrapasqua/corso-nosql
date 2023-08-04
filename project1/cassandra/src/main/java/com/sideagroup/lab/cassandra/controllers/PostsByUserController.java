package com.sideagroup.lab.cassandra.controllers;

import com.sideagroup.lab.cassandra.domain.PostsByUserEntity;
import com.sideagroup.lab.cassandra.dtos.PostsByUser;
import com.sideagroup.lab.cassandra.mappers.PostsByUserMapper;
import com.sideagroup.lab.cassandra.repositories.PostsByUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Posts", description = "Posts management APIs")
@RestController
@RequestMapping("/api/v1/posts")
public class PostsByUserController {

    @Autowired
    private PostsByUserRepository repo;

    @Autowired
    private PostsByUserMapper mapper;

    @Operation(summary = "Fetch all posts by user userId")
    @GetMapping("{userId}")
    public ResponseEntity<List<PostsByUser>> findByUserId(@PathVariable(value = "userId") String userId) {

        List<PostsByUserEntity> entities = repo.findByKeyUserId(UUID.fromString(userId));

        return ResponseEntity.ok( entities.stream().map(item -> mapper.toDto(item)).toList() );
    }


}
