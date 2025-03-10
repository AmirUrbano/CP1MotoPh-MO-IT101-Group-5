/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 *
 * @author Amir
 */
public class AttendanceProcessor {
    private static final String ATTENDANCE_FILE = "src/main/java/com/mycompany/cp1project/Attendance.csv";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");
    private static final LocalTime SHIFT_START = LocalTime.of(8,0); // 8am 
    private static final LocalTime GRACE_PERIOD_END = LocalTime.of(8,10); // 10 minute grace period
    
    
    private List<AttendanceRecord> attendanceRecords;
    
    public AttendanceProcessor(){
        attendanceRecords = new ArrayList<>();
    }
    
    public void loadAttendance() {
    attendanceRecords.clear(); // Clear previous records
    try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_FILE))) {
        String header = br.readLine(); // ✅ Skip header row
        System.out.println(header);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line); // ✅ Debug print to check what's being read

            String[] data = line.split(",");
            if (data.length < 6) { 
                System.out.println("❌ Skipping invalid row (not enough columns): " + line);
                continue;
            }

            String empId = data[0].trim();
            String date = data[3].trim();
            String logInRaw = data[4].trim();
            String logOutRaw = data[5].trim();

            try {
                LocalTime logInTime = LocalTime.parse(logInRaw, DateTimeFormatter.ofPattern("H:mm"));
                LocalTime logOutTime = LocalTime.parse(logOutRaw, DateTimeFormatter.ofPattern("H:mm"));

                boolean isLate = logInTime.isAfter(LocalTime.of(8, 10)); // After 8:10 AM
                int lateMinutes = isLate ? (int) java.time.Duration.between(LocalTime.of(8, 10), logInTime).toMinutes() : 0;

                AttendanceRecord record = new AttendanceRecord(empId, date, logInTime, logOutTime, isLate, lateMinutes);
                attendanceRecords.add(record);
            } catch (Exception e) {
                System.out.println(" Error parsing time for line: " + line + " | " + e.getMessage());
            }
        }

        System.out.println(" Attendance loaded successfully. Total records: " + attendanceRecords.size());
    } catch (IOException e) {
        System.err.println(" Error reading CSV file: " + e.getMessage());
    }
     
}
    public List<AttendanceRecord> getAttendanceRecords() {
    return attendanceRecords;
    }
 }
         
         
         
         
         

