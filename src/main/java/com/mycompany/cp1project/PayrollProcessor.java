/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class PayrollProcessor {

    private AttendanceProcessor attendanceProcessor = AttendanceProcessor.getInstance();
    private EmployeeDetails employeeDetails = new EmployeeDetails();

    public void processMonthlyPayroll(String employeeId, int month) {
        attendanceProcessor.loadAttendance("Attendance.csv");

        Employee employee = employeeDetails.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        employee.calculateContributions();

        System.out.println("\nMonth of " + getMonthName(month) + ":");
        System.out.println("========================================");

        LocalDate startOfMonth = LocalDate.of(2024, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        double totalMonthlyGrossSalary = 0;
        double totalMonthlyNetSalary = 0;
        double totalSSS = 0;
        double totalPhilHealth = 0;
        double totalPagIbig = 0;
        double totalWithholdingTax = 0;
        double totalLateDeductions = 0;
        int totalLateDays = 0;

        int weekCount = 1;
        LocalDate currentWeekStart = startOfMonth.with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());

        while (!currentWeekStart.isAfter(endOfMonth)) {
            LocalDate currentWeekEnd = currentWeekStart.plusDays(6);
            if (currentWeekEnd.isAfter(endOfMonth)) currentWeekEnd = endOfMonth;

            double totalHoursWorked = attendanceProcessor.getHoursWorked(employeeId, currentWeekStart, currentWeekEnd);

            // ✅ Skip week if there was no work at all
            if (totalHoursWorked == 0) {
                currentWeekStart = currentWeekStart.plusDays(7);
                continue;
            }

            int totalMinutesLate = attendanceProcessor.getMinutesLate(employeeId, currentWeekStart, currentWeekEnd);
            int daysLate = attendanceProcessor.getDaysLate(employeeId, currentWeekStart, currentWeekEnd);

            double overtimeHours = Math.max(totalHoursWorked - 40, 0);
            double regularHours = Math.min(totalHoursWorked, 40);

            double grossSalary = (regularHours * employee.getHourlyRate()) + (overtimeHours * employee.getHourlyRate() * 1.25);
            double lateDeductions = totalMinutesLate * (employee.getHourlyRate() / 60);

            double sss = getSSSContribution(grossSalary);
            double philHealth = getPhilHealthContribution(grossSalary);
            double pagIbig = getPagIbigContribution(grossSalary);
            double withholdingTax = getWithholdingTax(grossSalary);

            double totalDeductions = lateDeductions + sss + philHealth + pagIbig + withholdingTax;
            double netSalary = grossSalary - totalDeductions;

            System.out.println("\n----------------------------------------");
            System.out.printf("WEEK %d: %s - %s\n", weekCount, formatDate(currentWeekStart), formatDate(currentWeekEnd));
            System.out.println("----------------------------------------");
            System.out.printf("-> Regular Hours Worked: %.2f\n", regularHours);
            System.out.printf("-> Overtime Hours: %.2f\n", overtimeHours);
            System.out.printf("-> Total Hours Worked: %.2f\n", totalHoursWorked);
            System.out.printf("-> Days Late: %d\n", daysLate);
            System.out.printf("-> Minutes Late: %d\n", totalMinutesLate);
            System.out.printf("-> Gross Salary: PHP %.2f\n", grossSalary);
            System.out.printf("-> Late Deductions: PHP %.2f\n", lateDeductions);
            System.out.printf("-> SSS Deduction: PHP %.2f\n", sss);
            System.out.printf("-> PhilHealth Deduction: PHP %.2f\n", philHealth);
            System.out.printf("-> Pag-IBIG Deduction: PHP %.2f\n", pagIbig);
            System.out.printf("-> Withholding Tax: PHP %.2f\n", withholdingTax);
            System.out.printf("-> Net Salary: PHP %.2f\n", netSalary);

            totalMonthlyGrossSalary += grossSalary;
            totalMonthlyNetSalary += netSalary;
            totalSSS += sss;
            totalPhilHealth += philHealth;
            totalPagIbig += pagIbig;
            totalWithholdingTax += withholdingTax;
            totalLateDeductions += lateDeductions;
            totalLateDays += daysLate;

            currentWeekStart = currentWeekStart.plusDays(7);
            weekCount++;
        }

        System.out.println("\n========================================");
        System.out.println("TOTAL MONTHLY SUMMARY:");
        System.out.printf("-> Total Gross Salary: PHP %.2f\n", totalMonthlyGrossSalary);
        System.out.printf("-> Total Late Deductions: PHP %.2f\n", totalLateDeductions);
        System.out.printf("-> Total SSS Deduction: PHP %.2f\n", totalSSS);
        System.out.printf("-> Total PhilHealth Deduction: PHP %.2f\n", totalPhilHealth);
        System.out.printf("-> Total Pag-IBIG Deduction: PHP %.2f\n", totalPagIbig);
        System.out.printf("-> Total Withholding Tax: PHP %.2f\n", totalWithholdingTax);
        System.out.printf("-> Total Days Late: %d\n", totalLateDays);
        System.out.printf("-> Total Net Salary: PHP %.2f\n", totalMonthlyNetSalary);
        System.out.println("========================================");
    }

    private String getMonthName(int month) {
        return LocalDate.of(2024, month, 1).getMonth().getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH);
    }

    private String formatDate(LocalDate date) {
        return date.format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    private double getSSSContribution(double salary) {
        return Math.min(1125.00, Math.max(135.00, Math.floor(salary / 500) * 22.5));
    }

    private double getPhilHealthContribution(double salary) {
        return salary * 0.0275;
    }

    private double getPagIbigContribution(double salary) {
        return salary * 0.01;
    }

    private double getWithholdingTax(double salary) {
        if (salary <= 20833) {
            return 0;
        } else if (salary <= 33332) {
            return (salary - 20833) * 0.2;
        } else if (salary <= 66666) {
            return 2500 + (salary - 33333) * 0.25;
        } else if (salary <= 166666) {
            return 10833 + (salary - 66667) * 0.3;
        } else if (salary <= 666666) {
            return 40833.33 + (salary - 166667) * 0.32;
        } else {
            return 200833.33 + (salary - 666667) * 0.35;
        }
    }
}
