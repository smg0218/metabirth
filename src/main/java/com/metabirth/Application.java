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
            System.out.println("\n===== 메타벌스 아카데미 관리 시스템 =====");
            System.out.println("1. 학생(Students) 관리");
            System.out.println("2. 출석(Attendances) 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> startStudentManagement(connection);
                case 2 -> startAttendancesManagement(connection);
                case 9 -> JDBCConnection.printConnectionPoolStatus();
                case 0 -> {
                    connection.close();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다! 다시 입력해주세요.");
            }
        }
    }

    /**
     * 학생 관리 프로그램
     * @param connection : DB가 연결된 connection
     */
    private static void startStudentManagement(Connection connection) {
        StudentView studentView = new StudentView(connection);
        studentView.showMenu();
    }

    /**
     * 출석 관리 프로그램
     * @param connection : DB가 연결된 connection
     */
    private static void startAttendancesManagement(Connection connection) {
        AttendanceView attendanceView = new AttendanceView(connection);
        attendanceView.showMenu();
    }
}
