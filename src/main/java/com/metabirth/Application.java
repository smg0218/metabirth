package com.metabirth;

import com.metabirth.config.JDBCConnection;
import com.metabirth.view.AttendanceView;
import com.metabirth.view.StudentView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LMS 관리 시스템 =====");
            System.out.println("1. 학생(Students) 관리");
            System.out.println("2. 출석(Attendances) 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> startStudentManagement(connection);
                case 2 -> startAttendancesManagement(connection);
                case 0 -> {
                    connection.close();
                    System.out.println("🚀 프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("❌ 잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    private static void startAttendancesManagement(Connection connection) {
        AttendanceView attendanceView = new AttendanceView(connection);
        attendanceView.showMenu();
    }

    private static void startStudentManagement(Connection connection) {
        StudentView studentView = new StudentView(connection);
        studentView.showMenu();
    }
}
