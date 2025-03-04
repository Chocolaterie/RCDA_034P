package fr.eni.enchere;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.AuthDAO;
import fr.eni.enchere.service.AuthService;
import fr.eni.enchere.service.v2.AuthServiceV2;
import fr.eni.enchere.service.v2.ServiceResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnchereApplicationTests {

	@Autowired
	AuthService authService;

	@Autowired
	AuthServiceV2 authServiceV2;

	@Test
	void contextLoads() {
	}

	@Test
	void authTest() {

		// User existant
		Utilisateur user = authService.login("coach@campus-eni.fr", "{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu");
		assertNotNull(user);

		// User inexistant
		Utilisateur userNotFound = authService.login("admin", "admin");
		assertNull(userNotFound);
	}

	@Test
	void authTestV2() {

		// User existant
		ServiceResponse<Utilisateur> response = authServiceV2.login("coach@campus-eni.fr", "{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu");
		assertEquals(response.code, "200");

		// User inexistant
		ServiceResponse<Utilisateur> responseNotWork = authServiceV2.login("admin", "admin");
		assertEquals(responseNotWork.code, "701");
	}

}
