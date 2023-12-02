package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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
	            String sql = "INSERT INTO patient (first_name, last_name, dob, gender, address, city, state, zip_code, country, email, phone_number ,secure_hash) " +
	                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
	            preparedStatement.setString(12, patient.getSecretKey());

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
	
	/**
	 * This function is use to retrive data from DB using JDBC which is configure in 
	 * com.atm.config.DBConfig class
	 * 
	 * @param con - is a Connection object initiated from dataSource Class
	 * @param umail - it is use to as parameter in select query to fetch email and name
	 * @param Uphone - it is use to as parameter in select query to fetch email and name
	 * 
	 * @return HashMap<String, String> mailandFullName where,
	 *  keys are "mail" and "fullName".
	 * */
	public HashMap<String, String>  getPatientMailAdressAndName(Connection con,String umail,String Uphone) throws SQLException {
		java.sql.Statement statement = con.createStatement();
		
		HashMap<String, String> mailandFullName =new HashMap<String, String>();
		String query="Select email,CONCAT(IFNULL(first_name, ''), ' ', IFNULL(last_name, '')) AS full_name "
				+ "from ayurveda.patient  "
				+ "where 1=1";
		if(! umail.isBlank()) {
			query = query + " and email= '" +umail +"' ";
		}
		else if(! Uphone.isBlank()) {
			query = query + " and phone_number='" +Uphone +"' ";
		}
		System.out.println(query);
		
		try {
			ResultSet rs=statement.executeQuery(query);
			if (rs.next()) {
			    mailandFullName.putIfAbsent("mail", rs.getString("email"));
			    mailandFullName.putIfAbsent("fullName", rs.getString("full_name"));
			    // Process or use the retrieved string
			    System.out.println("Retrieved String: " + mailandFullName.get("mail"));
			    System.out.println("Retrieved String: " + mailandFullName.get("fullName"));
			} else {
			    System.out.println("No data found.");
			}
			System.out.println(mailandFullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			statement.close();
			con.close();
		}
		return mailandFullName;
	}
	
}

