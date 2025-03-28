package com.metabirth.model;

import com.metabirth.util.TimeUtil;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Objects;

public class Students {
    private int studentId;
    private String studentName;
    private String password;
    private Date birthDate;
    private byte gender;
    private String phone;
    private String address;
    private String email;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Students(int studentId, String studentName, String password, Date birthDate, byte gender, String phone, String address, String email, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Students(Students student) {
        this.studentId = student.studentId;
        this.studentName = student.studentName;
        this.password = student.password;
        this.birthDate = student.birthDate;
        this.gender = student.gender;
        this.phone = student.phone;
        this.address = student.address;
        this.email = student.email;
        this.status = student.status;
        this.createdAt = student.createdAt;
        this.updatedAt = student.updatedAt;
        this.deletedAt = student.deletedAt;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Students{" +
                "학생Id: " + studentId +
                ", 이름: '" + studentName + '\'' +
                ", 비밀번호: '" + password + '\'' +
                ", 생년월일: " + birthDate +
                ", 성별: " + convertGender(gender) +
                ", 핸드폰 번호: '" + phone + '\'' +
                ", 주소: '" + address + '\'' +
                ", 이메일: '" + email + '\'' +
                ", 삭제여부: " + status +
                ", 생성일: " + TimeUtil.formatDateTimeToString(createdAt) +
                ", 수정일: " + (updatedAt != null ? TimeUtil.formatDateTimeToString(updatedAt) : null) +
                ", 삭제일: " + (deletedAt != null ? TimeUtil.formatDateTimeToString(deletedAt) : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return studentId == students.studentId && gender == students.gender && status == students.status && Objects.equals(studentName, students.studentName) && Objects.equals(password, students.password) && Objects.equals(birthDate, students.birthDate) && Objects.equals(phone, students.phone) && Objects.equals(address, students.address) && Objects.equals(email, students.email) && Objects.equals(createdAt, students.createdAt) && Objects.equals(updatedAt, students.updatedAt) && Objects.equals(deletedAt, students.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, password, birthDate, gender, phone, address, email, status, createdAt, updatedAt, deletedAt);
    }

    /**
     * Byte 타입의 변수를 받아와서 남,여 여부를 판단하는 메서드
     * @param gender : Byte 타입의 변수(0 혹은 1)
     * @return : String 타입의 "남" 혹은 "여" 반환
     */
    private String convertGender(Byte gender) {
        if(gender == 1)
            return "남";
        else
            return "여";
    }
}
