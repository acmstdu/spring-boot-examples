package com.stdu.mybatis.controller;

import com.stdu.mybatis.mapper.UserMapper;
import com.stdu.mybatis.model.User;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Resource
    UserMapper userMapper;

    @GetMapping
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userMapper.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userMapper.findById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User newUser, @PathVariable Long id) {
        userMapper.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setDescription(newUser.getDescription());
            userMapper.save(user);
            return user;
        }).orElseGet(() -> {
            newUser.setId(id);
            userMapper.save(newUser);
            return newUser;
        });
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userMapper.deleteById(id);
    }

    @GetMapping("/search/findByFirstName")
    public List<User> findUsersByFirstName(@Param("name") String name) {
        return userMapper.findByFirstName(name);
    }
}
