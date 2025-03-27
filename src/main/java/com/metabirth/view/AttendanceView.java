package com.metabirth.view;

import com.metabirth.model.Attendances;
import com.metabirth.service.AttendanceService;

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
            System.out.println("5. 학생 출석 조회");
            System.out.println("6. 출석 등록");
            System.out.println("7. 출석 수정");
            System.out.println("8. 출석 삭제");
            System.out.println("0. 종료");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllAttendances();
                case 2 -> getCheckInAllAttendances();
                case 3 -> getCheckOutAllAttendances();
                case 4 -> getDateAttendances();
                case 5 -> getAttendanceForStudent();
                case 6 -> addAttendance();
                case 7 -> updateAttendance();
                case 8 -> deleteAttendance();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    /**
     * 전체 출석 조회(Read)
     * - 출석 기록 전체 조회
     */
    private void getAllAttendances() {
        try {
            List<Attendances> attendances = attendanceService.getAllAttendance();

            if (attendances.isEmpty()) {
                System.out.println("등록된 출석 정보가 없습니다.");
            } else {
                System.out.println("\n===== 전체 출석 목록 =====");
                attendances.forEach(student -> System.out.println(student));
            }
        } catch (SQLException e) {
            System.out.println("출석 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    /**
     * 전체 체크인 조회(Read)
     * - 체크인이 있는 기록 전체 조회
     */
    private void getCheckInAllAttendances() {
        try {
            List<Attendances> attendances = attendanceService.getCheckInAllAttendances();

            if (attendances.isEmpty()) {
                System.out.println("등록된 출석 정보가 없습니다.");
            } else {
                System.out.println("\n===== 전체 출석 목록 =====");
                attendances.forEach(student -> System.out.println(student));
            }
        } catch (SQLException e) {
            System.out.println("출석 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    /**
     * 전체 체크아웃 조회(Read)
     * - 체크아웃이 있는 기록 전체 조회
     */
    private void getCheckOutAllAttendances() {
        try {
            List<Attendances> attendances = attendanceService.getCheckOutAllAttendances();

            if (attendances.isEmpty()) {
                System.out.println("등록된 출석 정보가 없습니다.");
            } else {
                System.out.println("\n===== 전체 출석 목록 =====");
                attendances.forEach(student -> System.out.println(student));
            }
        } catch (SQLException e) {
            System.out.println("출석 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    /**
     * 특정 날짜 출석 조회(Read)
     * - 특정 날짜의 출석 기록 조회
     */
    private void getDateAttendances() {

    }

    /**
     * 특정 학생 출석 조회(Read)
     * - 특정 학생의 학생 ID로 출석 기록 조회
     */
    private void getAttendanceForStudent() {

    }

    /**
     * 출석 추가(Create)
     * - 출석 기록 추가
     */
    private void addAttendance() {

    }

    /**
     * 📌 출석 정보 수정 (UPDATE)
     * - 출석 ID를 입력받아 정보를 수정
     */
    private void updateAttendance() {
        System.out.println("미완성입니다.");
    }

    /**
     * 📌 출석 삭제 (DELETE)
     * - 출석 ID를 입력받아 삭제
     */
    private void deleteAttendance() {
        System.out.println("미완성입니다.");
    }
}
