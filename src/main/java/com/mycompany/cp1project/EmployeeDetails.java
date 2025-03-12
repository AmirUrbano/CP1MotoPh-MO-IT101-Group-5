/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cp1project;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Amir
 */
public class EmployeeDetails {

    
    private List<Employee> employeeList;

    public EmployeeDetails() {
        employeeList = new ArrayList<>();

        // Adding employees directly from the CSV data
        employeeList.add(new Employee("10001", "Garcia", "Manuel III", "10/11/1983", 
            "Valero Carpark Building Valero Street 1227, Makati City", "966-860-270", "44-4506057-3",
            "820126853951", "442-605-657-000", "691295330870", "Regular", "Chief Executive Officer",
            "N/A", 90000, 1500, 2000, 1000, 45000, 535.71));

        employeeList.add(new Employee("10002", "Lim", "Antonio", "06/19/1988", 
            "San Antonio De Padua 2, Block 1 Lot 8 and 2, Dasmarinas, Cavite", "171-867-411", "52-2061274-9",
            "331735646338", "683-102-776-000", "663904995411", "Regular", "Chief Operating Officer",
            "Garcia, Manuel III", 60000, 1500, 2000, 1000, 30000, 357.14));

        employeeList.add(new Employee("10003", "Aquino", "Bianca Sofia", "08/04/1989",
            "Rm. 402 4/F Jiao Building Timog Avenue Cor. Quezon Avenue 1100, Quezon City", "966-889-370",
            "30-8870406-2", "177451189665", "971-711-280-000", "171519773969", "Regular", "Chief Finance Officer",
            "Garcia, Manuel III", 60000, 1500, 2000, 1000, 30000, 357.14));

        employeeList.add(new Employee("10004", "Reyes", "Isabella", "06/16/1994",
            "460 Solanda Street Intramuros 1000, Manila", "786-868-477", "40-2511815-0",
            "341911411254", "876-809-437-000", "416946776041", "Regular", "Chief Marketing Officer",
            "Garcia, Manuel III", 60000, 1500, 2000, 1000, 30000, 357.14));

        employeeList.add(new Employee("10005", "Hernandez", "Eduard", "09/23/1989",
            "National Highway, Gingoog, Misamis Occidental", "088-861-012", "50-5577638-1",
            "957436191812", "031-702-374-000", "952347222457", "Regular", "IT Operations and Systems",
            "Lim, Antonio", 52670, 1500, 1000, 1000, 26335, 313.51));

        // Add more employees here following the same pattern...
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee employee : employeeList) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null; // Return null if employee not found
    }

    
}