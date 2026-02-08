package com.education.api.repository;


import com.education.api.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Student> mapper = (rs, rowNum) -> new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("age"),
            rs.getDouble("gpa")
    );

    public List<Student> findAll() {
        return jdbc.query("SELECT * FROM students ORDER BY id", mapper);
    }

    public Optional<Student> findById(int id) {
        List<Student> list = jdbc.query("SELECT * FROM students WHERE id = ?", mapper, id);
        return list.stream().findFirst();
    }

    public int create(Student s) {
        return jdbc.update(
                "INSERT INTO students(name, age, gpa) VALUES(?, ?, ?)",
                s.getName(), s.getAge(), s.getGpa()
        );
    }

    public int updateGpa(int id, double gpa) {
        return jdbc.update("UPDATE students SET gpa = ? WHERE id = ?", gpa, id);
    }

    public int deleteById(int id) {
        return jdbc.update("DELETE FROM students WHERE id = ?", id);
    }
}
