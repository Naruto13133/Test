package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atm.config.DBConfig;

public class TwoStepAuthDao {

	static DBConfig dbConfig = new DBConfig();
	
	public String getSecureHashUsingEmailnPohne(String email, String phone ) throws SQLException {
		String secure_hash=""; 
		Connection con = dbConfig.GetMysqlCon();
		try {
			String sql = "select secure_hash from ayurveda.patient where email = ? and phone_number = ? ";
		
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, phone);
		
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				secure_hash = rs.getString("secure_hash");
				}
			}
		catch (SQLException | NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			con.close();
		}
		return secure_hash;
	}

	public Boolean storeSecureHashUsingEmailnPhone(String email, String phone, String secretKey ) throws SQLException {
		
		Connection con = dbConfig.GetMysqlCon();
		String sql = "update secure_hash = ? from ayurveda.patient where  phone_number = ? and email = ? ";
		boolean result = true;
		try {
			PreparedStatement S = con.prepareStatement(sql);
				S.setString(1,secretKey);
				S.setString(2, email);
				S.setString(3, phone);
		}
		catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			con.rollback();
			e.printStackTrace();
			result = false;
		}finally {
			
			con.close();
		}
		return result;
		
	}
	public Boolean updateAuthenticationStatus(String email, String phone) throws SQLException {
		Connection con = dbConfig.GetMysqlCon();
		String sql = "UPDATE `ayurveda`.`patient` SET `is_authenticated` = 'Y' where  email = ? and  phone_number = ?  ";
		boolean result = false;
		try {
			con.setAutoCommit(false);
			PreparedStatement S = con.prepareStatement(sql);
				S.setString(1, email);
				S.setString(2, phone);
			int rs = S.executeUpdate();
			if(rs == 1) {
				result =true ;
			}
		}
		catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			con.rollback();
			e.printStackTrace();
			result = false;
		}finally {
			con.commit();
			con.close();
		}
		return result;
	}
	
	
}
