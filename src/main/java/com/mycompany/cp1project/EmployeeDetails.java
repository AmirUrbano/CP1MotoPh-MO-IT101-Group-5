/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cp1project;

import java.util.ArrayList;
/**
 *
 * @author Amir
 */
public class EmployeeDetails {

    public static void main(String[] args) {
       // Creat a list of employees
       
       ArrayList<Employee> employees = new ArrayList<>();
       
       // Add employees 
       
       employees.add(new Employee(1001, "Garcia", "Manuel III", "10/11/1983",
                "Valero Carpark Building, Makati City", "966-860-270", 
                "44-4506057-3", "820126853951", "442-605-657-000", "691295330870",
                "Regular", "Chief Executive Officer", "N/A", 
                90000, 1500, 2000, 1000, 45000, 535.71));

        employees.add(new Employee(1002, "Lim", "Antonio", "06/19/1988",
                "San Antonio De Padua, Cavite", "171-867-411",
                "52-2061274-9", "331735646338", "683-102-776-000", "663904995411",
                "Regular", "Chief Operating Officer", "Garcia, Manuel III", 
                60000, 1500, 2000, 1000, 30000, 357.14));

        employees.add(new Employee(1003, "Aquino", "Bianca", "09/05/1992",
                "Paseo de Roxas, Makati City", "123-456-789",
                "11-2223334-5", "987654321001", "111-222-333-000", "123456789012",
                "Probation", "HR Manager", "Antonio Lim", 
                45000, 1500, 1500, 800, 22500, 267.85));
       
       // Display all employees 
       
       for (Employee emp : employees){
           emp.displayInfo();
       }
       
       
       
       
       
       
       
    }
}
