/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp1project;
import java.io.File;
/**
 *
 * @author Amir
 */
public class test {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/mycompany/cp1project/Attendance.csv");
        if (file.exists()) {
            System.out.println("✅ CSV File Found: " + file.getAbsolutePath());
        } else {
            System.out.println("❌ CSV File NOT Found! Check the file location.");
        }
    }
}


