package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.atm.config.*;
import com.atm.pojo.Patient;

public class User {
	
	/**
	 * @throws SQLException 
	 * */
	public int CreateUser(Connection con, Patient patient) throws SQLException 
	{
		int created=0;
		 PreparedStatement preparedStatement = null;

	        try {
	            // Establish database connection (con variable)
	            // ...

	            // SQL query to insert patient data
	            String sql = "INSERT INTO patient (first_name, last_name, dob, gender, address, city, state, zip_code, country, email, phone_number) " +
	                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            // Create PreparedStatement
	            preparedStatement = con.prepareStatement(sql);

	            // Set values from the Patient object to the PreparedStatement
	            preparedStatement.setString(1, patient.getFirstName());
	            preparedStatement.setString(2, patient.getLastName());
	            preparedStatement.setDate(3, patient.getDateOfBirth()); // Assuming dob is a java.sql.Date object
	            preparedStatement.setString(4, patient.getGender());
	            preparedStatement.setString(5, patient.getAddress());
	            preparedStatement.setString(6, patient.getCity());
	            preparedStatement.setString(7, patient.getState());
	            preparedStatement.setString(8, patient.getZipCode());
	            preparedStatement.setString(9, patient.getCountry());
	            preparedStatement.setString(10, patient.getEmail());
	            preparedStatement.setString(11, patient.getPhoneNumber());

	            // Execute the query
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Patient data inserted successfully.");
	                created=1;
	            } else {
	                System.out.println("Failed to insert patient data.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	preparedStatement.close();
	        	con.close(); 
	        }
		
		return created;
	}
	
	public boolean CheckUser(Connection con) {
		
		return false;
	}
	
	public boolean InsertPatientDosha(Connection con) {
		
		return false;
	}

	public boolean RetrivePatientDosha(Connection con) {
		
		return false;
	}
}

