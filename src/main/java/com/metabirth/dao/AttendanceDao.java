package com.metabirth.dao;

import com.metabirth.model.Attendances;
import com.metabirth.util.PropertiesUtil;
import com.metabirth.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    private final Connection con;
    private final String propertiesName = "query.attendance";

    public AttendanceDao(Connection connection) {
        this.con = connection;
        String fileName = PropertiesUtil.getProperty(propertiesName);
        QueryUtil.loadQueries(fileName);
    }

    public List<Attendances>getAllAttendances(){
        List<Attendances> Attendances = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllAttendances");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Attendances.add(new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getByte("attendance_status"),
                        rs.getTimestamp("checkin_time") != null ? rs.getTimestamp("checkin_time").toLocalDateTime() : null,
                        rs.getTimestamp("checkout_time") != null ? rs.getTimestamp("checkout_time").toLocalDateTime() : null,
                        rs.getDate("attendance_date"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Attendances;
    }

    public List<Attendances> getCheckInAllAttendances() {
        List<Attendances> Attendances = new ArrayList<>();
        String query = QueryUtil.getQuery("getCheckInAllAttendances");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Attendances.add(new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getByte("attendance_status"),
                        rs.getTimestamp("checkin_time") != null ? rs.getTimestamp("checkin_time").toLocalDateTime() : null,
                        rs.getTimestamp("checkout_time") != null ? rs.getTimestamp("checkout_time").toLocalDateTime() : null,
                        rs.getDate("attendance_date"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Attendances;
    }

    public List<Attendances> getCheckOutAllAttendances() {
        List<Attendances> Attendances = new ArrayList<>();
        String query = QueryUtil.getQuery("getCheckOutAllAttendances");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Attendances.add(new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getByte("attendance_status"),
                        rs.getTimestamp("checkin_time") != null ? rs.getTimestamp("checkin_time").toLocalDateTime() : null,
                        rs.getTimestamp("checkout_time") != null ? rs.getTimestamp("checkout_time").toLocalDateTime() : null,
                        rs.getDate("attendance_date"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Attendances;
    }

    public List<Attendances> getDateAttendances(Date date) {
        List<Attendances> Attendances = new ArrayList<>();
        String query = QueryUtil.getQuery("getDateAttendances");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Attendances.add(new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getByte("attendance_status"),
                        rs.getTimestamp("checkin_time") != null ? rs.getTimestamp("checkin_time").toLocalDateTime() : null,
                        rs.getTimestamp("checkout_time") != null ? rs.getTimestamp("checkout_time").toLocalDateTime() : null,
                        rs.getDate("attendance_date"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Attendances;
    }

    public List<Attendances> getStudentAttendance(int studentId) {
        List<Attendances> Attendances = new ArrayList<>();
        String query = QueryUtil.getQuery("getStudentAttendance");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Attendances.add(new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getByte("attendance_status"),
                        rs.getTimestamp("checkin_time") != null ? rs.getTimestamp("checkin_time").toLocalDateTime() : null,
                        rs.getTimestamp("checkout_time") != null ? rs.getTimestamp("checkout_time").toLocalDateTime() : null,
                        rs.getDate("attendance_date"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Attendances;
    }

    public Boolean addAttendance(Attendances attendance) {
        String query = QueryUtil.getQuery("addAttendance");

        try (PreparedStatement psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            psmt.setInt(1, attendance.getStudentId());
            psmt.setByte(2, attendance.getAttendanceStatus());
            if(attendance.getCheckinTime() != null)
                psmt.setTimestamp(3, Timestamp.valueOf(attendance.getCheckinTime()));
            else
                psmt.setNull(3, java.sql.Types.TIMESTAMP);
            if(attendance.getCheckoutTime() != null)
                psmt.setTimestamp(4, Timestamp.valueOf(attendance.getCheckoutTime()));
            else
                psmt.setNull(4, java.sql.Types.TIMESTAMP);
            psmt.setDate(5, attendance.getAttendanceDate());
            psmt.setBoolean(6, attendance.isStatus());

            int affectedRows = psmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("학생 추가 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return false;
    }

    public Attendances getAttendanceById(int attendanceId) {
        String query = QueryUtil.getQuery("getAttendanceById");
        Attendances attendance = null;

        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setInt(1, attendanceId);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                attendance = new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getByte("attendance_status"),
                        rs.getTimestamp("checkin_time") != null ? rs.getTimestamp("checkin_time").toLocalDateTime() : null,
                        rs.getTimestamp("checkout_time") != null ? rs.getTimestamp("checkout_time").toLocalDateTime() : null,
                        rs.getDate("attendance_date"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            System.out.println("학생 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return attendance;
    }

    public boolean updateAttendance(Attendances attendance) {
        String query = QueryUtil.getQuery("updateAttendance");

        try (PreparedStatement psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            psmt.setByte(1, attendance.getAttendanceStatus());
            if(attendance.getCheckinTime() != null)
                psmt.setTimestamp(2, Timestamp.valueOf(attendance.getCheckinTime()));
            else
                psmt.setNull(2, java.sql.Types.TIMESTAMP);
            if(attendance.getCheckoutTime() != null)
                psmt.setTimestamp(3, Timestamp.valueOf(attendance.getCheckoutTime()));
            else
                psmt.setNull(3, java.sql.Types.TIMESTAMP);
            psmt.setDate(4, attendance.getAttendanceDate());
            psmt.setInt(5, attendance.getAttendanceId());

            int affectedRows = psmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("학생 추가 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return false;
    }
}
