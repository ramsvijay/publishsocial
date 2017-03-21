<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
<form class="upload-box">
    <input type="file" id="file" name="file1" />
    <span id="upload-error" class="error" />
    <input type="submit" id="upload-button" value="upload" />
</form>
<script>
$(document).on("#upload-button", "click", function() {
    $.ajax({
        type: "POST",
        url: "/Upload",
        async: true,
        data: $(".upload-box").serialize(),
        contentType: "multipart/form-data",
        processData: false,
        success: function(msg) {
            alert("File has been uploaded successfully");
        },
        error:function(msg) {
            $("#upload-error").html("Couldn't upload file");
        }
    });
});
</script>
</body>
</html>