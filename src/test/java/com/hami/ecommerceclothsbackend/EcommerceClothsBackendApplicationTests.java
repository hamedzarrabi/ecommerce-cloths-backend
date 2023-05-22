package com.hami.ecommerceclothsbackend;

import com.hami.Entity.User;
import com.hami.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class EcommerceClothsBackendApplicationTests {

	@Autowired private UserRepository userRepository;

	@Test
	void testCreateUser() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User newUser = new User();
	}

}
