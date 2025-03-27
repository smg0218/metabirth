package com.metabirth.service;

import com.metabirth.dao.StudentDao;
import com.metabirth.model.Students;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentDao studentDao;
    private final Connection connection;

    public StudentService(Connection connection) {
        this.studentDao = new StudentDao(connection);
        this.connection = connection;
    }

    public List<Students> getAllStudent() throws SQLException {
        List<Students> students = studentDao.getAllStudents();

        if(students == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return studentDao.getAllStudents();
    }

    public List<Students> getActiveStudents() {
        List<Students> users = studentDao.getActiveStudents();

        if(users == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return studentDao.getActiveStudents();
    }

    public List<Students> getDeleteStudents() {
        List<Students> users = studentDao.getDeleteStudents();

        if(users == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return studentDao.getDeleteStudents();
    }

    public boolean deleteStudent(int studentId) throws SQLException {
        return studentDao.deleteUser(studentId);
    }

    public Boolean registerStudent(Students student) throws SQLException {
        List<Students> existingStudents = studentDao.getAllStudents();
        for (Students students : existingStudents) {
            if (students.getEmail() == student.getEmail()) {
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
            }
        }

        return studentDao.addStudent(student);
    }

    public Students getStudentById(int userId) throws SQLException {
        Students student = studentDao.getStudentById(userId);

        if (student == null) {
            throw new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        return student;
    }

    public Students getStudentByEmail(String studentEmail) {
        Students student = studentDao.getStudentByEmail(studentEmail);

        if (student == null) {
            throw new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        return student;
    }


    public boolean updateStudent(Students student) throws SQLException {
        List<Students> existingStudents = getAllStudent();
        for (Students u : existingStudents) {
            if (u.getEmail().equals(student.getEmail())) {
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
            }
        }

        boolean result = studentDao.updateStudent(student);
        if (!result) {
            throw new SQLException("수정하는 과정에서 오류가 발생되었습니다.");
        }

        // 3️⃣ 업데이트 수행
        return result;
    }
}
