<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap Login Form Template</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="resources/assets1/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/assets1/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="resources/assets1/css/form-elements.css">
        <link rel="stylesheet" href="resources/assets1/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="resources/assets1/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/assets1/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/assets1/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/assets1/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="resources/assets1/ico/apple-touch-icon-57-precomposed.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
var search = {};

function submit() {
	
		alert("came");
    	search["name"]=$('#name').val();
		search["email"] = $('#email').val();
		search["password"] = $('#password').val();
		search["mobile"] = $('#mobile').val();
		alert("");
		$.ajax({
			type : "POST",
			contentType : "application/json",
	
			url : "http://localhost:8080/PublishSocialMedia/register",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			complete: function(data) {
				if(data)
					window.location="login.jsp";
				else
					alert("Faild");
		
		}
		});


    }





</script>


    </head>

    <body background="resources/assets/img/backgrounds/1.jpg">

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>Publish</strong> Social Media</h1>
                            <div class="description">
                            	
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Register to our site</h3>
                            		<p>Enter your username and password to log on:</p>
                        		</div>
                        		<div class="form-top-right">
                        		                        			<a href="login.jsp"><h4>Login</h4></a>
	
                        		</div>
                            </div>
                            <div class="form-bottom">
                         
			                    <div  class="login-form">
								<div class="form-group">
			                    		<label class="sr-only" for="form-username">Name</label>
			                        	<input type="text" name="form-username" placeholder="Name..." class="form-username form-control" id="name">
			                        </div>
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Email</label>
			                        	<input type="text" name="form-username" placeholder="Email..." class="form-username form-control" id="email">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Password</label>
			                        	<input type="password" name="form-password" placeholder="Password..." class="form-password form-control" id="password">
			                        </div>
									<div class="form-group">
			                        	<label class="sr-only" for="form-password">Confirm-Password</label>
			                        	<input type="password" name="form-password" placeholder="Confirm-Password..." class="form-password form-control" id="form-password">
			                        </div>
									<div class="form-group">
			                        	<label class="sr-only" for="form-password">Mobile No</label>
			                        	<input type="text" name="form-password" placeholder="Mobile No..." class="form-password form-control" id="mobile">
			                        </div>
			                        <input  type="submit" class="btn" id="submit" onclick="submit()" value="Sign up!">
			                    </div>
			                  
		                    </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	<h3>...or login with:</h3>
                        	<div class="social-login-buttons">
	                        	<a class="btn btn-link-1 btn-link-1-facebook" href="#">
	                        		<i class="fa fa-facebook"></i> Facebook
	                        	</a>
	                        	<a class="btn btn-link-1 btn-link-1-twitter" href="#">
	                        		<i class="fa fa-twitter"></i> Twitter
	                        	</a>
	                        	
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="resources/assets1/js/jquery-1.11.1.min.js"></script>
        <script src="resources/assets1/bootstrap/js/bootstrap.min.js"></script>
        <script src="resources/assets1/js/jquery.backstretch.min.js"></script>
        <script src="resources/assets1/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>