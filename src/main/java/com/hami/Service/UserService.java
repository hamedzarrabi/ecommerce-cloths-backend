package com.hami.Service;

import com.hami.Entity.User;
import com.hami.Exception.EmailExistsException;


import java.util.List;

public interface UserService {
    public User register(String name, String email, String phoneNumber, String password) throws EmailExistsException;
    public void deleteUser(Long userId);
    public List<User> findAllUsers();
    public User findUserById(Long userId);
    public User findByEmail(String email);
}
