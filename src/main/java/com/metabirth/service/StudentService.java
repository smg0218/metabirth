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
        List<Students> users = studentDao.getAllStudents();

        if(users == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return studentDao.getAllStudents();
    }
}
