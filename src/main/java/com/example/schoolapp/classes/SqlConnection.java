package com.example.schoolapp.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection implements ISqlConnection{

    @Override
    public Connection connectToDb(String dbServer, int dbPort, String dbName, String userName, String password) {
        Connection conn = null;
        String connectionString = String.format(
            "jdbc:sqlserver://%s:%d;"
            + "database=%s;"
            + "user=%s;"
            + "password=%s;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "loginTimeout=30;"
            + "hostNameInCertificate=*.database.windows.net;",
            dbServer, dbPort, dbName, userName, password
        );

        try {
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection was succsesful");
        } catch (SQLException e) {
            System.out.println("SQL Server connection exception");
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public ResultSet getEmployees(Connection conn) {
       ResultSet rs = null;
       String query = "Select * from Employee";

       try {
        Statement s = conn.createStatement(); 
        rs = s.executeQuery(query);

       } catch (SQLException e) {
        System.out.println("SQL Server reading exception");
            e.printStackTrace();
       }
       return rs;
    }

    @Override
    public int addEmployee(Connection conn, String name, float salary) {
       int result = 0;
       String sql = String.format(
        "Insert Into Employee(Name, Salary) Values ('%s', %f)",
        name, salary
       );

       try {
        Statement s = conn.createStatement();
        result = s.executeUpdate(sql);
       } catch (SQLException e) {
        System.out.println("SQL Server reading exception");
            e.printStackTrace();
       }
       return result;
    }
        
    @Override
    public ResultSet getEmployeeById(Connection conn, int employeeId) {
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Id = " + employeeId;
    
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
    
        } catch (SQLException e) {
            System.out.println("SQL Server reading exception");
            e.printStackTrace();
        }
        return rs;
    }
    
    @Override
    public int updateEmployeeSalary(Connection conn, int employeeId, float newSalary) {
        int result = 0;
        String sql = String.format("UPDATE Employee SET Salary = %f WHERE Id = %d", newSalary, employeeId);
    
        try {
            Statement s = conn.createStatement();
            result = s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("SQL Server reading exception");
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int deleteEmployee(Connection conn, int employeeId) {
        int result = 0;
        String sql = "DELETE FROM Employee WHERE Id = " + employeeId;

        try {
        Statement s = conn.createStatement();
        result = s.executeUpdate(sql);

        System.out.println("Deleted " + result + " employee(s) with ID " + employeeId);

        } catch (SQLException e) {
        System.out.println("SQL Server delete exception");
        e.printStackTrace();
        }
        return result;
    }

}
