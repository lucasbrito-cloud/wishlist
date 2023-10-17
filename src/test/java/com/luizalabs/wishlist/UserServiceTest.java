package com.luizalabs.wishlist;

import com.luizalabs.wishlist.dto.ResponseDto;
import com.luizalabs.wishlist.dto.user.SignupDto;
import com.luizalabs.wishlist.exception.WishListException;
import com.luizalabs.wishlist.model.Authentication;
import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.repository.UserRepository;
import com.luizalabs.wishlist.service.AuthenticationService;
import com.luizalabs.wishlist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationService authenticationService;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, authenticationService);
    }

    @Test
    public void testSignUpWithSuccess() {
        SignupDto signupDto = new SignupDto("Lucas", "Brito", "lucasbrito@email.com", "password");
        when(userRepository.existsByEmail(signupDto.getEmail())).thenReturn(false);

        ResponseDto signupResponse = userService.signUp(signupDto);

        assertNotNull(signupResponse);
        assertEquals("User created successfully.", signupResponse.getMessage());

        Mockito.verify(userRepository).save(any(User.class));
        Mockito.verify(authenticationService).saveToken(any(Authentication.class));
    }

    @Test
    public void testSignUpUserExists() {

        SignupDto signupDto = new SignupDto("Lucas", "Brito", "lucasbrito@email.com", "password");

        when(userRepository.existsByEmail(signupDto.getEmail())).thenReturn(true);

        assertThrows(WishListException.class, () -> userService.signUp(signupDto));
        Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
        Mockito.verify(authenticationService, Mockito.never()).saveToken(any(Authentication.class));
    }

}
