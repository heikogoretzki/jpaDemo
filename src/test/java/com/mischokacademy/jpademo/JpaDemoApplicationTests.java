package com.mischokacademy.jpademo;

import com.mischokacademy.jpademo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaDemoApplicationTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testUserEntity() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPasswordEncoded("12345");

        entityManager.persist(admin);

        assertNotNull(admin.getId());
        assertTrue(admin.getId()>0);

        User adminFromDB = entityManager.find(User.class, admin.getId());

        assertEquals("admin", adminFromDB.getUsername());
        assertEquals("12345", adminFromDB.getPasswordEncoded());
    }
}
