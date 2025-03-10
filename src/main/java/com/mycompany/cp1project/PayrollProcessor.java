/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Amir
 */
public class PayrollProcessor {
   
    // calculate overtime pay (1.25 x hourly rate)
    public static double calculateOvertimePay(double overtimeHours, double hourlyRate) {
        return overtimeHours * hourlyRate * 1.25;
    }
    
    // calculate late deductions (if late minutes > 10)
    
    public static double calculateLateDeduction(int lateMinutes, double hourlyRate){
        if (lateMinutes > 10) {
            int excessLateMinutes = lateMinutes - 10;
            return (hourlyRate / 60) * excessLateMinutes; // deduction is based on per-minute rate
        }
        return 0;
    }    
    // Calculate GROSS Weekly Salary (with Overtime)
    public static double calculateGrossWeeklySalary(double totalHoursWorked, double overtimeHours, double hourlyRate){
            return (totalHoursWorked * hourlyRate) + calculateOvertimePay(overtimeHours, hourlyRate);
    }        
      
    // Calculate NET Weekly Salary (no gov deductions, only late deduction)
    
    public static double calculateNetWeeklySalary(double totalHoursWorked, double overtimeHours, int lateMinutes, double hourlyRate) {
        double grossSalary = calculateGrossWeeklySalary(totalHoursWorked, overtimeHours, hourlyRate);
        return grossSalary - calculateLateDeduction(lateMinutes, hourlyRate);
    }

    // calculate monthly government deductions (SSS, Philhealth, Pag-ibig)
     public static double calculateMonthlyDeductions(double basicSalary) {
        double sss = basicSalary * 0.045; // 4.5% SSS no need sssTable since lowest monthly salary is 22.5k
        double philHealth = basicSalary * 0.015; // 3% but 1.5 Employee and 1.5 employer
        double pagIbig = 100; // Fixed PHP 100 Pag-IBIG contribution
        return sss + philHealth + pagIbig;
     }
    
    
    // compute Monthly Withholding Tax
   public static double calculateMonthlyWithholdingTax(double grossMonthlySalary, double monthlyDeductions) {
        double taxableIncome = grossMonthlySalary - monthlyDeductions;
        if (taxableIncome <= 20832) {
            return 0;
        } else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome <= 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome <= 666667) {
            return 40833 + (taxableIncome - 166667) * 0.32;
        } else {
            return 200833 + (taxableIncome - 666667) * 0.35;
        
   }  } 
         // Compute Net Monthly Salary (Includes Monthly Deductions & Tax)
        public static double calculateNetMonthlySalary(double grossMonthlySalary, double basicSalary) {
        double monthlyDeductions = calculateMonthlyDeductions(basicSalary);
        double withholdingTax = calculateMonthlyWithholdingTax(grossMonthlySalary, monthlyDeductions);
        return grossMonthlySalary - monthlyDeductions - withholdingTax;
        }   
        
       // Process Payroll based on attendance 
        public static void processPayroll(AttendanceProcessor attendanceProcessor, double hourlyRate) {
        List<AttendanceRecord> records = attendanceProcessor.getAttendanceRecords();
        Map<String, Double[]> employeeHours = new HashMap<>();
        // summarize hours worked per employee
        for (AttendanceRecord record : records){
            String empId = record.getEmployeeId();
            double hoursWorked = record.getHoursWorked();
            double overtimeHours = Math.max(0, hoursWorked - 40);
            int lateMinutes = record.getLateMinutes();
            
            // store weekly totals for each employee
            
            employeeHours.putIfAbsent(empId, new Double[]{0.0, 0.0, 0.0});
            Double[] values = employeeHours.get(empId);
            values[0] = hoursWorked; // Total hours
            values[1] = Math.max(0, hoursWorked - 40); // Overtime hours
            values[2] = (double) lateMinutes; // Late minutes
        }
        for (Map.Entry<String, Double[]> entry : employeeHours.entrySet()) {
        String empId = entry.getKey();
        double totalHoursWorked = entry.getValue()[0];
        double overtimeHours = Math.max(0, totalHoursWorked - 40);
        int lateMinutes = entry.getValue()[2].intValue();

        double grossWeeklySalary = calculateGrossWeeklySalary(totalHoursWorked, overtimeHours, hourlyRate);
        double netWeeklySalary = calculateNetWeeklySalary(totalHoursWorked, overtimeHours, lateMinutes, hourlyRate);

        System.out.println("\nPayroll for Employee: " + empId);
        System.out.println("Total Hours Worked: " + totalHoursWorked);
        System.out.println("Overtime Hours: " + overtimeHours);
        System.out.println("Late Minutes: " + lateMinutes);
        System.out.println("Gross Weekly Salary: PHP " + grossWeeklySalary);
        System.out.println("Net Weekly Salary: PHP " + netWeeklySalary);
        System.out.println("=======================================");
        }
     }  
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

