package com.khanhhoang.repository;

import com.khanhhoang.model.User;
import com.khanhhoang.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.khanhhoang.model.dto.UserDTO (" +
            "u.id, " +
            "u.username" +
            ") " +
            "FROM User u " +
            "WHERE u.username = ?1"
    )
    Optional<UserDTO> findUserDTOByUsername(@Param("username") String username, @Param("id") Long id);


    Boolean existsByUsername(String username);
}
