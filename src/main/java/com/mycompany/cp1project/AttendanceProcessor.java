/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package com.mycompany.cp1project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;



    public class AttendanceProcessor {
    private final List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    private static final String ATTENDANCE_FILE = "C:\\Users\\Amir\\Documents\\NetBeansProjects\\CompProg1 H1101\\src\\main\\java\\com\\mycompany\\cp1project\\Attendance.csv";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final LocalTime GRACE_PERIOD_END = LocalTime.of(8, 10);

    private LocalDate baseDate; //  Store the earliest recorded date

    public void loadAttendance() {
    attendanceRecords.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_FILE))) {
        String header = br.readLine(); // Skip header
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length < 6) {
                System.out.println("Skipping invalid row: " + line);
                continue;
            }

            String empNum = data[0].trim();
            String dateAttendance = data[3].trim();               
            LocalTime logInTime = LocalTime.parse(data[4].trim(), TIME_FORMATTER);
            LocalTime logOutTime = LocalTime.parse(data[5].trim(), TIME_FORMATTER);

            // ✅ Define attendanceDate BEFORE using it
            LocalDate attendanceDate = LocalDate.parse(dateAttendance, DATE_FORMATTER);

            // ✅ Set base date as the earliest recorded date
            if (baseDate == null || attendanceDate.isBefore(baseDate)) {
                baseDate = attendanceDate;
            }

            boolean isLate = logInTime.isAfter(GRACE_PERIOD_END);
            int lateMinutes = isLate ? (int) java.time.Duration.between(GRACE_PERIOD_END, logInTime).toMinutes() : 0;
            double hrsWorked = java.time.Duration.between(logInTime, logOutTime).toMinutes() / 60.0;

            //  Set the correct week number relative to baseDate
            int weekNumber = (int) ChronoUnit.WEEKS.between(baseDate, attendanceDate) + 1;

            //  Pass weekNumber to the constructor
                AttendanceRecord record = new AttendanceRecord(
                empNum, dateAttendance, logInTime, logOutTime,
                isLate, lateMinutes, hrsWorked, weekNumber // Add weekNumber
            );

            attendanceRecords.add(record);
        }
    } catch (IOException e) {
        System.out.println("Error loading attendance data: " + e.getMessage());
    }
    
            
    }

    //  Method to get weekly hours per employee
    public Map<Integer, Double[]> getWeeklyHours(String employeeId) {
        Map<Integer, Double[]> weeklyHours = new HashMap<>();

        for (AttendanceRecord record : attendanceRecords) {
            if (record.getEmployeeId().equals(employeeId)) {
                int weekNumber = record.getWeekNumber();
                double hoursWorked = record.getHoursWorked();
                int lateMinutes = record.getLateMinutes();

                weeklyHours.putIfAbsent(weekNumber, new Double[]{0.0, 0.0, 0.0});
                Double[] values = weeklyHours.get(weekNumber);

                double regularHours = values[0];
                double overtimeHours = values[1];
                double totalLateMinutes = values[2];

                if (regularHours < 40) {
                    double remainingRegularHours = 40 - regularHours;
                    if (hoursWorked <= remainingRegularHours) {
                        regularHours += hoursWorked;
                    } else {
                        regularHours += remainingRegularHours;
                        overtimeHours += hoursWorked - remainingRegularHours;
                    }
                } else {
                    overtimeHours += hoursWorked;
                }

                totalLateMinutes += lateMinutes;

                values[0] = regularHours;
                values[1] = overtimeHours;
                values[2] = totalLateMinutes;
            }
        }
        return weeklyHours;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    // ✅ Method to display weekly payroll
    public void displayWeeklyPayroll(String employeeId, double hourlyRate, double govDeductions) {
        Map<Integer, Double[]> weeklyHours = getWeeklyHours(employeeId);
        if (weeklyHours.isEmpty()) {
            System.out.println("\nNo attendance records found for this employee.");
            return;
        }

        double totalMonthlyGross = 0;
        double totalMonthlyNet = 0;

        System.out.println("\n========================================");

        for (int week : weeklyHours.keySet()) {
            Double[] values = weeklyHours.get(week);
            double regularHours = values[0];
            double overtimeHours = values[1];
            double lateMinutes = values[2];

            double grossWeeklySalary = (regularHours * hourlyRate) + (overtimeHours * hourlyRate * 1.25);
            double lateDeduction = (lateMinutes / 60) * hourlyRate;
            double netWeeklySalary = grossWeeklySalary - lateDeduction - govDeductions;

            totalMonthlyGross += grossWeeklySalary;
            totalMonthlyNet += netWeeklySalary;

            // ✅ Display week breakdown
            System.out.printf("Week %d\n", week);
            System.out.printf("- Total Hours Worked: %.2f\n", regularHours);
            System.out.printf("- Overtime Hours: %.2f\n", overtimeHours);
            System.out.printf("- Late Minutes: %.0f\n", lateMinutes);
            System.out.printf("- Weekly Gross Salary: PHP %.2f\n", grossWeeklySalary);
            System.out.printf("- Weekly Net Salary: PHP %.2f (with government deductions)\n\n", netWeeklySalary);
        }

        // ✅ Display total for the month
        System.out.printf("Total Monthly Gross Salary: PHP %.2f\n", totalMonthlyGross);
        System.out.printf("Total Monthly Net Salary: PHP %.2f\n", totalMonthlyNet);

        System.out.println("========================================");
    }
}
