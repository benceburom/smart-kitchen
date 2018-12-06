package com.example.smartkitchenbackend.serviceTests;

import com.example.smartkitchenbackend.DTOs.authentication.SignUpRequest;
import com.example.smartkitchenbackend.entities.Role;
import com.example.smartkitchenbackend.entities.RoleName;
import com.example.smartkitchenbackend.entities.User;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import com.example.smartkitchenbackend.security.JwtTokenProvider;
import com.example.smartkitchenbackend.services.KitchenService;
import com.example.smartkitchenbackend.services.RoleService;
import com.example.smartkitchenbackend.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "email@email.com";
    private static final String NAME = "Real_Name";
    private static final SignUpRequest signUpRequest = SignUpRequest.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .email(EMAIL)
            .name(NAME)
            .build();

    private UserService userService;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private KitchenService mockKitchenService;
    @Mock
    private RoleService mockRoleService;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private AuthenticationManager mockAuthenticationManager;
    @Mock
    private JwtTokenProvider mockTokenProvider;

    @Before
    public void setUp() {
        userService = new UserService(mockUserRepository,
                mockKitchenService,
                mockRoleService,
                mockPasswordEncoder,
                mockAuthenticationManager,
                mockTokenProvider);
    }

    @Test
    public void returnsApiResponseWithBadRequest_WhenUsernameAlreadyTaken() {
        when(mockUserRepository.existsByUsername(USERNAME)).thenReturn(true);

        ResponseEntity<?> result = userService.registerUser(signUpRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void returnsApiResponseWithBadRequest_WhenEmailAlreadyTaken() {
        when(mockUserRepository.existsByUsername(USERNAME)).thenReturn(false);
        when(mockUserRepository.existsByEmail(EMAIL)).thenReturn(true);

        ResponseEntity<?> result = userService.registerUser(signUpRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void savesCorrectUser_WhenSignUpRequestGiven() {
        when(mockUserRepository.existsByUsername(USERNAME)).thenReturn(false);
        when(mockUserRepository.existsByEmail(EMAIL)).thenReturn(false);
        when(mockPasswordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(mockRoleService.findByName(RoleName.ROLE_USER)).thenReturn(createRole());

        userService.registerUser(signUpRequest);

        verify(mockUserRepository).save(createUser());
    }


    private User createUser() {
        User user = new User();
        user.setName(NAME);
        user.setUsername(USERNAME);
        user.setRoles(Collections.singleton(createRole()));
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        return user;
    }

    private Role createRole() {
        return new Role(RoleName.ROLE_USER);
    }
}
