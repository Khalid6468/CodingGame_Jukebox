package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Optional;

import com.crio.jukebox.entities.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        final HashMap<String, User> userMap = new HashMap<String, User>() {
            {
                put("user1", new User("user1", "Walter White", null));
                put("user2", new User("user2", "Gustavo Fring", null));
                put("user3", new User("user3", "Jesse Pinkman", null));
            }
        };
        userRepository = new UserRepository(userMap);
    }

    @Test
    @DisplayName("save method create and return new User")
    public void saveUser() {
        User expectedUser = new User("1", "Mike", null);
        User actualUser = userRepository.save(expectedUser);
        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    @DisplayName("getById method should return User Given userId")
    public void getById_ShouldReturnUser_GivenUserId() {
        String expectedUserId = "user3";
        Optional<User> actualUser = userRepository.getById(expectedUserId);
        Assertions.assertEquals(expectedUserId, actualUser.get().getId());
    }

    @Test
    @DisplayName("getById method should return Optional Empty")
    public void getById_ShouldReturnEmptyOptional_GivenUserId() {
        String expectedId = "user5";
        Optional<User> actualUser = userRepository.getById(expectedId);
        Assertions.assertTrue(actualUser.isEmpty());
    }
}