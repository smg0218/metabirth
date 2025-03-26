package com.metabirth.view;

import com.metabirth.model.Students;
import com.metabirth.service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final StudentService studentService;
    private Scanner scanner;

    public StudentView(Connection connection) {
        this.studentService = new StudentService(connection);
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== 학생 관리 시스템 =====");
            System.out.println("1. 전체 학생 조회");
            System.out.println("2. 삭제 학생 조회");
            System.out.println("3. 학생 등록");
            System.out.println("4. 학생 조회 (ID)");
            System.out.println("5. 학생 수정");
            System.out.println("6. 학생 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllStudents();
                case 2 -> getDeleteStudents();
                case 3 -> registerStudent();
                case 4 -> getStudentById();
                case 5 -> updateStudent();
                case 6 -> deleteStudent();
                case 0 -> {
                    System.out.println("메인 화면으로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    private void getAllStudents() {
        try {
            List<Students> students = studentService.getAllStudent();

            if (students.isEmpty()) {
                System.out.println("등록된 학생이 없습니다.");
            } else {
                System.out.println("\n===== 전체 학생 목록 =====");
                students.forEach(student -> System.out.println(student));
            }
        } catch (SQLException e) {
            System.out.println("학생 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    private void getDeleteStudents() {
        System.out.println("미완성입니다.");
    }

    private void getStudentById() {
        System.out.println("미완성입니다.");
    }


    /**
     * 📌 사용자 등록 (CREATE)
     * - 사용자 정보를 입력받아 새로운 사용자를 등록
     */
    private void registerStudent() {
        System.out.println("미완성입니다.");
    }

    /**
     * 📌 사용자 정보 수정 (UPDATE)
     * - 사용자 ID를 입력받아 정보를 수정
     */
    private void updateStudent() {
        System.out.println("미완성입니다.");
    }

    /**
     * 📌 사용자 삭제 (DELETE)
     * - 사용자 ID를 입력받아 삭제
     */
    private void deleteStudent() {
        System.out.println("미완성입니다.");
    }
}
