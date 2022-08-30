package com.ecwo.bot.telegram.repository;

import com.ecwo.bot.telegram.config.DBConfig;
import com.ecwo.bot.telegram.model.Teacher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
@ContextConfiguration(classes = {DBConfig.class}, loader = AnnotationConfigContextLoader.class)
class ITeacherRepoTest {
    @Autowired
    private TeacherRepo repo;

    @Test
    void injectedComponentsAreNotNull() {
        org.assertj.core.api.Assertions.assertThat(repo).isNotNull();
    }

    @Test
    @DisplayName("1. добавление Teacher ")
    public void insert() {
        Teacher teacher = new Teacher(0, "test1");
        repo.insert(teacher);
    }


    @Test
    void getTeachers() {
        List<Teacher> teachers = repo.getTeachers();
        assertEquals(teachers.size(), 1);
    }

    @Test
    void findByName() {
        List<Teacher> test1 = repo.findByName("test1");
        assertEquals(test1.size(), 1);
    }

    @Test
    @Disabled
    void getById() {
    }


    @Test
    void delete() {
        List<Teacher> test1 = repo.findByName("test1");
        repo.delete(test1.get(0));
    }
}