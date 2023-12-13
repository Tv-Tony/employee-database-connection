package com.example.schoolapp.classes;

import java.sql.Connection;
import java.sql.ResultSet;

public interface ISqlConnection {
    Connection connectToDb(String dbServer, int dbPort, String dbName, String userName, String password);

    ResultSet getEmployees(Connection conn);

    int addEmployee(Connection conn, String name, float salary);

    ResultSet getEmployeeById(Connection conn, int employeeId);

    int updateEmployeeSalary(Connection conn, int employeeId, float newSalary);

    int deleteEmployee(Connection conn, int employeeId);
}