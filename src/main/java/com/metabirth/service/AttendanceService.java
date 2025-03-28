package com.metabirth.service;

import com.metabirth.dao.AttendanceDao;
import com.metabirth.model.Attendances;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.util.List;

public class AttendanceService {
    private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);
    private final AttendanceDao attendanceDao;
    private final Connection connection;

    public AttendanceService(Connection connection) {
        this.attendanceDao = new AttendanceDao(connection);
        this.connection = connection;
    }

    public List<Attendances> getAllAttendance() throws SQLException {
        List<Attendances> attendance = attendanceDao.getAllAttendances();

        if(attendance == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return attendance;
    }

    public List<Attendances> getCheckInAllAttendances() throws SQLException {
        List<Attendances> attendance = attendanceDao.getCheckInAllAttendances();

        if(attendance == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return attendance;
    }

    public List<Attendances> getCheckOutAllAttendances() throws SQLException {
        List<Attendances> attendance = attendanceDao.getCheckOutAllAttendances();

        if(attendance == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return attendance;
    }

    public List<Attendances> getDateAttendances(Date date) throws SQLException, ParseException {
        List<Attendances> attendance = attendanceDao.getDateAttendances(date);

        if(attendance == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return attendance;
    }

    public List<Attendances> getStudentAttendance(int studentId) throws SQLException {
        List<Attendances> attendance = attendanceDao.getStudentAttendance(studentId);

        if(attendance == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return attendance;
    }
}
