<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Social media</title>
		<meta name="description" content="Multi-Element Selection: Click and hold to select multiple items" />
		<meta name="keywords" content="checkbox, selection, javascript, hover, click and hold, drag, multi-select" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="resources/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="resources/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="resources/css/component.css" />
		<script src="resources/js/modernizr.custom.js"></script>
<style>
		
		#ft{
        border-radius: 10px;
		position:absolute;
		align:center;
		left:400px;
		width:200px;
		height:100px;
		}
		#submit{
		position:absolute;
		align:center;
		left:620px;
		top:150px;
		}
		#submit {
 background-color: #E5E8E8  ;
    border: none;
    color: blackw;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
		</style>
		

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#submit").click(function(){
	 var f1 = $('.f:checked').val();
	 var f2= $('.t:checked').val();
	 alert(f1);
	 alert(f2);
	 var status=$('#ft').val();
	 if((f1=="facebook")&&(f2=="twitter"))
		 {
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/PublishSocialMedia/facebookTweet/"+status,
			timeout : 100000,
			success: function(data) {
				if(data=="success")
					{
					window.location="view.jsp";
					}
					else
					alert("Faild");

		
		}
		});
		 }
		else  if((f1=="facebook")&&(f2!="twitter"))
			{
			$.ajax({
				type : "POST",
				url : "http://localhost:8080/PublishSocialMedia/face/"+status,
				timeout : 100000,
				success: function(data) {
					if(data=='success')
						{
						window.location="view.jsp";
						}
						else
						alert("Faild");

			
			}
			
			});
			}
			
			else  if((f2=="twitter")&&(f1!="facebook"))
			{
			$.ajax({
				type : "POST",
				url : "http://localhost:8080/PublishSocialMedia/tweet/"+status,
				timeout : 100000,
				success: function(data) {
					if(data=="success")
						{
						window.location="view.jsp";
						}
						else
						alert("Faild");

			
			}
			
			
			});
			
			}
			else{
				alert("selected atleast one social media");
				
			}
		 
				
	
});
    });
	</script> 
</head>
<body background="resources/img/white-background-5.jpg">

		<div class="container demo-1">
			<!-- Top Navigation -->
			<div class="codrops-top clearfix">
<h1 align="center">Publish Document</h1>

<br>
     <input type="text" id="ft" placeholder="Compose Nessage" name="document">
      <input type="submit" value="Publish" name="submit" id="submit">
			</div>
			

			<br>
			<br>
			<section>
				<div class="me-select">
					<ul id="me-select-list">
						<li><input id="cb1" class="f" name="cb1" type="checkbox" value="facebook"><label for="cb1" ><span><b>Facebook</b></span></label></li>
						<li><input id="cb2" class="t" name="cb2" type="checkbox" value="twitter"><label for="cb2" ><span><b>Twitter</b></span></label></li>
						<li><input id="cb3" class="y" name="cb3" type="checkbox" value="youtube"><label for="cb3"><span><b>YouTube</b></span></label></li>
					</ul>
				</div>
				
		
			</section>
		</div><!-- /container -->
		<script src="resources/js/magicselection.js"></script>
		<script>
			(function() {
				var selList = document.getElementById( 'me-select-list' ),
					items = selList.querySelectorAll( 'li' );
				
				// fill the initial checked elements (mozilla)
				[].slice.call( items ).forEach( function( el ) {
					el.parentNode.className = el.checked ? 'selected' : '';
				} );

				function checkUncheck( el ) {
					el.parentNode.className = el.checked ? '' : 'selected';
					el.checked = !el.checked;
				}

				new magicSelection( selList.querySelectorAll( 'li > input[type="checkbox"]' ), {
					onSelection : function( el ) { checkUncheck( el ); },
					onClick : function( el ) {
						el.parentNode.className = el.checked ? 'selected' : '';
					}
				} );

			} )();
		</script>
	
</body>
</html>
