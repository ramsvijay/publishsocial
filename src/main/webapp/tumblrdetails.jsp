
<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
	
	<script>
	window.onload = function () {
 var follow;
 var likes;
	$.ajax({
		type : "GET",
		contentType : "application/xml",
		url : "http://localhost:8080/PublishSocialMedia/tumblrdetails/",
		dataType : 'json',
		timeout : 100000,
		success : function(response) {
			alert(JSON.stringify(response));
			var ob=response;
			
			alert("Total Likes"+ob.response.user.likes);
			alert("Followers "+ob.response.user.following);
			follow=new Number(ob.response.user.following);
			likes=new Number(ob.response.user.likes);
		
			
			
			}
	
	
	
		
	});



	alert(follow);

	var chart = new CanvasJS.Chart("chartContainer", {
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
				{ label: "Likes",  y: follow  },
				{ label: "comments", y: likes },
				
			]
		}
		]
	});
	chart.render();

}
	
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

<!-- <table align="center">
Text input
  <tr><td><label class="col-md-4 control-label" for="name">Id</label></td>
 <td> <input type="text"  name="postid" id="postid"></td>
 <td> <input id="submit" type="submit"  class="form-control input-md" ></td>
    
 </tr>

</table> -->



<table id="table1">

			<thead>
				<tr> 
			
    <th>Likes</th>
    <th>FaceBookId</th>
   
 
  </tr> 

			</thead>
		</table>
		
	
	<div id="chartContainer" style="height: 300px; width: 50%;"></div>
		
	
</body>
</html>