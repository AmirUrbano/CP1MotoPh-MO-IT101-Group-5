/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Amir
 */

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

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getDate() { return date; }
    public LocalTime getLogInTime() { return logIn; }
    public LocalTime getLogOutTime() { return logOut; }
    public boolean isLate() { return isLate; }
    public int getLateMinutes() { return lateMinutes; }
    public double getHoursWorked() { return hoursWorked; }
    public int getWeekNumber() { return weekNumber; }

    //  Calculate week number using LocalDate and WeekFields
  /*  private int calculateWeekNumber(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
        return parsedDate.get(WeekFields.of(java.time.DayOfWeek.MONDAY, 4).weekOfWeekBasedYear());
    }
    */
    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Date: " + date + ", Log In: " + logIn + ", Log Out: " + logOut +
               ", Late: " + (isLate ? lateMinutes + " mins" : "No") + ", Hours Worked: " + hoursWorked;
    }
}