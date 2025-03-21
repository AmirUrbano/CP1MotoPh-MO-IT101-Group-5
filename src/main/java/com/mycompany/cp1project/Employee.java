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
    String employeeId;
    String lastName, firstName, birthday, address, phoneNumber;
    String sssNumber, philHealth, tinNumber, pagIbig, status, position, supervisor;
    double basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
    double grossSemiMonthlyRate, hourlyRate;
    private double sssContribution;
    private double philHealthContribution;
    private double pagIbigContribution;
    // constructor to initialize employee data
    
    public double getHourlyRate() {
    return hourlyRate;
}
    
    
    
    public Employee(String employeeId, String lastName, String firstName, String birthday,
                    String address, String phoneNumber, String sssNumber, String philHealth,
                    String tinNumber, String pagIbig, String status, String position,
                    String supervisor, double basicSalary, double riceSubsidy,
                    double phoneAllowance, double clothingAllowance,
                    double grossSemiMonthlyRate, double hourlyRate) {
        this.employeeId = employeeId;
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
    
        // getter for employee id
    public String getEmployeeId(){
        return employeeId; 
        
    }public String getFirstName() {
    return firstName;
    }

    public String getLastName() {
    return lastName;
    }
    public String getBirthday(){
        return birthday;
    }

    public String getPosition() {
    return position;
    }

    public String getStatus() {
    
    return status;
    }

    public double getBasicSalary() {
    return basicSalary;
    }

    public double getRiceSubsidy() {
    return riceSubsidy;
    }

    public double getPhoneAllowance() {
    return phoneAllowance;
    }

    public double getClothingAllowance() {
    return clothingAllowance;
    }
    // Getters for SSS, PhilHealth, and Pag-Ibig
    public double getSssContribution() {
    return sssContribution;
    }

    public double getPhilHealthContribution() {
    return philHealthContribution;
    }

    public double getPagIbigContribution() {
    return pagIbigContribution;
    }

    public void displayInfo() {
    System.out.println("Employee ID: " + employeeId);
    System.out.println("Name: " + firstName + " " + lastName);
    System.out.println("Position: " + position);
    System.out.println("Status: " + status);
    System.out.println("Hourly Rate: PHP " + hourlyRate);
}
    
    
}
