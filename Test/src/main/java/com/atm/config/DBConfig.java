package com.atm.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	
	/**
	 * @author atulm
	 * @return Connection (java.sql.Connection)
	 * @exception 
	 * ClassNotFoundException - If sql Driver class is not fount 
	 * SQLException - if a database access error occurs or the url is null
	 * SQLTimeoutException - when the driver has determined that thetimeout value specified by the 
	 * */
	public Connection GetMysqlCon() 
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ayurveda","root","admin");
			return con;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}


}


