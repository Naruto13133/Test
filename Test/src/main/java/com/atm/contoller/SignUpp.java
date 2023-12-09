 package com.atm.contoller;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


import com.atm.config.DBConfig;
import com.atm.dao.User;
import com.atm.pojo.Patient;
import com.atm.service.TwoStepVerificationService;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SignUpp extends HttpServlet {
	
	DBConfig getCon= new DBConfig();
	User userDao= new User();
	Patient patient= new Patient();
	final static String  AUTHENTICATION_PAGE = "/Authenticate.jsp";

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
	    String password = request.getParameter("password");
	    Patient patient = new Patient();
	    patient.setFirstName(firstName);
	    patient.setLastName(lastName);
	    Date dob = null;
	    try {
	        LocalDate dobLocal = LocalDate.parse(dobStr); // Use appropriate date parsing based on your date format
	        dob = Date.valueOf(dobLocal);
	    }
	    catch (DateTimeParseException e) {
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
	    patient.setPassword(password);
	    

	   
	   
	   try {
		   if (! new User().isUserPresent(patient.getEmail())){
			   String secretekey=TwoStepVerificationService.generateSecretKey();
			   patient.setSecretKey(secretekey);
			   int created=userDao.CreateUser(patient);

			   	if (created != 1){
		    	//response.sendRedirect("/");
			   		throw new NullPointerException();
			   	}
			   	else {
			   		String secreteUrl = TwoStepVerificationService.getGoogleAuthenticatorBarCode(secretekey, patient.getEmail(), patient.getPhoneNumber());
			   		//TwoStepVerificationService.createQRCode(patient.getFirstName()+patient.getLastName() ,patient.getPhoneNumber(),secreteUrl);
			   		//response.sendRedirect("");
			   		java.io.ByteArrayOutputStream byteData = TwoStepVerificationService.imageBydeData(secreteUrl);
			   		String base64Image = TwoStepVerificationService.base64StringFromByte (byteData);
			   		RequestDispatcher dispatcher = request.getRequestDispatcher( AUTHENTICATION_PAGE );
			   		
			   		request.setAttribute("base64Image", base64Image);
			   		// Forward the request to another servlet or JSP
			   		HttpSession session = request.getSession();
			   		session.setMaxInactiveInterval(60*5);
			   		session.setAttribute("email", patient.getEmail());
			   		session.setAttribute("phone", patient.getPhoneNumber());
			   		dispatcher.forward(request, response);
			   	}
		   }else {
			   response.sendRedirect("/Test/SignUp.html?userpresent=true");
		   }	
	   }
	   catch (Exception e) {
			e.printStackTrace();
	   }
	   finally {
	   }
		   	
	   
	}
	
	
}
