package com.hami.Controller.userController;

import com.hami.DTO.userDto.RegisterDto;
import com.hami.Entity.user.User;

import com.hami.Exception.EmailExistsException;
import com.hami.Service.user.UserService;
import com.hami.security.AuthRequest;
import com.hami.security.AuthResponse;
import com.hami.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@EnableWebMvc
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterDto registerDto) throws EmailExistsException {

        User newUser = userService.register(registerDto.getName(), registerDto.getEmail(), registerDto.getPhoneNumber(), registerDto.getPassword());
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = (User) authenticate.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getId(), user.getEmail(), accessToken, user.getRoles().toString().replace("[", "").replace("]", ""), user.getName(), user.getPhoneNumber());

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logOut(HttpServletRequest request, HttpServletResponse response) {
        userService.logOut(request, response);

        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> allUsers() {
        List<User> user = userService.findAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable(name = "email") String email) {
        User user = userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<User>> findUserByName(@PathVariable(name = "name") String name) {
        List<User> user = userService.findByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @Transactional
    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable(name = "userId") Long  userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
