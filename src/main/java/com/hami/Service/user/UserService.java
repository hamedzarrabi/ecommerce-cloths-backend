package com.hami.Service.user;

import com.hami.Entity.user.User;
import com.hami.Exception.EmailExistsException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    public User register(String name, String email, String phoneNumber, String password) throws EmailExistsException;
    public void deleteUserById(Long userId);
    public List<User> findAllUsers();
    public User findUserById(Long userId);
    public User findByEmail(String email);
    public List<User> findByName(String name);
    public void logOut(HttpServletRequest request, HttpServletResponse response);
}
