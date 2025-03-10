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

        System.out.println(" Welcome to MotoPH Payroll System!");

        // Load attendance data from CSV
        AttendanceProcessor attendanceProcessor = new AttendanceProcessor();
        attendanceProcessor.loadAttendance(); // Reads CSV and processes attendance

        // Ask user for hourly rate (or hardcode it)
        System.out.print("\nEnter hourly rate for employees: PHP ");
        double hourlyRate = scanner.nextDouble();

        // Process payroll and display results
        PayrollProcessor.processPayroll(attendanceProcessor, hourlyRate);

        System.out.println("\n✅ Payroll Processing Complete!");
        scanner.close();
    } 
}
