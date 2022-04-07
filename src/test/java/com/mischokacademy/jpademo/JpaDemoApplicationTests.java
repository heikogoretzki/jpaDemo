package com.mischokacademy.jpademo;

import com.mischokacademy.jpademo.domain.Attendance;
import com.mischokacademy.jpademo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

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

        admin.setPasswordEncoded("54321");
        entityManager.persist(admin);

        adminFromDB = entityManager.find(User.class, admin.getId());

        assertEquals("admin", adminFromDB.getUsername());
        assertEquals("54321", adminFromDB.getPasswordEncoded());

        entityManager.remove(admin);

        adminFromDB = entityManager.find(User.class, adminFromDB.getId());

        assertNull(adminFromDB);
    }

    @Test
    @Transactional
    public void testAttendanceEntity() {
        User user = new User();
        user.setUsername("max.muster");
        user.setPasswordEncoded("12345");

        entityManager.persist(user);

        Attendance attendance = new Attendance();
        attendance.setDate(LocalDateTime.now());
        attendance.setUserId(user.getId());

        entityManager.persist(attendance);

        assertNotNull(user.getId());
        assertNotNull(attendance.getId());

        User userOfAttendance = entityManager.find(User.class, attendance.getUserId());

        assertNotNull(userOfAttendance);
        assertEquals("max.muster", userOfAttendance.getUsername());
    }
}
