package com.atm.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import de.taimos.totp.TOTP;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class TwoStepVerificationService {

	
		
		
		final static String SECRETE_KEY="4RAUYVPUWRVKLSCTG2CSPHSJGHJ43TU3";
		

		
		
		/**
		 * This method is use to generate A key which will be used as key in TOTP.gerOPT()
		 * Each User will have different key to access.
		 * @return String encoded random hex value with base 32;
		 * */
		public static String generateSecretKey() {
		    SecureRandom random = new SecureRandom();
		    byte[] bytes = new byte[20];
		    random.nextBytes(bytes);
		    Base32 base32 = new Base32();
		    return base32.encodeToString(bytes);
		}
		
		/**
		 * @param SECRETE_KEY - this is use to generate the ramdom key OTP for authentication.
		 * It give the encripted which is encripted by key(Random hex code) and  OTP code.
		 * */
		public static String getTOTPCode(String SECRETE_KEY) {
		    Base32 base32 = new Base32();
		    byte[] bytes = base32.decode(SECRETE_KEY);
		    String hexKey = Hex.encodeHexString(bytes);
		    return TOTP.getOTP(hexKey);
		}

		/**
		 * This method use to get the get Google Authenticator Url set in QRCode  
		 */
		public static String getGoogleAuthenticatorBarCode(String secretKey, String email, String fullNameOfPatient) {
			try {
		        String AuthenticatorBarCodeURL = "otpauth://totp/"
	            + URLEncoder.encode(fullNameOfPatient + ":" + email, "UTF-8").replace("+", "%20")
	            + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
	            + "&issuer=" + URLEncoder.encode(fullNameOfPatient, "UTF-8").replace("+", "%20");
		        return AuthenticatorBarCodeURL;
		    } catch (UnsupportedEncodingException e) {
		        throw new IllegalStateException(e);
		    }catch (NullPointerException e) {
				System.out.println("Please Provide correct email or phone number to get verified.");
				e.printStackTrace();
				throw new NullPointerException ();
			}
		}
		
		/**
		 * use to create a barcode png format image
		 * */
		public static void createQRCode(String name, String phone,String barCodeData)
		        throws WriterException, IOException {
		    BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
		            100, 100);
		    try (FileOutputStream out = new FileOutputStream("C:\\Users\\atulm\\Desktop\\Depe\\Test\\" + name+phone+".png")) {
		        MatrixToImageWriter.writeToStream(matrix, "png", out);
		    }catch (Exception e) {
				e.printStackTrace();
			}
		}
	

		public static ByteArrayOutputStream imageBydeData(String URLString) throws ServletException, IOException {
	        
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        try {
	            BitMatrix bitMatrix = new MultiFormatWriter().encode(URLString, BarcodeFormat.QR_CODE, 300, 300);

	            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
	            
	            
	            // Set response content type to image
//	            response.setContentType("image/png");
//	            // Write the image data to the client
//	            response.getOutputStream().write(out.toByteArray());
//	            response.getOutputStream().flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        return out;
	    }

		
public static String base64StringFromByte(ByteArrayOutputStream byteData) throws ServletException, IOException {
	        
	        String result="";
	        try {
	            byte[] encode = Base64.getEncoder().encode(byteData.toByteArray());
	            result = new String(encode);
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        return result;
			
	    }


}

