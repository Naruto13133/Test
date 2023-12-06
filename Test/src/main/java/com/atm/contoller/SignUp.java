package com.atm.contoller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base32;

import com.atm.config.DBConfig;
import com.atm.dao.User;
import com.atm.pojo.Patient;
import com.atm.dao.*; 
import com.atm.service.*;
import com.google.zxing.WriterException;
/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
    
	 
	
	DBConfig getCon= new DBConfig();
	User userDao= new User();
	Patient patient= new Patient();
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String dobStr = request.getParameter("dob"); 
	    String country = request.getParameter("country");
	    String genderParam = request.getParameter("gender");
	    String addressParam = request.getParameter("address");
	    String cityParam = request.getParameter("city");
	    String stateParam = request.getParameter("state");
	    String zipCodeParam = request.getParameter("zipCode");
	    String emailParam = request.getParameter("email");
	    String phoneNumberParam = request.getParameter("phoneNumber");
	    Patient patient = new Patient();
	    patient.setFirstName(firstName);
	    patient.setLastName(lastName);
	    Date dob = null;
	    try {
	        LocalDate dobLocal = LocalDate.parse(dobStr); // Use appropriate date parsing based on your date format
	        dob = Date.valueOf(dobLocal);
	    } catch (DateTimeParseException e) {
	        // Handle date parsing exception
	        e.printStackTrace();
	    }
	    patient.setDateOfBirth(dob);

	    // Set other fields using local variables
	    patient.setGender(genderParam);
	    patient.setAddress(addressParam);
	    patient.setCity(cityParam);
	    patient.setState(stateParam);
	    patient.setZipCode(zipCodeParam);
	    patient.setEmail(emailParam);
	    patient.setPhoneNumber(phoneNumberParam);
	    patient.setCountry(country);
	    
	    
	   try {
		   String secretekey=TwoStepVerificationService.generateSecretKey();
			patient.setSecretKey(secretekey);
		int created=userDao.CreateUser(patient);
		
		if (created != 1) 
		    {
		    	//response.sendRedirect("/");
		    	throw new NullPointerException();
		    }
		    else {
		    	//TwoStepVerificationService.createQRCode(patient.getFirstName()+" "+patient.getLastName() ,patient.getPhoneNumber(),secreteUrl);
		    	//response.sendRedirect("");
			}
		}catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			//response.sendRedirect("" );
	}
	}

}
