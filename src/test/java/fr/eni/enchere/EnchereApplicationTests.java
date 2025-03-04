package fr.eni.enchere;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.service.AuthService;
import fr.eni.enchere.service.ServiceResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnchereApplicationTests {

	@Autowired
	AuthService authService;

	@Test
	void contextLoads() {
	}


	@Test
	void authTest() {

		// User existant
		ServiceResponse<Utilisateur> response = authService.login("coach@campus-eni.fr", "{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu");
		assertEquals(response.code, "200");

		// User inexistant
		ServiceResponse<Utilisateur> responseNotWork = authService.login("admin", "admin");
		assertEquals(responseNotWork.code, "701");
	}

}
