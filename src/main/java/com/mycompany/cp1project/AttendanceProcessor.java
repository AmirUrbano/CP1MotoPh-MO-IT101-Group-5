package com.mycompany.cp1project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AttendanceProcessor {

    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    // ✅ For parsing CSV values
    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    // ✅ For parsing internally stored ISO date strings (like "2024-06-03")
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // ➕ Singleton instance
    private static AttendanceProcessor instance;

    public static AttendanceProcessor getInstance() {
        if (instance == null) {
            instance = new AttendanceProcessor();
        }
        return instance;
    }

    // ✅ Load attendance records from CSV file
    public void loadAttendance(String filePath) {
    attendanceRecords.clear();
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            

            String[] data = line.split(",");

            // ✅ Adjusted for 6 fields: ID, Last Name, First Name, Date, Time In, Time Out
            if (data.length >= 6) {
                String employeeId = data[0];
                LocalDate date = LocalDate.parse(data[3], CSV_DATE_FORMAT);       // Index 3 = date (MM/dd/yyyy or similar)
                LocalTime timeIn = parseTime(data[4]);     // Index 4 = time in
                LocalTime timeOut = parseTime(data[5]);    // Index 5 = time out

                if (date != null && timeIn != null && timeOut != null) {
                    AttendanceRecord record = new AttendanceRecord(
                        employeeId,
                        date.toString(),
                        timeIn.toString(),
                        timeOut.toString()
                    );
                    attendanceRecords.add(record);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateStr);
            return null;
        }
    }

    private LocalTime parseTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format: " + timeStr);
            return null;
        }
    }

    public double getHoursWorked(String employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRecords.stream()
                .filter(record -> record.getEmployeeId().equals(employeeId))
                .filter(record -> {
                    LocalDate date = LocalDate.parse(record.getDate(), DATE_FORMATTER);
                    return !date.isBefore(startDate) && !date.isAfter(endDate);
                })
                .mapToDouble(AttendanceRecord::getHoursWorked)
                .sum();
    }

    public int getMinutesLate(String employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRecords.stream()
                .filter(record -> record.getEmployeeId().equals(employeeId))
                .filter(record -> {
                    LocalDate date = LocalDate.parse(record.getDate(), DATE_FORMATTER);
                    return !date.isBefore(startDate) && !date.isAfter(endDate);
                })
                .mapToInt(AttendanceRecord::getLateMinutes)
                .sum();
    }

    public int getDaysLate(String employeeId, LocalDate startDate, LocalDate endDate) {
        return (int) attendanceRecords.stream()
                .filter(record -> record.getEmployeeId().equals(employeeId))
                .filter(record -> {
                    LocalDate date = LocalDate.parse(record.getDate(), DATE_FORMATTER);
                    return !date.isBefore(startDate) && !date.isAfter(endDate);
                })
                .filter(AttendanceRecord::isLate)
                .count();
    }

    public int getWeekNumber(LocalDate date) {
        return date.get(WeekFields.of(Locale.getDefault()).weekOfMonth());
    }

    public LocalDate getStartOfWeek(LocalDate date) {
        return date.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
    }

    public LocalDate getEndOfWeek(LocalDate date) {
        return date.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 7);
    }
}

