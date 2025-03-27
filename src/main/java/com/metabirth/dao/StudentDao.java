package com.metabirth.dao;

import com.metabirth.model.Students;
import com.metabirth.util.PropertiesUtil;
import com.metabirth.util.QueryUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private final Connection con;
    private final String propertiesName = "query.student";

    public StudentDao(Connection connection) {
        this.con = connection;
        String fileName = PropertiesUtil.getProperty(propertiesName);
        QueryUtil.loadQueries(fileName);
    }

    public List<Students>getAllStudents(){
        List<Students> students = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllStudents");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                students.add(new Students(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("password"),
                        rs.getDate("birth_date"),
                        rs.getByte("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public List<Students> getDeleteStudents() {
        List<Students> students = new ArrayList<>();
        String query = QueryUtil.getQuery("getDeleteStudents");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                students.add(new Students(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("password"),
                        rs.getDate("birth_date"),
                        rs.getByte("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public Boolean addStudent(Students student) {
        String query = QueryUtil.getQuery("addStudent");

        try (PreparedStatement psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            psmt.setString(1, student.getStudentName());
            psmt.setString(2, student.getPassword());
            psmt.setDate(3, student.getBirthDate());
            psmt.setString(4, student.getEmail());
            psmt.setByte(5, student.getGender());
            psmt.setString(6, student.getPhone());
            psmt.setString(7, student.getAddress());
            psmt.setBoolean(8, student.isStatus());

            int affectedRows = psmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Students getStudentById(int userId) {
        String query = QueryUtil.getQuery("getStudentById");
        Students student = null;

        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setInt(1, userId);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                student = new Students(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("password"),
                        rs.getDate("birth_date"),
                        rs.getByte("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                        rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean updateStudent(Students student) {
        String query = QueryUtil.getQuery("updateStudent");

        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, student.getStudentName());
            psmt.setString(2, student.getPassword());
            psmt.setDate(3, student.getBirthDate());
            psmt.setString(4, student.getEmail());
            psmt.setByte(5, student.getGender());
            psmt.setString(6, student.getPhone());
            psmt.setString(7, student.getAddress());
            psmt.setInt(8, student.getStudentId());

            int affectedRows = psmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int studentId) {
        String query = QueryUtil.getQuery("deleteStudent");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
