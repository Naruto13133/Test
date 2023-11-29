package com.atm;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetSq extends HttpServlet
{

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException 
	{
		
		
	}
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException 
	{
		String i= (String)req.getParameter("a");
		String j= (String)req.getParameter("b");
		
		System.out.println(i+j);
		
		PrintWriter out=res.getWriter();
		out.println("<h1>SQ is "+ i + "<h1>");
		
	}
	
}
