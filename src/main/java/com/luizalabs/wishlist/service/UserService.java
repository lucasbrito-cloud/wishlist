package com.luizalabs.wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.luizalabs.wishlist.dto.ResponseDto;
import com.luizalabs.wishlist.dto.user.SignInDto;
import com.luizalabs.wishlist.dto.user.SignInReponseDto;
import com.luizalabs.wishlist.dto.user.SignupDto;
import com.luizalabs.wishlist.exception.WishListException;
import com.luizalabs.wishlist.exception.AuthenticationException;
import com.luizalabs.wishlist.model.Authentication;
import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private AuthenticationService authenticationService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;

    }

    public ResponseDto signUp(SignupDto signupDto) {
        if (userRepository.existsByEmail(signupDto.getEmail())) {
            throw new WishListException("User already exists.");
        }

        User user = createUser(signupDto);

        userRepository.save(user);

        Authentication authenticationToken = createAuthenticationToken(user);
        authenticationService.saveToken(authenticationToken);

        return new ResponseDto("success", "User created successfully.");
    }

    private User createUser(SignupDto signupDto) {
        String encryptedPassword = hashPassword(signupDto.getPassword());
        return new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedPassword);
    }

    private Authentication createAuthenticationToken(User user) {
        return new Authentication(user);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder hash = new StringBuilder();
            for (byte b : digest) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash the password.");
        }
    }

    public SignInReponseDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());

        if (user == null || !isPasswordValid(user, signInDto.getPassword())) {
            throw new AuthenticationException("Invalid credentials.");
        }

        Authentication token = authenticationService.getToken(user);

        if (token == null) {
            throw new WishListException("Token not found.");
        }

        return new SignInReponseDto(token.getToken());
    }

    private boolean isPasswordValid(User user, String password) {
        String hashedPassword = hashPassword(password);
        return user.getPassword().equals(hashedPassword);
    }
}
