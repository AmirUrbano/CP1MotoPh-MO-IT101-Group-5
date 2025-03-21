/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDetails employeeDetails = new EmployeeDetails();
        AttendanceProcessor attendanceProcessor = new AttendanceProcessor();

        // Load attendance data ONCE at the beginning
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

            Employee employee = employeeDetails.findEmployeeById(empId);

            if (employee != null) {
                System.out.println("\n Employee Found:");
                System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
                System.out.println("Birthday: " + employee.getBirthday());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Status: " + employee.getStatus());
                System.out.printf("Hourly Rate: PHP %.2f\n", employee.getHourlyRate());

                // Call the new displayWeeklyPayroll()` method to handle all salary calculations and display
                double govDeductions = (employee.getSssContribution() + employee.getPhilHealthContribution() + employee.getPagIbigContribution()) / 4;
                attendanceProcessor.displayWeeklyPayroll(empId, employee.getHourlyRate(), govDeductions);

                //  Display fixed monthly salary and basic salary
                double monthlyGross = employee.getBasicSalary() + employee.getRiceSubsidy() +
                        employee.getPhoneAllowance() + employee.getClothingAllowance();
               

                System.out.printf("\nFixed Monthly Gross Salary: PHP %.2f\n", monthlyGross);
             
                System.out.printf("Basic Salary: PHP %.2f\n", employee.getBasicSalary());
            } else {
                System.out.println("\n Employee not found. Please try again.");
            }

            System.out.println("\n========================================");
        }

        System.out.println("\n Payroll Processing Complete!");
        scanner.close();
    }
}
