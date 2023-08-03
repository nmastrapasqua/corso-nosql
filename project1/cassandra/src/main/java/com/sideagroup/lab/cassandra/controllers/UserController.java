package com.sideagroup.lab.cassandra.controllers;

import com.sideagroup.lab.cassandra.domain.UserEntity;
import com.sideagroup.lab.cassandra.dtos.User;
import com.sideagroup.lab.cassandra.mappers.UserMapper;
import com.sideagroup.lab.cassandra.repositories.UserRepository;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;


/**
 * Swagger
 *
 * http://localhost:8080/swagger-ui/index.html
 */
@Tag(name = "Users", description = "Users management APIs")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Operation(summary = "Returns all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all users",
                    content = { @Content(mediaType = "application/json",
                            array =  @ArraySchema(schema = @Schema(implementation = User.class)) ) })})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        List<User> result =  userRepository.findAll().stream().map(item -> userMapper.toDto(item)).toList();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Retrieves a user by email")
    @GetMapping("{email}")
    public ResponseEntity<User> findById(@PathVariable(value = "email") String email) {
        Optional<UserEntity> e = userRepository.findById(email);
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(e.get()));
    }

    @Operation(summary = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successful created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> create(@RequestBody User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity newEntity = userRepository.save(entity);
        return ResponseEntity.ok(userMapper.toDto(newEntity));
    }

    @Operation(summary = "Updates an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successful updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @PutMapping(path = "{email}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable(value = "email") String email) {
        user.setEmail(email);
        UserEntity entity = userMapper.toEntity(user);
        UserEntity newEntity = userRepository.save(entity);
        return ResponseEntity.ok(userMapper.toDto(newEntity));
    }


    @Operation(summary = "Deletes an existing user")
    @ApiResponse(responseCode = "204", description = "User was successful deleted",
            content = @Content(schema = @Schema(implementation = Void.class)))
    @DeleteMapping("{email}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "email") String email) {
        if (!userRepository.existsById(email)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
