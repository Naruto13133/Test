package com.atm.contoller;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
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
		boolean isauthenticated = true;
		String  secketkey="";
		boolean originFromLogin = false;
		
		HttpSession session= req.getSession();
		long sessionCreatedAt = session.getCreationTime();
		String email = (String) session.getAttribute("email");
		String phone = (String) session.getAttribute("phone");
		System.out.println("email:"+email
				+ "\n phone:"+phone);
		try{
			int i = daoAuth.isAuthenticated(email, phone );
			if(i == 0) {
				isauthenticated = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Enumeration<String> a = session.getAttributeNames();
		
		for(int i=0; i==0; i=i+0) {
			if(!a.hasMoreElements() ) {
				break;
			}else {
			boolean b= a.nextElement().equals("origin");
			System.out.println("b:"+b);
				System.out.println("origin in for loop b:"+originFromLogin);
				if(b) {
					originFromLogin = true;
				}
			}
		}
		
		System.out.println("before timer");
		System.out.println("origin:" + originFromLogin);
		System.out.println();
		// Athentication is valid till 4 minute before creation of session
		if(sessionCreatedAt > 240000 && ! originFromLogin && !isauthenticated)  {
			System.out.println("In side the signupp");
			try {
				secketkey = twoStepAuthDao.getSecureHashUsingEmailnPohne(email, phone);
				System.out.println("email:"+email);
				System.out.println("secketkey:"+secketkey);
					new TwoStepAuthDao().updateAuthenticationStatus(email, phone);
					isauthenticated = true;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		String OTP = (String) req.getParameter("otp");
		String userOTP = TwoStepVerificationService.getTOTPCode(secketkey);
			if(OTP.equals(userOTP) && isauthenticated) {
				res.sendRedirect("/Test?athnt=true");
			}else {
				res.sendRedirect("/Test?athnt=false");	
				}
			}
		else if (originFromLogin && isauthenticated) {
			System.out.println("In side the origin!");
			
				System.out.println("email:"+email);
				try {
					
						System.out.println("Test2");
						
						secketkey = twoStepAuthDao.getSecureHashUsingEmailnPohne(email, phone);
						
						if (!isauthenticated) {
							res.sendRedirect("/Test?athnt=false");
						}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
		String OTP = (String) req.getParameter("otp");
		String userOTP = TwoStepVerificationService.getTOTPCode(secketkey);
			if(OTP.equals(userOTP)) {
				res.sendRedirect("/Test/DoshaSurvey.jsp?athnt=true");
			}else {
				res.sendRedirect("/Test?athnt=false");
			}
		}
	}}
		
		
	


