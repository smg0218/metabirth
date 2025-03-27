package com.metabirth.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Attendances {
    private int attendanceId;
    private int studentId;
    private boolean attendanceStatus;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    private Date attendanceDate;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Attendances(int attendanceId, int studentId, boolean attendanceStatus,
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

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
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
                ", 출석상태: " + attendanceStatus +
                ", 체크인시간: " + checkinTime +
                ", 체크아웃시간: " + checkoutTime +
                ", 출석날짜: " + attendanceDate +
                ", 삭제여부: " + status +
                ", 생성일: " + createdAt +
                ", 수정일: " + updatedAt +
                ", 삭제일: " + deletedAt +
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
}
