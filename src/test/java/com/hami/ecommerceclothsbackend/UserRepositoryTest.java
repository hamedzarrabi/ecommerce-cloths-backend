package com.hami.ecommerceclothsbackend;

import com.hami.Entity.user.Role;
import com.hami.Entity.user.User;
import com.hami.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPasswordString = "hamed3d";
        String encodedPassword = passwordEncoder.encode(rawPasswordString);

        User newUser = new User("hamed zarrabi", "09124431480", "hamed.zarrabi87@gmail.com", encodedPassword);

        User savedUser =  userRepository.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRolesToUser() {
        Long userId = 2L;
        Long roleId = 2L;

        User user = userRepository.findById(userId).get();
        user.addRole(new Role(roleId));

        User updateUser = userRepository.save(user);

        assertThat(updateUser.getRoles());
    }
}
