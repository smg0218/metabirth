package com.metabirth.dao;

import com.metabirth.config.JDBCConnection;
import com.metabirth.model.Students;
import com.metabirth.util.TimeUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {
    private Connection connection;
    private StudentDao studentDao;
    private static int studentId;

    @BeforeEach
    void setUp() {
        try {
            connection = JDBCConnection.getConnection();
            studentDao = new StudentDao(connection);

            Students student = new Students(0, "테스터", "test123!@#", TimeUtil.formatStringDateToSqlDate("2011-02-18"), (byte) 1, "010-1234-5678", "테스트시 테스트구", "test@test.com", false, null, null, null);
            studentDao.addStudent(student);
            Students students = studentDao.getStudentByEmail("test@test.com");
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
    @DisplayName("Student ID로 역할 조회 테스트")
    void getStudentByIdTest() {
        try {

            Students student = studentDao.getStudentById(studentId);
            System.out.println("조회된 학생: " + student);

            Assertions.assertNotNull(student, "조회된 Student 객체가 null이 아니어야 합니다.");
            Assertions.assertEquals(student.getStudentId(), studentId, "조회된 학생의 ID가 삽입한 ID와 일치해야 합니다.");
            Assertions.assertEquals("테스터", student.getStudentName(), "조회된 학생의 이름이 일치해야 합니다.");
            Assertions.assertEquals("test123!@#", student.getPassword(), "조회된 학생의 비밀번호가 일치해야 합니다.");
            Assertions.assertEquals(TimeUtil.formatStringDateToSqlDate("2011-02-18"), student.getBirthDate(), "조회된 학생의 생년월일이 일치해야 합니다.");
            Assertions.assertEquals(1, student.getGender(), "조회된 학생의 성별이 일치해야 합니다.");
            Assertions.assertEquals("010-1234-5678", student.getPhone(), "조회된 학생의 핸드폰 번호가 일치해야 합니다.");
            Assertions.assertEquals("테스트시 테스트구", student.getAddress(), "조회된 학생의 주소가 일치해야 합니다.");
            Assertions.assertEquals("test@test.com", student.getEmail(), "조회된 학생의 이메일이 일치해야 합니다.");

        } catch (ParseException e) {
            Assertions.fail("타입 변환 실패");
        }
    }

    @Test
    @DisplayName("학생 등록 테스트")
    void insertStudent() {
    }

    @AfterEach
    void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            Assertions.fail("트랜잭션 롤백 실패: " + e.getMessage());
        }
    }

}