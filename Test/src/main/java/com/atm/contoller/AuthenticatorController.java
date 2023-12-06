package com.atm.contoller;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import com.atm.dao.TwoStepAuthDao;
import com.atm.dao.User;
import com.atm.service.*;


public class AuthenticatorController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		User daoAuth=new User();
		TwoStepAuthDao twoStepAuthDao = new TwoStepAuthDao();
		
		String  secketkey="";
		HttpSession session= req.getSession();
		long sessionCreatedAt = session.getCreationTime();
		String email = (String) session.getAttribute("email");
		String phone = (String) session.getAttribute("phone");
		boolean originFromLogin = false;
		Enumeration<String> a = session.getAttributeNames();
		
		for(int i=0; i==0;i=i+0) {
			boolean b= a.nextElement().equals("origin");
			System.out.println("b:"+b);
			if(b) {
				originFromLogin = true;
				System.out.println("origin in for loop b:"+originFromLogin);
				break;
			}else if(! a.hasMoreElements()) {
				break;
			}
		}
		System.out.println("before timer");
		System.out.println("origin:" + originFromLogin);
		// Athentication is valid till 4 minute before creation of session
		if(sessionCreatedAt > 240000 && ! originFromLogin )  {
			System.out.println("In side the signupp");
			try {
				System.out.println("email:"+email);
				int i = daoAuth.isAuthenticated(email, phone);
				if(i == 1) {
					System.out.println("Test1");
					secketkey = twoStepAuthDao.getSecureHashUsingEmailnPohne(email, phone);
					System.out.println(secketkey);
				}else {
					if( new TwoStepAuthDao().updateAuthenticationStatus(email, phone)) {
					}else {
						res.sendRedirect("/Test?athnt=false");
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		String OTP = (String) req.getParameter("otp");
		String userOTP = TwoStepVerificationService.getTOTPCode(secketkey);
			if(OTP.equals(userOTP)) {
				
				res.sendRedirect("/Test?athnt=true");
			}
			else {
				res.sendRedirect("/Test?athnt=false");
			}
		}//For Login User Authentiction
		else if (originFromLogin) {
			System.out.println("In side the origin!");
			try {
				System.out.println("email:"+email);

				int i = daoAuth.isAuthenticated(email, phone );
				if(i == 1) {
					System.out.println("Test2");
					
					secketkey = twoStepAuthDao.getSecureHashUsingEmailnPohne(email, phone);
					
					if ( 1 !=  daoAuth.isAuthenticated(email, phone)) {
						res.sendRedirect("/Test?athnt=false");
					}
					//sucssess page
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		String OTP = (String) req.getParameter("otp");
		String userOTP = TwoStepVerificationService.getTOTPCode(secketkey);
			if(OTP.equals(userOTP)) {
				res.sendRedirect("/Test?athnt=true");
			}else {
				res.sendRedirect("/Test?athnt=false");
			}
		}else{
			res.sendRedirect("/Test?athnt=false");
		}
		
		
	}

}
