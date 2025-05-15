package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class CredentialsRepositoryTest {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Test
    void checkAdminCredentials_validUsername() {
        // Arrange
        String username = "ADM_bert";
        String password = "123";

        // Act
        Admin admin = credentialsRepository.checkAdminCredentials(username, password);

        // Assert
        assertNotNull(admin, "Admin should not be null for valid credentials");
        assertEquals(username, admin.getUsername(), "Admin username should match");
    }

    @Test
    void checkAdminCredentials_invalidUsername() {
        // Arrange
        String username = "INVALID_ADM_bert";
        String password = "123";

        // Act
        Admin admin = credentialsRepository.checkAdminCredentials(username, password);

        // Assert
        assertNull(admin, "Admin should be null for invalid username");
    }


    @Test
    void checkAdminCredentials_invalidPassword() {
        // Arrange
        String username = "ADM_bert";
        String password = "wrongpassword";

        // Act
        Admin admin = credentialsRepository.checkAdminCredentials(username, password);

        // Assert
        assertNull(admin, "Admin should be null for invalid password");
    }

}