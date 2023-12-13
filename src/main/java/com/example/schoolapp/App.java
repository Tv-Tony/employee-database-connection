package com.example.schoolapp;

import java.sql.Connection;
import java.sql.ResultSet;

import com.example.schoolapp.classes.SqlConnection;

public class App {
    static String DBSERVER = "utbserver.database.windows.net";
    static int DBPORT = 1433;
    static String DBNAME = "utb-database";
    static String USERNAME = "utbadmin@utbserver";
    static String PASSWORD = "UTB_user123";

    public static void main(String[] args) {
        SqlConnection sqlConn = new SqlConnection();
        Connection conn = sqlConn.connectToDb(DBSERVER, DBPORT, DBNAME, USERNAME, PASSWORD);

        // Add a new employee
        addEmployee(sqlConn, conn, "Toby", 1);

        // Get all employees
        listEmployees(sqlConn, conn);

        // Get employee by ID
        findSpecificEmployee(sqlConn, conn, 1);

        // Update salary of a specific employee
        updateEmployeeSalary(sqlConn, conn, 5, 3000);

        // Delete a specific employee
        removeEmployee(sqlConn, conn, 28);

        // Get all employees
        listEmployees(sqlConn, conn);
        
        // Close the connection
        closeConnection(conn);

    }

    private static void addEmployee(SqlConnection sqlConn, Connection conn, String name, float salary) {
        int count = sqlConn.addEmployee(conn, name, salary);
        if (count == 1)
            System.out.println("New employee added");
        else
            System.out.println("The employee wasn't added");
    }

    private static void listEmployees(SqlConnection sqlConn, Connection conn) {
        ResultSet data = sqlConn.getEmployees(conn);
        System.out.println("All Employees:");
        System.out.println("Id\tName\tSalary");
        try {
            while (data.next()) {
                int id = data.getInt("Id");
                String name = data.getString("Name");
                float salary = data.getFloat("Salary");

                System.out.println(id + "\t" + name + "\t" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findSpecificEmployee(SqlConnection sqlConn, Connection conn, int employeeId) {
        ResultSet specificEmployee = sqlConn.getEmployeeById(conn, employeeId);
        System.out.println("Specific Employee by ID:");
        System.out.println("Id\tName\tSalary");
        try {
            while (specificEmployee.next()) {
                int id = specificEmployee.getInt("Id");
                String name = specificEmployee.getString("Name");
                float salary = specificEmployee.getFloat("Salary");
                System.out.println(id + "\t" + name + "\t" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployeeSalary(SqlConnection sqlConn, Connection conn, int employeeId, float newSalary) {
        int updateResult = sqlConn.updateEmployeeSalary(conn, employeeId, newSalary);
        if (updateResult == 1)
            System.out.println("Employee salary updated");
        else
            System.out.println("Failed to update employee salary");
    }

    private static void removeEmployee(SqlConnection sqlConn, Connection conn, int employeeId) {
        int deleteResult = sqlConn.deleteEmployee(conn, employeeId);
        if (deleteResult == 1)
            System.out.println("Employee deleted");
        else
            System.out.println("Failed to delete employee");
    }

    private static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

