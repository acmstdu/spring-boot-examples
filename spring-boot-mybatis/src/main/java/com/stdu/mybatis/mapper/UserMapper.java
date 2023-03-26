package com.stdu.mybatis.mapper;

import com.stdu.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(Long id);

    @Insert("INSERT INTO users (first_name ,last_name, description) VALUES (#{firstName}, #{lastName}, #{description})")
    void save(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(Long id);

    @Select("SELECT * FROM users WHERE first_name = #{name}")
    List<User> findByFirstName(String name);
}
