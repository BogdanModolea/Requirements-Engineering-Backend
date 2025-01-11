package com.example.internship.repository;

import com.example.internship.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    /**
     * get user by username
     * @param username
     * @return
     */
    Optional<UserInfo> findByName(String username);

    @Query("SELECT u.roles " +
            "FROM UserInfo u " +
            "WHERE u.name = ?1")
    String getRoleByUsername(String username);
}
