package com.ecwo.bot.telegram.repository;

import com.ecwo.bot.telegram.exeptions.DBException;
import com.ecwo.bot.telegram.model.Teacher;
import com.ecwo.bot.telegram.model.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TeacherRepo implements ITeacherRepo {
    public static final String SELECT_FROM_ID = "select id,fio from teachers where id=?";
    public static final String SQL_INSERT = "INSERT INTO teachers (fio) VALUES ( ?)";
    private static final String SQL_DELETE = "DELETE FROM teachers WHERE id = ?";
    private static final String SQL_SELECT_LIST = "Select id,fio from teachers";
    private static final String SQL_FIND_LIST = "Select id,fio from teachers where fio like ?";
    protected final JdbcTemplate template;
    protected final static TeacherMapper MAPPER = new TeacherMapper();

    public TeacherRepo(@Qualifier("bot-db") JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Teacher> getTeachers() {
        try {
            return template.query(SQL_SELECT_LIST, MAPPER);
        } catch (DataAccessException exception) {
            throw new DBException(exception.toString());
        }
    }

    @Override
    public List<Teacher> findByName(String name) {
        try {
            return template.query(SQL_SELECT_LIST, MAPPER, name);
        } catch (DataAccessException exception) {
            throw new DBException(exception.toString());
        }
    }

    @Override
    public Teacher getById(int id) throws DBException {
        try {
            return DataAccessUtils.singleResult(
                    template.query(SELECT_FROM_ID, MAPPER, id));
        } catch (DataAccessException e) {
            throw new DBException(e.toString());

        }
    }

    @Override
    public void insert(Teacher teacher) {
        try {
            var rez = //id  serial и генерируется автоматом
                    template.update(SQL_INSERT,
                            teacher.getFio()
                    );
            if (rez != 1) log.trace("TeacherRepository.update() with {} rows inserted", teacher);
            log.info("insert({}) result={}", teacher, rez);
        } catch (DataAccessException e) {
            throw new DBException(e.toString());

        }

    }

    @Override
    public void delete(Teacher teacher) {
        try {
            var rez = //id  serial и генерируется автоматом
                    template.update(SQL_DELETE,
                            teacher.getId()
                    );
            if (rez != 1) log.trace("TeacherRepository.update() with {} rows inserted", teacher);
        } catch (DataAccessException e) {
            throw new DBException(e.toString());

        }
    }
}
