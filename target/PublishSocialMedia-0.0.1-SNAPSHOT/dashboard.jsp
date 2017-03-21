<%@page language="java" contentType="text/html" %>
    <%@page import="javax.servlet.http.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>dashboard</title>
		<script src="resources/js/bar.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>

	
	<style>
	#fb{
  position:absolute;
		 align:center;
		
		 font:1000px;
	}
	</style>
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

	
	<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script type="text/javascript">

window.onload = function () {
	
	var f1=Number($("#like").val());
	var f2=Number($("#comment").val());
	var f3=Number($("#share").val());
	var t1=Number($("#fav").val());
	var t2=Number($("#retweet").val());
   var q1=Number($("#tumblr1").val());
   var q2=Number($("#tumblr2").val());
	
	var chart = new CanvasJS.Chart("chartContainer", {
		theme: "theme2",//theme1
		title:{
			text: "FaceBook"              
		},
		animationEnabled: true,   // change to true
		data: [              
		{
			// Change type to "bar", "area", "spline", "pie",etc.
			type: "column",
			dataPoints: [
				{ label: "Likes",  y: f1  },
				{ label: "comments", y: f2  },
				{ label: "shares",  y: f3  }
			]
		}
		]
	});
	chart.render();
	var chart1 = new CanvasJS.Chart("chartContainer1", {
		theme: "theme2",//theme1
		title:{
			text: "Twitter"              
		},
		animationEnabled: true,   // change to true
		data: [              
		{
			// Change type to "bar", "area", "spline", "pie",etc.
			type: "column",
			dataPoints: [
				{ label: "Likes",  y:  t1 },
				{ label: "Retweet", y: t2  }
				
			]
		}
		]
	});
	chart1.render();
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		theme: "theme2",//theme1
		title:{
			text: "Tumblr"              
		},
		animationEnabled: true,   // change to true
		data: [              
		{
			// Change type to "bar", "area", "spline", "pie",etc.
			type: "column",
			dataPoints: [
				{ label: "Likes",  y:  q1 },
				{ label: "Follower", y: q2  }
				
			]
		}
		]
	});
	chart2.render();
}
</script>



<!-- <style>
#chartContainer1
{
	position:absolute;
	left:520px;
	top:10px;
}
td
{
font-size: 30px;
font:bold;

}
</style>
	 -->
	
	
	<style>
	#chartContainer1
	{
	position:absolute;
	left:500px;
	top:8px;
	
	}
	
	</style>
	
	</head>
<body>

<% HttpSession hs=request.getSession(); %>

<div id="chartContainer" style="height: 300px; width: 50%;"></div>
<div id="chartContainer1" style="height: 300px; width: 50%;"></div>
<div id="chartContainer2" style="height: 300px; width: 50%;"></div>

<table >
<tr>
<th><h4><b>Liked Users</b></h4></th>
<th ><h4><b>Commented Users</b></h4></th>
<th ><h4><b>Retweeted Users</b></h4></th>


</tr>
<tr>
<td id="l1"></td><td id="y1"></td><td id="b1" ></td>
</tr>
<tr>
<td id="l2" ></td><td id="y2"></td><td id="b2" ></td>
</tr>
<tr><td id="l3" ></td><td id="y3"></td><td id="b3" ></td>
</tr>
</table>


<input type="hidden" id="like" value="<%=hs.getAttribute("likes")%>">
<input type="hidden" id="comment" value="<%=hs.getAttribute("comments")%>">
<input type="hidden" id="share" value="<%=hs.getAttribute("shares")%>">

<input type="hidden" id="fav" value="<%=hs.getAttribute("tlike")%>">
<input type="hidden" id="retweet" value="<%=hs.getAttribute("tshare")%>">

	
	
<input type="hidden" id="s1" value="<%=hs.getAttribute("lname0")%>">
<input type="hidden" id="s2" value="<%=hs.getAttribute("lname1")%>">
<input type="hidden" id="s3" value="<%=hs.getAttribute("lname2")%>">

	
	
<input type="hidden" id="t1" value="<%=hs.getAttribute("tw0")%>">
<input type="hidden" id="t2" value="<%=hs.getAttribute("tw1")%>">
<input type="hidden" id="t3" value="<%=hs.getAttribute("tw2")%>">



<%-- <input type="hidden" id="re1" value="<%=hs.getAttribute("tweet0")%>">
<input type="hidden" id="re2" value="<%=hs.getAttribute("tweet1")%>">
<input type="hidden" id="re3" value="<%=hs.getAttribute("tweet2")%>">
 --%>
	
	
<input type="hidden" id="c1" value="<%=hs.getAttribute("cname0")%>">
<input type="hidden" id="c2" value="<%=hs.getAttribute("cname1")%>">
<input type="hidden" id="c3" value="<%=hs.getAttribute("cname2")%>">
	
	<!-- tumblr likes AND followers -->
	<input type="hidden" id="tumblr1" value="<%=hs.getAttribute("tumlike")%>">
<input type="hidden" id="tumblr2" value="<%=hs.getAttribute("tumfollower")%>">
	
	
	<script type="text/javascript">
	$(document).ready(function(){
var a0=$("#t1").val();
var a1=$("#t2").val();
var a2=$("#t3").val();
if(a0!="null")
	{
	$( "#b1" ).html(a0);
	}
 if(a1!="null")
{
$( "#b2" ).html(a1);
}
 if(a2!="null")
{
$( "#b3" ).html(a2);
}
if(a3!="null")
{
$( "#b4" ).html(a3);
}
	});

	
	</script>
	
	
	
	
	
	
	
	
	
	<script type="text/javascript">
	$(document).ready(function(){
var r0=$("#s1").val();
var r1=$("#s2").val();
var r2=$("#s3").val();
if(r0!="null")
	{
	$( "#l1" ).html(r0);
	}
 if(r1!="null")
{
$( "#l2" ).html(r1);
}
 if(r2!="null")
{
$( "#l3" ).html(r2);
}
if(r3!="null")
{
$( "#l4" ).html(r3);
}
	});

	
	</script>
	
	
	
	
		
	<script type="text/javascript">
	$(document).ready(function(){
var y00=$("#c1").val();
var y11=$("#c2").val();
var y22=$("#c3").val();
if(y00!="null")
	{
	$( "#y1" ).html(y00);
	}
 if(y11!="null")
{

$( "#y2" ).html(y11);
}
 if(y22!="null")
{
$( "#y3" ).html(y22);
}

	});

	
	</script>
	
	
	<!-- 
		<script type="text/javascript">
	$(document).ready(function(){
var m1=$("#re1").val();
alert(m1);
var m2=$("#re2").val();
var m3=$("#re3").val();
if(m1!="null")
	{

	$( "#q1" ).html(m1);
	}
 if(m2!="null")
{

$( "#q2" ).html(m2);
}
 if(m3!="null")
{
$( "#q3" ).html(m3);
}

	});

	
	</script>
	
	 -->
	
</body>

	<div id="fb" style="align:center;color:blue;">FaceBook Posted Details</div>



</html>