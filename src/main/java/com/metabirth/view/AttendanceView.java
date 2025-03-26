package com.metabirth.view;

import com.metabirth.model.Attendances;
import com.metabirth.model.Students;
import com.metabirth.service.AttendanceService;
import com.metabirth.service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AttendanceView {
    private final AttendanceService attendanceService;
    private Scanner scanner;

    public AttendanceView(Connection connection) {
        this.attendanceService = new AttendanceService(connection);
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== 출석 관리 시스템 =====");
            System.out.println("1. 전체 출석 조회");
            System.out.println("2. 입실 조회");
            System.out.println("3. 퇴실 조회");
            System.out.println("4. 특정 날짜 출석 조회");
            System.out.println("5. 출석 등록");
            System.out.println("6. 학생 출석 조회 (Student ID)");
            System.out.println("7. 출석 수정");
            System.out.println("8. 출석 삭제");
            System.out.println("0. 종료");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllAttendances();
                case 2 -> getDeleteAttendance();
                case 3 -> registerAttendance();
                case 4 -> getAttendanceById();
                case 5 -> updateAttendance();
                case 6 -> deleteAttendance();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    private void getAllAttendances() {
        try {
            List<Attendances> attendances = attendanceService.getAllAttendance();

            if (attendances.isEmpty()) {
                System.out.println("등록된 학생이 없습니다.");
            } else {
                System.out.println("\n===== 전체 학생 목록 =====");
                attendances.forEach(student -> System.out.println(student));
            }
        } catch (SQLException e) {
            System.out.println("학생 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    private void getDeleteAttendance() {
        System.out.println("미완성입니다.");
    }

    private void getAttendanceById() {
        System.out.println("미완성입니다.");
    }


    /**
     * 📌 사용자 등록 (CREATE)
     * - 사용자 정보를 입력받아 새로운 사용자를 등록
     */
    private void registerAttendance() {
        System.out.println("미완성입니다.");
    }

    /**
     * 📌 사용자 정보 수정 (UPDATE)
     * - 사용자 ID를 입력받아 정보를 수정
     */
    private void updateAttendance() {
        System.out.println("미완성입니다.");
    }

    /**
     * 📌 사용자 삭제 (DELETE)
     * - 사용자 ID를 입력받아 삭제
     */
    private void deleteAttendance() {
        System.out.println("미완성입니다.");
    }
}
