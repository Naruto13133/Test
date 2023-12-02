package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.atm.service.*;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import com.atm.config.DBConfig;

public class TwoStepAuthDao {

	DBConfig dbConfig = new DBConfig();
	
	
	
	public String getSecureHashUsingEmailnPohne(String email, String phone) {
		String secure_hash=""; 
		Connection con = dbConfig.GetMysqlCon();
		String sql = "select secure_hash from ayurveda.patient where 1=1  ";
		try {
			PreparedStatement S = con.prepareStatement(sql);
			if ((email == null || email.isEmpty())) {
				S.setString(1, sql);
				sql = sql + "and email = ? ";
			}if ((phone == null || phone.isEmpty())) {
				phone = "and phone = ? ";
				S.setString(2, sql);
			}
		ResultSet rs= S.executeQuery();
		secure_hash = rs.getString("secure_hash");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return secure_hash;
	}
	
	public Boolean storeSecureHashUsingEmailnPhone(String email, String phone, String secretKey  ) {
		
		Connection con = dbConfig.GetMysqlCon();
		String sql = "update secure_hash = '"+secretKey+"' from ayurveda.patient where 1=1  ";
		try {
			PreparedStatement S = con.prepareStatement(sql);
			if ((email == null || email.isEmpty())) {
				sql = sql + "and email = ? ";
				S.setString(1, sql);
			}if ((phone == null || phone.isEmpty())) {
				phone = "and phone = ? ";
				S.setString(2, sql);
			}
		int rs= S.executeUpdate();
		if ( rs >= 1 ) {
			return true;
		}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
}
