package com.jdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BusinessLogic {
	static String url = "jdbc:mysql://localhost:3306/palle";
	static String user = "root";
	static String password = "admin@2002";
	
	// Query to Create Table
	static String create = "create table student(id int,name varchar(20) ,email varchar(20),phone bigint)";
	
	// Query to Insert Record
	static String insert = "insert into student values(?,?,?,?)";
	
	// Query to Update Record
	static String update = "update student set phone = ? where id = ?";
	
	// Query to Delete Record
	static String delete = "delete from student where id = ?";
	
	// Query to View All Records
	static String selectall = "select * from student";
	
	static Connection connection = null;
	static Statement s = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	// Table Creation Method
	public static void createTable() {
		try {
			// 1.Load & Register Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2.Establish Connection
			connection = DriverManager.getConnection(url,user,password);
			
			// 3.Prepare Query
			s = connection.createStatement();
			
			// 4.Execute Query
			s.executeUpdate(create);		
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				// 5.Close Connections
				s.close();
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}			
		}		
	}
	
	// Record Insertion Method
	public static void insert(int id ,String name, String email, long phone) {
		try {			
		Class.forName("com.mysql.cj.jdbc.Driver");	
		connection = DriverManager.getConnection(url,user,password);
		ps = connection.prepareStatement(insert);
		ps.setInt(1,id);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.setLong(4, phone);
		ps.executeUpdate();
		}
		catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				connection.close();
			}			
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}
	
	// Field Updation Method
	public static void update(int id , long phone){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			ps = connection.prepareStatement(update);
			ps.setLong(1, phone);
			ps.setInt(2, id);
			ps.executeUpdate();
		}	
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		finally{
			try {
				ps.close();
				connection.close();
			}		
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Record Deletion Method
	public static void delete(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			ps = connection.prepareStatement(delete);
			ps.setInt(1, id);
			ps.executeUpdate();
		}		
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		finally {
			try {
				ps.close();
				connection.close();
			}		
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Records Viewing Method
	public static ArrayList<Student> selectAll() {
		ArrayList<Student> al = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			s = connection.createStatement();
			rs = s.executeQuery(selectall);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				long phone = rs.getLong("phone");
				Student s = new Student(id, name, email, phone);
				al.add(s);			
			}
		} 	
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		finally {
			try {
				rs.close();
				s.close();
				connection.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}	
}

