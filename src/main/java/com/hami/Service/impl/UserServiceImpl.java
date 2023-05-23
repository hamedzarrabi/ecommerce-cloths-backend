package com.hami.Service.impl;

import com.hami.Entity.Role;
import com.hami.Entity.User;
import com.hami.Exception.EmailExistsException;
import com.hami.Repository.RoleRepository;
import com.hami.Repository.UserRepository;
import com.hami.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(String name, String email, String phoneNumber, String password) {

        User user = new User();

        Role role = roleRepository.findByName("ROLE_USER").get();

        if (userRepository.existsByEmail(email)) {
            throw new EmailExistsException("User", "email", email);
        }

        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.addRole(role);

        System.out.println("Register User Successfully!");

        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
