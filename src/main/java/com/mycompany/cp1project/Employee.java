/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;

/**
 *
 * @author Amir
 */
public class Employee {
    int employeeNumber;
    String lastName, firstName, birthday, address, phoneNumber;
    String sssNumber, philHealth, tinNumber, pagIbig, status, position, supervisor;
    double basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
    double grossSemiMonthlyRate, hourlyRate;
    
    // constructor to initialize employee data
    public Employee(int employeeNumber, String lastName, String firstName, String birthday,
                    String address, String phoneNumber, String sssNumber, String philHealth,
                    String tinNumber, String pagIbig, String status, String position,
                    String supervisor, double basicSalary, double riceSubsidy,
                    double phoneAllowance, double clothingAllowance,
                    double grossSemiMonthlyRate, double hourlyRate) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philHealth = philHealth;
        this.tinNumber = tinNumber;
        this.pagIbig = pagIbig;
        this.status = status;
        this.position = position;
        this.supervisor = supervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }
        // display employee information (without payroll calculations
        
    public void displayInfo() {
        System.out.println("\n===== Employee Details =====");  
        System.out.println("Employee #: " + employeeNumber);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Birthday: " + birthday);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("SSS #: " + sssNumber);
        System.out.println("PhilHealth #: " + philHealth);
        System.out.println("TIN #: " + tinNumber);
        System.out.println("Pag-IBIG #: " + pagIbig);
        System.out.println("Status: " + status);
        System.out.println("Position: " + position);
        System.out.println("Immediate Supervisor: " + supervisor);
        System.out.println("Basic Salary: PHP " + basicSalary);
        System.out.println("Rice Subsidy: PHP " + riceSubsidy);
        System.out.println("Phone Allowance: PHP " + phoneAllowance);
        System.out.println("Clothing Allowance: PHP " + clothingAllowance);
        System.out.println("Gross Semi-Monthly Rate: PHP " + grossSemiMonthlyRate);
        System.out.println("Hourly Rate: PHP " + hourlyRate);
        System.out.println("=======================================");
    }
    
    
    }
    
    
    

