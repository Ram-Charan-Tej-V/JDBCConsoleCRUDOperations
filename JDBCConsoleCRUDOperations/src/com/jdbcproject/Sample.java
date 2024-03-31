package com.jdbcproject;

import java.util.ArrayList;

public class Sample {

	public static void main(String[] args) {
		
		// Table Creation Method Call
		BusinessLogic.createTable();
		
		// Record Insertion Method Call
		BusinessLogic.insert(1, "ram", "ram911@gmail.com", 9876543210L);
		
		// Field Updation Method Call
		BusinessLogic.update(1, 8876543211L);
		
		// Record Deletion Method Call
		BusinessLogic.delete(1);
	
		// Records Viewing Method Call
		ArrayList<Student> al = BusinessLogic.selectAll();
		for(Student s : al)
		{
				System.out.println(s.getId() + " " + s.getName() + " " + s.getEmail() + " " + s.getPhone());
		}
	}
}
