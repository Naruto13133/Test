package com.atm;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddServlet extends HttpServlet
{

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException 
	{
		String UserId= (String)req.getParameter("a");
		String Pass= (String)req.getParameter("b");
		
		//res.sendRedirect("sq?a="+i+"&b="+j); 
		HttpSession hs= req.getSession();
		
		if(UserId.equals("Test") && Pass.equals("Test")) {

		hs.setAttribute("name", UserId);
		hs.setAttribute("pass", Pass);
		
		res.sendRedirect("sq?a="+UserId+"&b="+UserId); 
		
		}
		
		else {
			
		}
	}
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException 
	{
		int i= Integer.parseInt(req.getParameter("a"));
		int j= Integer.parseInt(req.getParameter("b"));
		i=i+j;
		System.out.println(i+j);
		
		PrintWriter out=res.getWriter();
		out.println("<h1>Total is "+ i + "<h1>");
		
	}
	
}
