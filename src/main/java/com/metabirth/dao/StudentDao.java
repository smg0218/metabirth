package com.metabirth.dao;

import com.metabirth.model.Students;
import com.metabirth.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private final Connection con;

    public StudentDao(Connection connection) {
        this.con = connection;
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
}
