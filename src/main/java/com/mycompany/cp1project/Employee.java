/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;

public class Employee {
    String employeeId;
    String lastName, firstName, birthday, address, phoneNumber;
    String sssNumber, philHealth, tinNumber, pagIbig, status, position, supervisor;
    double basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
    double grossSemiMonthlyRate, hourlyRate;
    private double sssContribution;
    private double philHealthContribution;
    private double pagIbigContribution;
    private double withholdingTax;

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

    public void calculateContributions() {
        double weeklySalary = basicSalary / 2;
        this.sssContribution = weeklySalary * 0.045;
        this.philHealthContribution = weeklySalary * 0.025;
        this.pagIbigContribution = weeklySalary * 0.02;
    }

    public double getSssContribution() { return sssContribution; }
    public double getPhilHealthContribution() { return philHealthContribution; }
    public double getPagIbigContribution() { return pagIbigContribution; }
    public double getHourlyRate() { return hourlyRate; }

    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthday() { return birthday; }
    public String getPosition() { return position; }
    public String getStatus() { return status; }
    public double getBasicSalary() { return basicSalary; }
    public double getRiceSubsidy() { return riceSubsidy; }
    public double getPhoneAllowance() { return phoneAllowance; }
    public double getClothingAllowance() { return clothingAllowance; }

    public double getWithholdingTax() { return withholdingTax; }
    public void setWithholdingTax(double withholdingTax) { this.withholdingTax = withholdingTax; }

    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Position: " + position);
        System.out.println("Status: " + status);
        System.out.println("Hourly Rate: PHP " + hourlyRate);
        System.out.println("SSS Contribution: PHP " + sssContribution);
        System.out.println("PhilHealth Contribution: PHP " + philHealthContribution);
        System.out.println("Pag-IBIG Contribution: PHP " + pagIbigContribution);
    }
}
