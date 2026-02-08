package com.education.api.factory;

import com.education.api.model.Student;
import org.springframework.jdbc.core.RowMapper;

public final class StudentMapperFactory {
    private StudentMapperFactory() {}

    public static RowMapper<Student> create() {
        return (rs, rowNum) -> Student.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .gpa(rs.getDouble("gpa"))
                .build();
    }
}
