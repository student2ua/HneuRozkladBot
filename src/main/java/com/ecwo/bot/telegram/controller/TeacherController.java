package com.ecwo.bot.telegram.controller;

import com.ecwo.bot.telegram.model.Teacher;
import com.ecwo.bot.telegram.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("${app.http.bot}")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping
    public List<Teacher> getTeacherList() {
        log.debug("method >> getTeacherList called");
        return teacherService.getTeachers();
    }
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id) {
        log.debug("method >> getTeacherList called");
        return teacherService.getById(id);
    }

}
