package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("createUser method should create User")
    public void createUser_ShouldCreateUser() {
        User expectedUser = new User("1", "Khalid", null);
        when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);

        userService.createUser("Khalid");

        verify(userRepositoryMock, times(1)).save(any(User.class));
    }
}