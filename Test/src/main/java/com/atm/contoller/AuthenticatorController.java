package com.atm.contoller;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.atm.pojo.Patient;
import com.atm.service.*;


public class AuthenticatorController extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
	
	Patient patient =(Patient) req.getAttribute("patient");
	String secretekey= (String) req.getAttribute("secretekey");
	String a = TwoStepVerificationService.getGoogleAuthenticatorBarCode(secretekey,patient.getEmail() , patient.getFirstName()+patient.getLastName());
	ByteArrayOutputStream out = TwoStepVerificationService.imageBydeData(a);
	// Set response content type to image
    res.setContentType("image/png");
    
    res.getOutputStream().write(out.toByteArray());
    
    res.getOutputStream().flush();
    
     

    // Set response content type to application/json
    res.setContentType("application/json");
 // Write JSON string to the response output stream
    res.getWriter().write(jsonString);
    
	}

}
