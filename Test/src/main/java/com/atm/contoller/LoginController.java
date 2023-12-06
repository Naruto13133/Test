package com.atm.contoller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.SQLException;

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

public class LoginController extends HttpServlet {
	
	final String AUTHENTICATION_PAGE="/Authenticate.jsp";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email =(String) request.getParameter("email");
		String pass =(String) request.getParameter("password");
		
		User user = new User();
		Patient patient= new Patient();
		try {
			
			patient = user.getPatiest(email);
			System.out.println("patient:"+ patient);
			if(patient.getPassword().equals(pass) && patient.getEmail().equals(email)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher( AUTHENTICATION_PAGE );
	   			
	   			// Forward the request to another servlet or JSP
	   			HttpSession session = request.getSession();
	   			session.setAttribute("email", patient.getEmail());
	   			session.setAttribute("phone", patient.getPhoneNumber());
	   			session.setAttribute("origin", "login");
	   			dispatcher.forward(request, response);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
