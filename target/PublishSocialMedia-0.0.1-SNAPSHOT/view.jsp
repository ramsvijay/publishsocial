<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
 -->
 
 

<style>
body {
	background: #fafafa url(http://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
	background: #f5f5f5;
	border-collapse: separate;
	box-shadow: inset 0 1px 0 #fff;
	font-size: 12px;
	line-height: 24px;
	margin: 30px auto;
	text-align: left;
	width: 800px;
}	

th {
	background: url(http://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
	border-left: 1px solid #555;
	border-right: 1px solid #777;
	border-top: 1px solid #555;
	border-bottom: 1px solid #333;
	box-shadow: inset 0 1px 0 #999;
	color: #fff;
  font-weight: bold;
	padding: 10px 15px;
	position: relative;
	text-shadow: 0 1px 0 #000;	
}

th:after {
	background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
	content: '';
	display: block;
	height: 25%;
	left: 0;
	margin: 1px 0 0 0;
	position: absolute;
	top: 25%;
	width: 100%;
}

th:first-child {
	border-left: 1px solid #777;	
	box-shadow: inset 1px 1px 0 #999;
}

th:last-child {
	box-shadow: inset -1px 1px 0 #999;
}

td {
	border-right: 1px solid #fff;
	border-left: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #e8e8e8;
	padding: 10px 15px;
	position: relative;
	transition: all 300ms;
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
	border-right: 1px solid #e8e8e8;
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: url(http://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:nth-child(odd) td {
	background: #f1f1f1 url(http://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:last-of-type td {
	box-shadow: inset 0 -1px 0 #fff; 
}

tr:last-of-type td:first-child {
	box-shadow: inset 1px -1px 0 #fff;
}	

tr:last-of-type td:last-child {
	box-shadow: inset -1px -1px 0 #fff;
}	

tbody:hover td {
	color: transparent;
	text-shadow: 0 0 3px #aaa;
}

tbody:hover tr:hover td {
	color: #444;
	text-shadow: 0 1px 0 #fff;
}



</style>


<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
$(document).ready(function(){
    $("#submit").click(function(){
	
	 var postid=$('#postid').val();
	alert(postid);
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/PublishSocialMedia/details/"+postid,
			timeout : 100000,
			success: function(data) {
				if(data=="done")
					{
					window.location="dashboard.jsp";
					}
					else
					alert("Invalid");

		
		}
		});
		 });
});



</script>


</head>


<body bgcolor="black">
<!-- <div class="w3-container w3-teal"><h1 align="center">Posted Documents Details</h1></div> 
 -->
  <h1 align="center">Posted Documents Details</h1>
<a href="login.jsp">Logout</a>
<br>
<br>
<br>

<table align="center">
<!-- Text input-->
  <tr><td><label class="col-md-4 control-label" for="name">Id</label></td>
 <td> <input type="text"  name="postid" id="postid"></td>
 <td> <input id="submit" type="submit"  class="form-control input-md" ></td>
    
 </tr>

</table>



<table id="table1">

	<thead>
	<tr> 
    <th>Id</th>
    <th>FaceBookId</th>
    <th>TwitterId</th>
    <th>Tumblrid</th>
    <th>Status</th>
    <th>Username</th>
    </tr> 

			</thead>
		</table>
		
		<script>
$(document).ready(function() {

	$.ajax({
		type : "GET",
		contentType : "application/xml",
		url : "http://localhost:8080/PublishSocialMedia/view/",
		dataType : 'json',
		timeout : 100000,
		
		success : function(response) {
			alert(JSON.stringify(response));
			var obj = $.parseJSON(JSON
							.stringify(response));
			for ( var key in obj) {
				var obj1 = obj[key];

				for ( var key1 in obj1) {
					var obj2 = obj1[key1];
					 var table = document.getElementById("table1");
						    var tr = table.insertRow();

					for ( var key2 in obj2) {	
						var td = tr.insertCell();
					    td.innerHTML= JSON.stringify(obj2[key2]).replace('"',' ').replace('"',' ');

					}   
				}
			}
		}
	});


		});
</script>
	
</body>
</html>