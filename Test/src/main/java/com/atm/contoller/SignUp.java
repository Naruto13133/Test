package com.atm.contoller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.atm.config.DBConfig;
import com.atm.dao.User;
import com.atm.pojo.Patient;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DBConfig getCon= new DBConfig();
	User userDao= new User();
	Patient patient= new Patient();
	
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		 // Retrieve patient data from HTML form
		PrintWriter pw= response.getWriter();
		
		Map m=request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();

            while(it.hasNext()){

                Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>)it.next();

                String key             = entry.getKey();
                String[] value         = entry.getValue();

                pw.println("Key is "+key+"<br>");

                    if(value.length>1){    
                        for (int i = 0; i < value.length; i++) {
                            pw.println("<li>" + value[i].toString() + "</li><br>");
                        }
                    }else
                            pw.println("Value is "+value[0].toString()+"<br>");

                    pw.println("-------------------<br>");
            }

        pw.close();    
		
		
		String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String dobStr = request.getParameter("dob"); // Assuming date of birth as a String
	    // Retrieve other form fields similarly...
	    String country = request.getParameter("country");

	    // Create local variables to store parameter values
	    String genderParam = request.getParameter("gender");
	    String addressParam = request.getParameter("address");
	    String cityParam = request.getParameter("city");
	    String stateParam = request.getParameter("state");
	    String zipCodeParam = request.getParameter("zipCode");
	    String emailParam = request.getParameter("email");
	    String phoneNumberParam = request.getParameter("phoneNumber");

	    // Create a new Patient object and set the retrieved data
	    Patient patient = new Patient();
	    patient.setFirstName(firstName);
	    patient.setLastName(lastName);

	    // Parse date string to java.sql.Date (if applicable)
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
	    
	   Connection con= getCon.GetMysqlCon();
	   try {
		int i=userDao.CreateUser(con,patient);
	} catch (SQLException e) {
			e.printStackTrace();
	}
	   
	    
	    System.out.println("Received Patient Data:");
	    System.out.println("First Name: " + firstName);
	    System.out.println("Last Name: " + lastName);
	    System.out.println("Date of Birth: " + dob);
	    System.out.println("Gender: " + genderParam);
	    System.out.println("Address: " + addressParam);
	    System.out.println("City: " + cityParam);
	    System.out.println("State: " + stateParam);
	    System.out.println("Zip Code: " + zipCodeParam);
	    System.out.println("Country: " + country);
	    System.out.println("Email: " + emailParam);
	    System.out.println("Phone Number: " + phoneNumberParam);
	    
	    
	    
		
	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 // Retrieve patient data from HTML form
		String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String dobStr = request.getParameter("dob"); // Assuming date of birth as a String
	    // Retrieve other form fields similarly...
	    String country = request.getParameter("country");

	    // Create local variables to store parameter values
	    String genderParam = request.getParameter("gender");
	    String addressParam = request.getParameter("address");
	    String cityParam = request.getParameter("city");
	    String stateParam = request.getParameter("state");
	    String zipCodeParam = request.getParameter("zipCode");
	    String emailParam = request.getParameter("email");
	    String phoneNumberParam = request.getParameter("phoneNumber");

	    // Create a new Patient object and set the retrieved data
	    Patient patient = new Patient();
	    patient.setFirstName(firstName);
	    patient.setLastName(lastName);

	    // Parse date string to java.sql.Date (if applicable)
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
	    
	   Connection con= getCon.GetMysqlCon();
	   try {
		int i=userDao.CreateUser(con,patient);
	} catch (SQLException e) {
			e.printStackTrace();
	}
	   
	    
	    System.out.println("Received Patient Data:");
	    System.out.println("First Name: " + firstName);
	    System.out.println("Last Name: " + lastName);
	    System.out.println("Date of Birth: " + dob);
	    System.out.println("Gender: " + genderParam);
	    System.out.println("Address: " + addressParam);
	    System.out.println("City: " + cityParam);
	    System.out.println("State: " + stateParam);
	    System.out.println("Zip Code: " + zipCodeParam);
	    System.out.println("Country: " + country);
	    System.out.println("Email: " + emailParam);
	    System.out.println("Phone Number: " + phoneNumberParam);
	    
	    
	    
		
	}

}
