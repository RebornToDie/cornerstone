package com.example.cornerstone.mybatis.repository;

import com.example.cornerstone.mybatis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author reborntodie
 * @date 2019/10/29 10:12
 */
public interface UserRepository extends JpaRepository<User, String> {
}
