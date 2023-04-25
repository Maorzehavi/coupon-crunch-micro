package com.maorzehavi.userservice.controller;

import com.maorzehavi.userservice.model.ClientType;
import com.maorzehavi.userservice.model.dto.request.UserRequest;
import com.maorzehavi.userservice.model.dto.response.UserResponse;
import com.maorzehavi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(userService.getById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Validated UserRequest userRequest) {
        try {
            return ResponseEntity.ok(userService.createUser(userRequest, ClientType.ADMINISTRATOR).orElseThrow());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
