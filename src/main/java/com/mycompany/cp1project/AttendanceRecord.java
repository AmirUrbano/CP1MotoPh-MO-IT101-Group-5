/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;


import java.time.LocalTime;


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

    public AttendanceRecord(String employeeId, String date, LocalTime logIn, LocalTime logOut, boolean isLate, int lateMinutes) {
        this.employeeId = employeeId;
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
        this.isLate = isLate;
        this.lateMinutes = lateMinutes;
    }

    public String getEmployeeId() { return employeeId; }
    public String getDate() { return date; }
    public LocalTime getLogInTime() { return logIn; }
    public LocalTime getLogOutTime() { return logOut; }
    public boolean isLate() { return isLate; }
    public int getLateMinutes() { return lateMinutes; }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Date: " + date + ", Log In: " + logIn + ", Log Out: " + logOut +
               ", Late: " + (isLate ? lateMinutes + " mins" : "No");
    } 
        // get hours worked
        
        public double getHoursWorked() {
    return (double) java.time.Duration.between(logIn, logOut).toMinutes() / 60; // Convert to hours
}
    }






