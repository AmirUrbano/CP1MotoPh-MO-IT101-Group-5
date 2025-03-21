/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;

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
     public static double calculateMonthlyDeductions(double basicSalary) {
        double sss = basicSalary * 0.045;
        double philHealth = basicSalary * 0.015;
        double pagIbig = 100;
        return sss + philHealth + pagIbig;
     }
        // Calculate monthly government deductions (divide by 4 for weekly)
     
       public static double calculateWeeklyDeductions(double basicSalary) {
        return calculateMonthlyDeductions(basicSalary) / 4;
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
        
        }   
   }
        // Compute weekly Withholding tax (divide by 4)
        public static double calculateWeeklyWithholdingTax(double grossWeeklySalary, double weeklyDeductions) {
        double taxableIncome = grossWeeklySalary - weeklyDeductions;
        if (taxableIncome <= 20832 / 4) {
        return 0;
         } else if (taxableIncome <= 33333 / 4) {
        return (taxableIncome - (20833 / 4)) * 0.20;
        } else if (taxableIncome <= 66667 / 4) {
        return (2500 / 4) + (taxableIncome - (33333 / 4)) * 0.25;
        } else if (taxableIncome <= 166667 / 4) {
        return (10833 / 4) + (taxableIncome - (66667 / 4)) * 0.30;
        } else if (taxableIncome <= 666667 / 4) {
        return (40833 / 4) + (taxableIncome - (166667 / 4)) * 0.32;
        } else {
        return (200833 / 4) + (taxableIncome - (666667 / 4)) * 0.35;
        }
   }
        public static double calculateNetMonthlySalary(double grossMonthlySalary, double totalDeductions) {
            double netSalary = grossMonthlySalary - totalDeductions;
            return Math.max(netSalary, 0);
        
}

      // Process weekly payroll summary
    public static void processWeeklyPayroll(AttendanceProcessor attendanceProcessor, Employee employee) {
         System.out.println("\n🟢 Processing payroll for employee ID: " + employee.getEmployeeId() +
                       " | Name: " + employee.getFirstName() + " " + employee.getLastName());
         
    Map<Integer, Double[]> weeklyHours = attendanceProcessor.getWeeklyHours(employee.getEmployeeId());

    for (Map.Entry<Integer, Double[]> entry : weeklyHours.entrySet()) {
        int weekNumber = entry.getKey();
        double totalHoursWorked = entry.getValue()[0];
        double overtimeHours = entry.getValue()[1];
        int lateMinutes = entry.getValue()[2].intValue();
        System.out.println("️ Processing Week " + weekNumber + " | Total Hours: " + totalHoursWorked + " | Overtime: " + overtimeHours + " | Late Minutes: " + lateMinutes);

        
        double grossWeeklySalary = calculateGrossWeeklySalary(totalHoursWorked, overtimeHours, employee.getHourlyRate());
        double weeklyDeductions = calculateWeeklyDeductions(employee.getBasicSalary());
        double withholdingTax = calculateWeeklyWithholdingTax(grossWeeklySalary, weeklyDeductions);
        double netWeeklySalary = calculateNetWeeklySalary(totalHoursWorked, overtimeHours, lateMinutes, employee.getHourlyRate()) - weeklyDeductions - withholdingTax;
System.out.println(" Fetching weekly hours for employee ID: " + employee.getEmployeeId());
if (weeklyHours.isEmpty()) {
    System.out.println(" No attendance data found for employee ID: " + employee.getEmployeeId());
} else {
    System.out.println(" Data found. Processing payroll...");
}

        System.out.println("\n -->️ Week " + weekNumber);
        System.out.println("Total Hours Worked: " + String.format("%.2f", totalHoursWorked));
        System.out.println("Overtime Hours: " + String.format("%.2f", overtimeHours));
        System.out.println("Late Minutes: " + lateMinutes);
        System.out.println("Weekly Gross Salary: PHP " + String.format("%.2f", grossWeeklySalary));
        System.out.println("Net Weekly Salary (after deductions): PHP " + String.format("%.2f", netWeeklySalary));
    }
}
     } 
