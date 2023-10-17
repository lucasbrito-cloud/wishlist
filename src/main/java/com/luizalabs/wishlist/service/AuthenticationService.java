package com.luizalabs.wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.wishlist.exception.AuthenticationException;
import com.luizalabs.wishlist.model.Authentication;
import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.repository.AuthenticationRepository;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    public void saveToken(Authentication authenticationToken) {
        authenticationRepository.save(authenticationToken);
    }

    public Authentication getToken(User user) {
        return authenticationRepository.findByUser(user);
    }

    public User getUser(String token) {
        final Authentication authenticationToken = authenticationRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            return null;
        }

        return authenticationToken.getUser();
    }

    public void authenticate(String token) {
        validateTokenPresence(token);
        validateTokenValidity(token);
    }

    private void validateTokenPresence(String token) {
        if (Objects.isNull(token)) {
            throw new AuthenticationException("Token is not present");
        }
    }

    private void validateTokenValidity(String token) {
        User user = getUser(token);
        if (Objects.isNull(user)) {
            throw new AuthenticationException("Token is not valid");
        }
    }
}
