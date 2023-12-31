<!DOCTYPE html>
<%@page import="com.atm.pojo.AnswersPojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.atm.pojo.DoshaSurveyQuestionPojo"%>
<%@page import="com.atm.dao.DoshaDao"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible"
		content="IE=edge">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0">
	<title>
		Build a Survey Form using HTML and CSS
	</title>

	<style>

		/* Styling the Body element i.e. Color, 
		Font, Alignment */ 
		body {
			background-color: #05c46b;
			font-family: Verdana;
			text-align: left;
		}

		/* Styling the Form (Color, Padding, Shadow) */
		form {
			background-color: #fff;
			max-width: 500px;
			margin: 50px auto;
			padding: 30px 20px;
			box-shadow: 2px 5px 10px rgba(0, 0, 0, 0.5);
		}

		/* Styling form-control Class */
		.form-control {
			text-align: left;
			margin-bottom: 25px;
		}

		/* Styling form-control Label */ 
		.form-control label {
			display: block;
			margin-bottom: 10px;
		}

		/* Styling form-control input, 
		select, textarea */
		.form-control input,
		.form-control select,
		.form-control textarea {
			border: 1px solid #777;
			border-radius: 2px;
			font-family: inherit;
			padding: 10px;
			display: block;
			width: 95%;
		}

		/* Styling form-control Radio 
		button and Checkbox */
		.form-control input[type="radio"],
		.form-control input[type="checkbox"] {
			display: inline-block;
			width: auto;
		}

		/* Styling Button */
		button {
			background-color: #05c46b;
			border: 1px solid #777;
			border-radius: 2px;
			font-family: inherit;
			font-size: 21px;
			display: block;
			width: 100%;
			margin-top: 50px;
			margin-bottom: 20px;
		}
	</style>
</head>
	
<body>
	<h1>PLease Fill The Form</h1>
	<form action="userData" method="post">
<%
	DoshaDao dd = new DoshaDao();
	ArrayList<DoshaSurveyQuestionPojo> dss = dd.getAllQuestion();
	for(DoshaSurveyQuestionPojo dsp : dss){
		ArrayList<AnswersPojo> anss = dsp.getOption();
		%>
		<h3>question: <%=dsp.getQuestion() %></h3>
		<%
		for(AnswersPojo a : anss){
			if(a.getQuestionId()== dsp.getQuestionid()){
	%>
		<input type="checkbox" id="<%=dsp.getQuestionid() %>" name= "<%=dsp.getQuestionid()%>" value="<%=a.getId()%>" >
		<label for="<%=dsp.getQuestionid()%> " > <%=a.getAnswerId() %></label><br>
	
	<%
				}
			} 
	}
		%>
		<input type="submit" value="submit">
		</form>
</body>
</html>
