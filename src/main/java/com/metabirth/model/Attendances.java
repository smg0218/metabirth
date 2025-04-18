package com.metabirth.model;

import com.metabirth.util.TimeUtil;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Objects;

public class Attendances {
    private int attendanceId;
    private int studentId;
    private byte attendanceStatus; // 0: 관리자가 직접 데이터를 넣음, 1: 체크인 완료, 2: 체크아웃 완료, 3: 체크인, 체크아웃 완료
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    private Date attendanceDate;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Attendances(int attendanceId, int studentId, byte attendanceStatus,
                       LocalDateTime checkinTime, LocalDateTime checkoutTime, Date attendanceDate,
                       boolean status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.attendanceStatus = attendanceStatus;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.attendanceDate = attendanceDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Attendances(Attendances attendance) {
        this.attendanceId = attendance.getAttendanceId();
        this.studentId = attendance.getStudentId();
        this.attendanceStatus = attendance.getAttendanceStatus();
        this.checkinTime = attendance.getCheckinTime();
        this.checkoutTime = attendance.getCheckoutTime();
        this.attendanceDate = attendance.getAttendanceDate();
        this.status = attendance.status;
        this.createdAt = attendance.getCreatedAt();
        this.updatedAt = attendance.getUpdatedAt();
        this.deletedAt = attendance.getDeletedAt();
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public byte getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(byte attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
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
        return "Attendances{" +
                "출석Id: " + attendanceId +
                ", 학생Id: " + studentId +
                ", 출석상태: " + convertAttendanceStatus(attendanceStatus) +
                ", 체크인시간: " + (checkinTime != null ? TimeUtil.formatDateTimeToString(checkinTime) : null) +
                ", 체크아웃시간: " + (checkoutTime != null ? TimeUtil.formatDateTimeToString(checkoutTime) : null) +
                ", 출석날짜: " + attendanceDate +
                ", 삭제여부: " + status +
                ", 생성일: " + TimeUtil.formatDateTimeToString(createdAt) +
                ", 수정일: " + (updatedAt != null ? TimeUtil.formatDateTimeToString(updatedAt) : null) +
                ", 삭제일: " + (deletedAt != null ? TimeUtil.formatDateTimeToString(deletedAt) : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Attendances that = (Attendances) o;
        return attendanceId == that.attendanceId && studentId == that.studentId && attendanceStatus == that.attendanceStatus && status == that.status && Objects.equals(checkinTime, that.checkinTime) && Objects.equals(checkoutTime, that.checkoutTime) && Objects.equals(attendanceDate, that.attendanceDate) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceId, studentId, attendanceStatus, checkinTime, checkoutTime, attendanceDate, status, createdAt, updatedAt, deletedAt);
    }

    /**
     * Byte 타입의 변수를 받아와서 출석 상태 변환하는 메서드
     * @param attendanceStatus : Byte 타입의 변수, 0,1,2,3 중에 하나여야 한다.
     * @return : Byte 타입에 따라 변환된 출석 상태정보 (String)
     */
    private String convertAttendanceStatus(Byte attendanceStatus) {
        switch (attendanceStatus) {
            case 0 -> {
                return "관리자가 직접 데이터를 넣음";
            }
            case 1 -> {
                return "출석";
            }
            case 2 -> {
                return "지각";
            }
            case 3 -> {
                return "결석";
            }
            default -> {
                return "잘못된 값이 입력된 상태, 확인 필요!!!";
            }
        }
    }
}
