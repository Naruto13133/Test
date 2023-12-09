package com.atm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import com.atm.config.*;
import com.atm.pojo.Patient;

public class User {
	
	public String secketkey;
	static DBConfig dbconfig = new DBConfig(); 
	/**
	 * @throws SQLException 
	 * */
	public int CreateUser( Patient patient) throws SQLException 
	{
		Connection con = dbconfig.GetMysqlCon();
		int created=0;
		 PreparedStatement preparedStatement = null;

	        try {
	            // Establish database connection (con variable)
	            // ...

	            // SQL query to insert patient data
	            String sql = "INSERT INTO patient (first_name, last_name, dob, gender, address, city, state, zip_code, country, email, phone_number ,secure_hash,password) " +
	                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
	            preparedStatement.setString(13, patient.getPassword());

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
	            con.rollback();
	        } finally {
	        	preparedStatement.close();
	        	con.close(); 
	        }
		
		return created;
	}
	
	public boolean CheckUser() {
//		Connection con = dbconfig.GetMysqlCon();
		return false;
	}
	
	public boolean InsertPatientDosha() {
//		Connection con = dbconfig.GetMysqlCon();
		return false;
	}

	public boolean RetrivePatientDosha() {
//		Connection con = dbconfig.GetMysqlCon();
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
	public HashMap<String, String>  getPatientMailAdressAndName(String umail,String Uphone) throws SQLException {
		Connection con = dbconfig.GetMysqlCon();
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
	
	/**
	 *this method is use to check if user is authenticate  
	 *and return 1 and 0 if present and not present in data base
	 * @param Connection con object for setting up the connection to db and 
	 * @param String email or 
	 * @param String phone to check user presents- email 
	 * 
	 * @return int i
	 * 
	 * */
	public int isAuthenticated(String email, String phone ) throws SQLException{
		Connection con = dbconfig.GetMysqlCon();
		int i=0;
		String query="Select * "
					+ "from ayurveda.patient  "
					+ "where  email = ? and is_authenticated = 'Y' and phone_number = ? ";
			System.out.println("query:");		
			PreparedStatement  statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, phone);
			ResultSet rs = statement.executeQuery();
		try {
			if (rs.next()) {
			    i=1;
			} else {
			    System.out.println("No data found.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			statement.close();
			con.close();
		}
		return i;
	}

	public boolean isUserPresent( String email) throws SQLException {
		Connection con = dbconfig.GetMysqlCon();
		boolean result = false;
		String sql = "select email from ayurveda.patient where email = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(email.equals(rs.getString("email")))
				{
					result = true;
				}
			}
			
		}catch(Exception e) {
			result = false;
			e.printStackTrace();
		}finally {
			con.close();
		}
		return result; 
	}
	
	public Patient getPatiest( String email) throws SQLException{
		Connection con = dbconfig.GetMysqlCon();
		String q = "select * from ayurveda.patient where email = \'"+email+"\'";
		Patient patient=new Patient();
		try {
			
			Statement s=con.createStatement();
			
			ResultSet rs = s.executeQuery(q);
			if(rs.next()) {
				patient.setFirstName(rs.getString("first_name"));
			    patient.setLastName(rs.getString("last_name"));
			    Date dob = null;
			    try {
			        LocalDate dobLocal = LocalDate.parse(rs.getString("dob")); // Use appropriate date parsing based on your date format
			        dob = Date.valueOf(dobLocal);
			    }
			    catch (DateTimeParseException e) {
			        // Handle date parsing exception
			        e.printStackTrace();
			    }
			    patient.setDateOfBirth(dob);

			    // Set other fields using local variables
			    patient.setGender(rs.getString("gender"));
			    patient.setAddress(rs.getString("address"));
			    patient.setCity(rs.getString("city"));
			    patient.setState(rs.getString("state"));
			    patient.setZipCode(rs.getString("zip_code"));
			    patient.setEmail(rs.getString("email"));
			    patient.setPhoneNumber(rs.getString("phone_number"));
			    patient.setCountry(rs.getString("country"));
			    patient.setPassword(rs.getString("password"));
			    patient.setSecretKey(rs.getString("secure_hash"));
			    patient.setIsAuthenticated(rs.getString("is_authenticated"));
			}
		}catch(Exception e) {
		
			con.close();
		}
		System.out.println("patient:"+ patient);
		return patient;
	}
}

