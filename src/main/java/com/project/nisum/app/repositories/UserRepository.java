package com.project.nisum.app.repositories;

import com.project.nisum.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository is a repository interface for User entities.
 * It extends JpaRepository, providing methods for CRUD operations and more.
 * It also declares two additional methods for checking if a user exists by email and finding a user by email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Checks if a user exists with the given email.
     * @param email The email to check.
     * @return true if a user exists with the given email, false otherwise.
     */
    Boolean existsByEmail(String email);

    /**
     * Finds a user by the given email.
     * @param email The email to find the user by.
     * @return An Optional containing the found user, or an empty Optional if no user was found.
     */
    Optional<User> findByEmail(String email);

}