package com.luizalabs.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.wishlist.dto.ResponseDto;
import com.luizalabs.wishlist.dto.user.SignInDto;
import com.luizalabs.wishlist.dto.user.SignInReponseDto;
import com.luizalabs.wishlist.dto.user.SignupDto;
import com.luizalabs.wishlist.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /* Cadastrar um novo usuário */
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signUp(@RequestBody SignupDto signupDto) {
        ResponseDto response = userService.signUp(signupDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /* Login de usuário */
    @PostMapping("/signin")
    public ResponseEntity<SignInReponseDto> signIn(@RequestBody SignInDto signInDto) {
        SignInReponseDto response = userService.signIn(signInDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
