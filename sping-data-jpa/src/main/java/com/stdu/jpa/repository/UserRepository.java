package com.stdu.jpa.repository;

import com.stdu.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
    List<User> findByFirstName(String name);
}
