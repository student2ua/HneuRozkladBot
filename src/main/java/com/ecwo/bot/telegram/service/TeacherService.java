package com.ecwo.bot.telegram.service;

import com.ecwo.bot.telegram.model.Teacher;
import com.ecwo.bot.telegram.repository.ITeacherRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService extends BaseService {
    protected final ITeacherRepo repo;
    public List<Teacher> getTeachers() {
        log.trace("#### getTeachers() - working");
        return wrapResults(repo.getTeachers());
    }
    public List<Teacher> getTeachersByName(String name) {
        log.trace("#### getTeachersByName() - working");
        return wrapResults(repo.findByName(name));
    }
    public Teacher getById(int id){
        log.trace("#### getById() [id={}]", id);
        return wrapResult(repo.getById(id));
    }
    public void insert(Teacher entity) {
        log.trace("#### insert() [entity={}]", entity);
        repo.insert(entity);
    }
    public void delete(Teacher entity) {
        log.trace("#### delete() [entity={}]", entity);
        repo.delete(entity);
    }
}
