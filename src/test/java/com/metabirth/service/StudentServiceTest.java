package com.metabirth.service;

import com.metabirth.config.JDBCConnection;
import com.metabirth.dao.StudentDao;
import com.metabirth.model.Students;
import com.metabirth.util.TimeUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private Connection connection;
    private StudentService studentService;
    public static int studentId;
    @BeforeEach
    void setUp() {
        try {
            connection = JDBCConnection.getConnection();
            studentService = new StudentService(connection);

            Students student = new Students(0, "테스터", "test123!@#", TimeUtil.formatStringDateToSqlDate("2011-02-18"), (byte) 1, "010-1234-5678", "테스트시 테스트구", "test@test.com", false, null, null, null);
            studentService.registerStudent(student);
            Students students = studentService.getStudentByEmail("test@test.com");
            studentId = students.getStudentId();
            Assertions.assertNotNull(students, "테스트 케이스 학생을 넣는데 성공해야 합니다!");
            Assertions.assertEquals(student.getEmail(), students.getEmail(), "넣어진 학생 데이터의 이메일은 테스트 케이스로 넣어진 학생의 이메일과 동일해야 합니다!");
        } catch (SQLException e) {
            Assertions.fail("데이터 베이스 연결 실패");
        } catch (ParseException e) {
            Assertions.fail("타입 변환 실패");
        }
    }

    @Test
    @DisplayName("학생 등록 실패 테스트(중복 이메일)")
    void registerStudent() {
        try {
            Students student = new Students(0, "테스터", "test123!@#", TimeUtil.formatStringDateToSqlDate("2011-02-18"), (byte) 1, "010-1234-5678", "테스트시 테스트구", "test@test.com", false, null, null, null);

            Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> studentService.registerStudent(student), "중복 이메일은 넣는데 실패해야 합니다!");
        } catch (ParseException e) {
            Assertions.fail("타입 변환 실패");
        }
    }

    @AfterEach
    void tearDown() {
        String query = "delete from students where student_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);

            int affectedRows = ps.executeUpdate();
            Assertions.assertTrue(affectedRows > 0, "테스트 케이스를 삭제하는데 성공해야 합니다!");
            connection.close();
        } catch (SQLException e) {
            Assertions.fail("트랜잭션 롤백 실패: " + e.getMessage());
        }
    }

}