package com.stdu.jpa.controller;

import com.stdu.jpa.entity.User;
import com.stdu.jpa.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Resource
    UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setDescription(newUser.getDescription());
            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/search/findByFirstName")
    public List<User> findUsersByFirstName(@Param("name") String name) {
        return userRepository.findByFirstName(name);
    }
}
