/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class AttendanceRecord {
    private String employeeId;
    private String date;
    private LocalTime logIn;
    private LocalTime logOut;
    private boolean isLate;
    private int lateMinutes;
    private double hoursWorked;
    private int weekNumber;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // Full constructor (existing)
    public AttendanceRecord(String employeeId, String date, LocalTime logIn, LocalTime logOut,
                             boolean isLate, int lateMinutes, double hoursWorked, int weekNumber) {
        this.employeeId = employeeId;
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
        this.isLate = isLate;
        this.lateMinutes = lateMinutes;
        this.hoursWorked = hoursWorked;
        this.weekNumber = weekNumber;
    }

    // ➕ Added constructor for CSV loading
    public AttendanceRecord(String employeeId, String date, String logIn, String logOut) {
        this.employeeId = employeeId;
        this.date = date;
        this.logIn = LocalTime.parse(logIn);
        this.logOut = LocalTime.parse(logOut);
        calculateLateAndHours();
    }

    // ➕ New method to calculate lateness and hours worked
    private void calculateLateAndHours() {
        LocalTime expectedLogin = LocalTime.of(9, 0); // 9:00 AM
        this.isLate = logIn.isAfter(expectedLogin);
        this.lateMinutes = isLate ? (int) Duration.between(expectedLogin, logIn).toMinutes() : 0;
        this.hoursWorked = Duration.between(logIn, logOut).toMinutes() / 60.0;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getDate() { return date; }
    public LocalTime getLogInTime() { return logIn; }
    public LocalTime getLogOutTime() { return logOut; }
    public boolean isLate() { return isLate; }
    public int getLateMinutes() { return lateMinutes; }
    public double getHoursWorked() { return hoursWorked; }
    public int getWeekNumber() { return weekNumber; }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Date: " + date + ", Log In: " + logIn + ", Log Out: " + logOut +
               ", Late: " + (isLate ? lateMinutes + " mins" : "No") + ", Hours Worked: " + hoursWorked;
    }
}