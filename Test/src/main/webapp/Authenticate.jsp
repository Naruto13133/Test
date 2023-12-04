<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URL"%>
<%@page import="com.atm.service.TwoStepVerificationService"%>
<%@page import="com.atm.pojo.Patient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
	Patient patient = (Patient) request.getAttribute("patient");
	String PATH = "file:///C:/Users/atulm/Desktop/Depe/Test/"+patient.getFirstName()+patient.getLastName()+patient.getPhoneNumber()+"";
	//PATH = PATH+patient.getFirstName()+patient.getLastName()+patient.getPhoneNumber()+".png";
	 
	 
	String key = patient.getSecretKey();
	String OTP = TwoStepVerificationService.getTOTPCode(key);
	%>
	<img src="<%=PATH%>" alt="Qrcode"><br><br>
	<form>
	<h4>
	Enter the OTP
	</h4><br><br>
	<input type="text" id= "opt" name="otp">
	
	<input type="submit" id="submit">
	
	</form>
	
</body>
</html>