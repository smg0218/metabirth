package com.metabirth.view;

import com.metabirth.exception.InvalidDateException;
import com.metabirth.model.Students;
import com.metabirth.service.StudentService;
import com.metabirth.util.PatternUtil;
import com.metabirth.util.TimeUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final StudentService studentService;
    private Scanner scanner;

    public StudentView(Connection connection) {
        this.studentService = new StudentService(connection);
        scanner = new Scanner(System.in);
    }

    /**
     * 학생 관리 화면
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n===== 학생 관리 시스템 =====");
            System.out.println("1. 전체 학생 조회");
            System.out.println("2. 활성화 학생 조회");
            System.out.println("3. 삭제 학생 조회");
            System.out.println("4. 단일 학생 조회");
            System.out.println("5. 학생 등록");
            System.out.println("6. 학생 수정");
            System.out.println("7. 학생 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllStudents();
                case 2 -> getActiveStudents();
                case 3 -> getDeleteStudents();
                case 4 -> getStudentById();
                case 5 -> registerStudent();
                case 6 -> updateStudent();
                case 7 -> deleteStudent();
                case 0 -> {
                    System.out.println("메인 화면으로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    /**
     * 학생 전체 조회(Read)
     * - 학생 전체 목록 조회
     */
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

    /**
     * 활성 학생만 조회(Read)
     * - 활성화 상태인 학생 목록 조회
     */
    private void getActiveStudents() {
        List<Students> students = studentService.getActiveStudents();

        if (students.isEmpty()) {
            System.out.println("활성화된 학생이 없습니다.");
        } else {
            System.out.println("\n===== 활성 학생 전체 목록 =====");
            students.forEach(student -> System.out.println(student));
        }
    }

    /**
     * 비활성화(삭제) 학생만 조회(Read)
     * - 비활성화 상태인 학생 목록 조회
     */
    private void getDeleteStudents() {
        List<Students> students = studentService.getDeleteStudents();

        if (students.isEmpty()) {
            System.out.println("삭제한 학생이 없습니다.");
        } else {
            System.out.println("\n===== 삭제 학생 전체 목록 =====");
            students.forEach(student -> System.out.println(student));
        }
    }

    /**
     * 단일 학생 조회(Read)
     * - 학생 ID 혹은 Email을 입력받아 단일 학생 조회
     */
    private void getStudentById() {
        while (true) {
            try {
                Students student = null;
                System.out.println("1. 학생 ID로 조회");
                System.out.println("2. 학생 Email로 조회");
                System.out.println("0. 뒤로가기");
                System.out.print("선택하세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.print("조회할 학생 ID를 입력하세요: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine();
                        student = studentService.getStudentById(studentId);
                    }
                    case 2 -> {
                        System.out.print("조회할 학생 이메일을 입력하세요: ");
                        String studentEmail = scanner.nextLine();
                        student = studentService.getStudentByEmail(studentEmail);
                    }
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                        continue;
                    }
                }
            System.out.println("\n===== 학생 정보 =====");
            System.out.println(student);
            } catch (SQLException e) {
                System.out.println("학생 조회 중 오류가 발생했습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 📌 학생 등록 (CREATE)
     * - 학생 정보를 입력받아 새로운 학생을 등록
     */
    private void registerStudent() {
        String studentName, birthDate, gender, phone, email, address, password;
        System.out.print("학생 이름: ");
        studentName = scanner.nextLine();

        try {
            System.out.print("생년월일(yyyy-mm-dd): ");
            birthDate = scanner.nextLine();

            Date sqlDate = TimeUtil.formatStringDateToSqlDate(birthDate);

            while(true) {
                System.out.print("성별(남,여): ");
                gender = scanner.nextLine();

                if(gender.equals("남") || gender.equals("여"))
                    break;
                else
                    System.out.println("성별은 [남] 또는 [여]만 입력할 수 있습니다! 다시 입력해주세요.");
            }

            while(true) {
                System.out.print("전화번호(010-xxxx-xxxx): ");
                phone = scanner.nextLine();

                if(PatternUtil.checkPhonePattern(phone))
                    break;
                else
                    System.out.println("전화번호는 [010-xxxx-xxxx]와 같은 형태로 입력해야 합니다!");
            }

            System.out.print("이메일: ");
            email = scanner.nextLine();

            System.out.print("비밀번호: ");
            password = scanner.nextLine();

            System.out.print("주소: ");
            address = scanner.nextLine();

            Students student = new Students(
                    0, studentName, password,
                    sqlDate,
                    convertGender(gender),
                    phone, address, email,
                    false, null, null, null);

            Boolean success = studentService.registerStudent(student);

            if(success) {
                System.out.println("학생이 성공적으로 추가되었습니다.");
            } else {
                System.out.println("학생 등록에 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("학생 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("존재하지 않는 날짜이거나 날짜 변환 도중 문제가 발생했습니다!");
        }
    }

    /**
     * 📌 학생 정보 수정 (UPDATE)
     * - 학생 ID 혹은 이메일을 입력받아 정보를 수정
     */
    private void updateStudent() {
        while (true) {
            System.out.println("1. 학생 ID로 변경");
            System.out.println("2. 학생 Email로 변경");
            System.out.println("0. 뒤로가기");
            System.out.print("선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                Students student = null;
                switch (choice) {
                    case 1 -> {
                        System.out.print("수정할 학생 ID를 입력하세요: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine();
                        student = studentService.getStudentById(studentId);
                        break;
                    }
                    case 2 -> {
                        System.out.print("수정할 학생 이메일을 입력하세요: ");
                        String studentEmail = scanner.nextLine();
                        student = studentService.getStudentByEmail(studentEmail);
                        break;
                    }
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                        continue;
                    }
                }
                Students updateStudent = new Students(student);

                System.out.print("새로운 학생 이름[엔터 시 기존 이름 유지]: ");
                String studentName = scanner.nextLine();
                if(!studentName.isEmpty())
                    updateStudent.setStudentName(studentName);

                while(true) {
                    System.out.print("새로운 생년월일(yyyy-mm-dd)[엔터 시 기존 생년월일 유지]: ");
                    String birthDate = scanner.nextLine();
                    if(!birthDate.isEmpty())
                        updateStudent.setBirthDate(TimeUtil.formatStringDateToSqlDate(birthDate));

                    break;
                }

                while(true) {
                    System.out.print("새로운 성별(남,여)[엔터 시 기존 성별 유지]: ");
                    String gender = scanner.nextLine();
                    if(gender.isEmpty() || gender.equals("남") || gender.equals("여")) {
                        if(!studentName.isEmpty())
                            updateStudent.setGender(convertGender(gender));

                        break;
                    }
                    else
                        System.out.println("성별은 [남] 또는 [여]만 입력할 수 있습니다! 다시 입력해주세요.");
                }

                while(true) {
                    System.out.print("새로운 전화번호(010-xxxx-xxxx)[엔터 시 기존 전화번호 유지]: ");
                    String phone = scanner.nextLine();

                    if(!phone.isEmpty() || !PatternUtil.checkPhonePattern(phone))
                        System.out.println("전화번호는 [010-xxxx-xxxx]와 같은 형태로 입력해야 합니다!");

                    if(!phone.isEmpty())
                        updateStudent.setPhone(phone);
                    else
                        break;
                }

                System.out.print("새로운 이메일[엔터 시 기존 이메일 유지]: ");
                String email = scanner.nextLine();
                if(!email.isEmpty())
                    updateStudent.setEmail(email);

                System.out.print("새로운 비밀번호[엔터 시 기존 비밀번호 유지]: ");
                String password = scanner.nextLine();
                if(!password.isEmpty())
                    updateStudent.setPassword(password);

                System.out.print("새로운 주소[엔터 시 기존 주소 유지]: ");
                String address = scanner.nextLine();
                if(!address.isEmpty())
                    updateStudent.setAddress(address);

                if(student.equals(updateStudent)) {
                    System.out.println("변경사항이 없습니다!");
                    return;
                }

                boolean success = studentService.updateStudent(updateStudent);
                if (success) {
                    System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
                } else {
                    System.out.println("사용자 정보 수정에 실패하였습니다.");
                }
            } catch (SQLException e) {
                System.out.println("사용자 정보 수정 중 오류가 발생했습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                System.out.println("존재하지 않는 날짜이거나 날짜 변환 도중 문제가 발생했습니다!");
            }
        }
    }

    /**
     * 📌 학생 삭제 (DELETE)
     * - 학생 ID 혹은 이메일을 입력받아 삭제
     */
    private void deleteStudent() {
        while (true) {
            System.out.println("1. 학생 ID로 삭제");
            System.out.println("2. 학생 Email로 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                Students student = null;
                switch (choice) {
                    case 1 -> {
                        System.out.print("삭제할 학생 ID를 입력하세요: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine();
                        student = studentService.getStudentById(studentId);
                        break;
                    }
                    case 2 -> {
                        System.out.print("삭제할 학생 이메일을 입력하세요: ");
                        String studentEmail = scanner.nextLine();
                        student = studentService.getStudentByEmail(studentEmail);
                        break;
                    }
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                        continue;
                    }
                }

                boolean success = studentService.deleteStudent(student.getStudentId());
                if (success) {
                    System.out.println("학생이 성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("학생 삭제에 실패하였습니다.");
                }
            } catch (SQLException e) {
                System.out.println("학생 삭제 중 오류가 발생했습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * String으로 입력받은 성별 정보를 Byte 타입으로 변경
     * @param gender : String으로 입력받은 성별 정보
     * @return : byte 값으로 바뀐 성별정보(남: 1, 여: 0)
     */
    private Byte convertGender(String gender) {
        if(gender.equals("남"))
            return 1;
        else
            return 0;
    }
}
