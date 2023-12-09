<%@page import="java.util.Enumeration"%>
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
<link rel="stylesheet" type="text/css" href="Resorces/CSS/Athenticate.css">
</head>
<body>
	
	<%
	//Enumeration<String> a = request.getParameterNames();
	Enumeration<String> sessionParameters = session.getAttributeNames();
	boolean originFromLogin = false;
	String base64Image = "";
	for(int i=0; i==0;i=i+0) {
		String str = sessionParameters.nextElement();
		boolean b= str.equals("origin");
		System.out.println(str);
		if(b) {
			System.out.println("origin present");
			originFromLogin = true;
			break;
		}else if(! sessionParameters.hasMoreElements()) {
			break;
			
		}
	}
	
	 base64Image = (String) request.getAttribute("base64Image");
	if(! originFromLogin){
	%>
	<img id="qrCodeImage" alt="Qrcode"><br><br>
	<form action="checkauthentication" method="post">
	<h4>
	Enter the OTP
	<h3></h3>
	</h4><br><br>
	<input type="text" id= "otp" name="otp">
	
	<input type="submit" id="submit">
	
	
	</form>
	
	<h1 id="authenticate" >Please Authenticate the By scanning QR code!</h1>
	
	<%
}else{
	%>
	<form action="checkauthentication" method="post">
	<h4>
	Enter the OTP
	</h4><br><br>
	<input type="text" id= "otp" name="otp">
	
	<input type="submit" id="submit">
	
	
	</form>
	
	<h1 id="authenticate" >Please Enter the OPT from Authentication App !</h1>
	
	
	<%
	}
	%>
</body>
<script type="text/javascript">

	window.onload = function() {
		if("" === "<%=base64Image%>"){
			
		}else{
			var qrCodeImage = document.getElementById('qrCodeImage');
			var baseStr64="<%=base64Image%>";
			qrCodeImage.setAttribute('src', "data:image/jpg;base64," + baseStr64);
		}
		
	};
	
	

</script>

</html>