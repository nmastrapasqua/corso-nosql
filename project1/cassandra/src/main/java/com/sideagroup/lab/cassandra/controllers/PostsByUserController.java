package com.sideagroup.lab.cassandra.controllers;

import com.sideagroup.lab.cassandra.domain.PostsByUserEntity;
import com.sideagroup.lab.cassandra.dtos.PostsByUser;
import com.sideagroup.lab.cassandra.mappers.PostsByUserMapper;
import com.sideagroup.lab.cassandra.repositories.PostsByUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Fetch all posts")
    @GetMapping
    public ResponseEntity<List<PostsByUser>> findAll() {
        List<PostsByUser> result =  repo.findAll().stream().map(item -> mapper.toDto(item)).toList();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Fetch all posts by user userId")
    @GetMapping("{userId}")
    public ResponseEntity<List<PostsByUser>> findByUserId(@PathVariable(value = "userId") String userId) {

        List<PostsByUserEntity> entities = repo.findByKeyUserId(UUID.fromString(userId));

        return ResponseEntity.ok( entities.stream().map(item -> mapper.toDto(item)).toList() );
    }

    @Operation(summary = "Creates a new post")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostsByUser> create(@RequestBody PostsByUser body) {
        PostsByUserEntity entity = mapper.toEntity(body);
        PostsByUserEntity newEntity = repo.save(entity);
        return ResponseEntity.ok(mapper.toDto(newEntity));
    }

    @Operation(summary = "Deletes all posts")
    @ApiResponse(responseCode = "204", description = "Posts was successful deleted",
            content = @Content(schema = @Schema(implementation = Void.class)))
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
