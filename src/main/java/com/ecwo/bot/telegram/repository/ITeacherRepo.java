package com.ecwo.bot.telegram.repository;

import com.ecwo.bot.telegram.model.Teacher;

import java.util.List;

public interface ITeacherRepo {
    List<Teacher> getTeachers();

    List<Teacher> findByName(String  name);

    Teacher getById(int id);

    void insert(Teacher teacher);

    void delete(Teacher teacher);
}
