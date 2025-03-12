/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;
import java.util.Scanner;
/**
 *
 * @author Amir
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDetails employeeDetails = new EmployeeDetails();
        AttendanceProcessor attendanceProcessor = new AttendanceProcessor();
        PayrollProcessor payrollProcessor = new PayrollProcessor();
        
        String employeeId;
        Employee employee;
        
   
        
        // Load attendance records from the CSV file
        attendanceProcessor.loadAttendance();

        System.out.println("\n========================================");
        System.out.println("Welcome to MotoPH Payroll System!");
        System.out.println("========================================");

        while (true) {
            System.out.print("Enter Employee ID (or type 'exit' to quit): ");
            String empId = scanner.nextLine().trim();

            if (empId.equalsIgnoreCase("exit")) {
                break;
            }

            // Find the employee details
            employee = employeeDetails.findEmployeeById(empId);

            if (employee != null) {
                System.out.println("\n✅ Employee Found:");
                System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Status: " + employee.getStatus());
                System.out.println("Hourly Rate: PHP " + employee.getHourlyRate());
                
                double totalHoursWorked = 0;
                double overtimeHours = 0;
                double grossMonthlySalary = 0;
                int lateMinutes = 0; 
                
                for (AttendanceRecord record : attendanceProcessor.getAttendanceRecords()){
                    if (record.getEmployeeId().equals(employee.getEmployeeId())){
                        totalHoursWorked += record.getHoursWorked();
                        overtimeHours += Math.max(0, record.getHoursWorked() - 40);
                        lateMinutes += record.getHoursWorked();
                    }
                }
                 //  Weekly Salary Calculation
                double weeklyGross = payrollProcessor.calculateGrossWeeklySalary(totalHoursWorked, overtimeHours, employee.getHourlyRate());
                double weeklyNet = payrollProcessor.calculateNetWeeklySalary(totalHoursWorked, overtimeHours, lateMinutes, employee.getHourlyRate());
                System.out.println("Weekly Gross Salary: PHP " + weeklyGross);
                System.out.println("Weekly Net Salary: PHP " + weeklyNet);

                //  Display Monthly Salary
                double monthlyGross = employee.getBasicSalary() + employee.getRiceSubsidy() +
                            employee.getPhoneAllowance() + employee.getClothingAllowance();
                double monthlyNet = PayrollProcessor.calculateNetMonthlySalary(grossMonthlySalary, employee.getBasicSalary());
                System.out.println("Monthly Gross Salary: PHP " + grossMonthlySalary);
                System.out.println("Monthly Net Salary: PHP " + monthlyNet);

            } else {
                System.out.println("\n❌ Employee not found. Please try again.");
            }

            System.out.println("\n========================================");
        }

        System.out.println("\n✅ Payroll Processing Complete!");
        scanner.close(); // Close the scanner to avoid memory leaks
    }
}