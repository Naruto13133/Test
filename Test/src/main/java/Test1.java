import java.beans.Statement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import com.atm.config.DBConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import de.taimos.totp.TOTP;


public class Test1 {
	
	
	final static String SECRETE_KEY="4RAUYVPUWRVKLSCTG2CSPHSJGHJ43TU3";
	
	
	/**
	 * This function is use to retrive data from DB using JDBC which is configure in 
	 * com.atm.config.DBConfig class
	 * 
	 * @param con - is a Connection object initiated from dataSource Class
	 * @param umail - it is use to as parameter in select query to fetch email and name
	 * @param Uphone - it is use to as parameter in select query to fetch email and name
	 * 
	 * @return HashMap<String, String> mailandFullName where,
	 *  keys are "mail" and "fullName".
	 * */
	public HashMap<String, String>  getPatientMailAdressAndName(Connection con,String umail,String Uphone) throws SQLException {
		java.sql.Statement statement = con.createStatement();
		
		HashMap<String, String> mailandFullName =new HashMap<String, String>();
		String query="Select email,CONCAT(IFNULL(first_name, ''), ' ', IFNULL(last_name, '')) AS full_name "
				+ "from ayurveda.patient  "
				+ "where 1=1";
		if(! umail.isBlank()) {
			query = query + " and email= '" +umail +"' ";
		}
		else if(! Uphone.isBlank()) {
			query = query + " and phone_number='" +Uphone +"' ";
		}
		System.out.println(query);
		
		try {
			ResultSet rs=statement.executeQuery(query);
			if (rs.next()) {
			    mailandFullName.putIfAbsent("mail", rs.getString("email"));
			    mailandFullName.putIfAbsent("fullName", rs.getString("full_name"));
			    // Process or use the retrieved string
			    System.out.println("Retrieved String: " + mailandFullName.get("mail"));
			    System.out.println("Retrieved String: " + mailandFullName.get("fullName"));
			} else {
			    System.out.println("No data found.");
			}
			System.out.println(mailandFullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			statement.close();
			con.close();
		}
		return mailandFullName;
	}
	
	public static String generateSecretKey() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[20];
	    random.nextBytes(bytes);
	    Base32 base32 = new Base32();
	    return base32.encodeToString(bytes);
	}
	
	public static String getTOTPCode(String SECRETE_KEY) {
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(SECRETE_KEY);
	    String hexKey = Hex.encodeHexString(bytes);
	    return TOTP.getOTP(hexKey);
	}
	
	public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
		try {
	        if (issuer == null) {
	            issuer = "";
	        }
	        if (account == null) {
	            account = "";
	        }
	        if (secretKey == null) {
	            secretKey = "";
	        }
	        
	        return "otpauth://totp/"
	                + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
	                + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
	                + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new IllegalStateException(e);
	    }
	}

	public static void createQRCode(String mail,String barCodeData, String filePath, int height, int width)
	        throws WriterException, IOException {
	    BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
	            width, height);
	    try (FileOutputStream out = new FileOutputStream("C:\\Users\\atulm\\Desktop\\Depe\\Test\\" + mail)) {
	        MatrixToImageWriter.writeToStream(matrix, "png", out);
	    }
	}
	
	
//	public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
//	    try {
//	    	System.out.println("otpauth://totp/"
//	                + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
//	                + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
//	                + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20"));
//	        return "otpauth://totp/"
//	                + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
//	                + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
//	                + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
//	    } catch (UnsupportedEncodingException e) {
//	        throw new IllegalStateException(e);
//	    }
//	}

	public static void main(String[] args) throws SQLException, WriterException, IOException {
		// TODO Auto-generated method stub
		Test1 g=new Test1();
		DBConfig dbconfig=new DBConfig();
		Connection con=dbconfig.GetMysqlCon();
		Map<String, String> name=g.getPatientMailAdressAndName(con,"jforxcea@gmail.com","atul MOurya");
		String autData=g.getGoogleAuthenticatorBarCode(SECRETE_KEY, name.get("mail"), name.get("fullNamej"));
		
		System.out.println(g.generateSecretKey());
		System.out.println(g.getTOTPCode(Test1.SECRETE_KEY));
		System.out.println(name);
		g.getTOTPCode(SECRETE_KEY);
		g.createQRCode(name.get("fullName"),autData,"",500,500);
		System.out.println(g.getGoogleAuthenticatorBarCode(SECRETE_KEY, name.get("mail"), name.get("fullName")));
	}

}
