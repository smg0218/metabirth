package com.metabirth.dao;

import com.metabirth.model.Attendances;
import com.metabirth.util.PropertiesUtil;
import com.metabirth.util.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    private final Connection con;
    private final String propertiesName = "query.attendance";

    public AttendanceDao(Connection connection) {
        this.con = connection;
    }

    public List<Attendances>getAllAttendances(){
        List<Attendances> Attendances = new ArrayList<>();
        String fileName = PropertiesUtil.getProperty(propertiesName);
        QueryUtil.loadQueries(fileName);
        String query = QueryUtil.getQuery("getAllAttendances");

        try(PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Attendances.add(new Attendances(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getBoolean("attendance_status"),
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
}
