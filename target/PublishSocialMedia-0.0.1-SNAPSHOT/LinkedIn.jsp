<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
function login()
alert();
{
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/PublishSocialMedia/LinkedIn",
			timeout : 100000,
			success: function(data) {
				if(data=="success")
					{
					window.location="publish1.jsp";
					}
				}
		});

		$.ajax({
			type : "POST",
			url : "http://localhost:8080/PublishSocialMedia/LinkedIn1",
			timeout : 100000,
			success: function(data) {
				if(data=="success")
					{
					window.location="publish1.jsp";
					}
				}
		});
}
</script>
</head>
<body>
<input type="submit" value="LinkedIn" onclick="login()">
</body>
</html>