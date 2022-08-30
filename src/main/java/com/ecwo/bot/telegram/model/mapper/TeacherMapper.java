package com.ecwo.bot.telegram.model.mapper;

import com.ecwo.bot.telegram.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        var entity = new Teacher(
                rs.getInt("id"),
                rs.getString("fio")
        );
        log.trace("mapRow(): entity = [{}]", entity);
        return entity;
    }
}
