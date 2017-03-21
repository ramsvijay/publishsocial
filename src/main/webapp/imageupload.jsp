<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- <script>
	var search={};
	$(document).ready(function(){
	    $("#submit").click(function(){
	    	var fileInput = document.getElementById('image');
		search["image"] = fileInput.val();
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "http://localhost:8080/PublishSocialMedia/faceimage",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100000,
		success: function(data) {
			
			if(data)
				{
				alert(data);
				}
				
	}
	
	});
			 
	    });
	});
	
	</script> -->	
		<script type="text/javascript">
$(document).ready(function() {
$("#submit").click(function() {
  var fileInput = document.getElementById('image');
  var file = fileInput.files[0];
  var formData = new FormData();
  formData.append('file', file);
  // console.log(file);
alert(file);
alert(formData);
 

      $.ajax({
        url: "http://localhost:8080/PublishSocialMedia/faceimage",
        type: "POST",
        data: "file="+file,
        cache: false,

        success: function(reponse) {
          if(reponse) {
            alert(reponse);

            // console.log(reponse);
            // $('#devis').trigger("reset");
          } else {
            alert('Erreur');
          }
        }
      });
 }); });
</script> 
</head>
<body>
<form  method="Post" enctype="multipart/form-data">
<input type="file" id="image" name="image" accept="image/*"/>
<input type="submit" id="submit" />
</form>
</body>
</html>