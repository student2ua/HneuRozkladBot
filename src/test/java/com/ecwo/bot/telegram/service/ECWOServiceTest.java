package com.ecwo.bot.telegram.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ECWOServiceTest {
    @Autowired
    private ECWOService service;

    @Test
    void getFaculties() {
        Map<Integer, String> faculties = service.getFaculties();
        Assertions.assertNotNull(faculties);
    }

    @Test
    void getFaculty() {
    }

    @Test
    void getListSpeciality() {
    }

    @Test
    void getSpeciality() {
    }
}