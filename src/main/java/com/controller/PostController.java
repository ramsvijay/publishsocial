package com.controller;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;






import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.Images;
import com.bean.Status1;
import com.bean.ViewList;
import com.bean.register;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;

import com.service.PostInf;
import com.temboo.Library.Tumblr.Blog.CreateTextPost;
import com.temboo.Library.Tumblr.Blog.CreateTextPost.CreateTextPostInputSet;
import com.temboo.Library.Tumblr.Blog.CreateTextPost.CreateTextPostResultSet;
import com.temboo.Library.Tumblr.Blog.FinalizeOAuth;
import com.temboo.Library.Tumblr.Blog.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Tumblr.Blog.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Tumblr.Blog.GetUserInformation;
import com.temboo.Library.Tumblr.Blog.GetUserInformation.GetUserInformationInputSet;
import com.temboo.Library.Tumblr.Blog.GetUserInformation.GetUserInformationResultSet;
import com.temboo.Library.Tumblr.Blog.InitializeOAuth;
import com.temboo.Library.Tumblr.Blog.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Tumblr.Blog.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.Library.Tumblr.Blog.RetrievePublishedPosts;
import com.temboo.Library.Tumblr.Blog.RetrievePublishedPosts.RetrievePublishedPostsInputSet;
import com.temboo.Library.Tumblr.Blog.RetrievePublishedPosts.RetrievePublishedPostsResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import io.swagger.annotations.ApiOperation;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@RestController

public class PostController {
	@Autowired
	PostInf service;
    public String accessToken="";
	StringBuffer sb=new StringBuffer(accessToken);
	
    
    public  final String FB_APP_ID = "376266176099736";
	public  final String FB_APP_SECRET = "1905295267c9cdac27a579d9d906a2d9";
	public  final String REDIRECT_URI = "http://localhost:8080/PublishSocialMedia/redirectfb";

	public  final String REDIRECT_URIs = "http://localhost:8080/PublishSocialMedia/redirectfbs";
    public String code="";
   public String  a="";
   public String  b="";
	public String y="";
   
	 public String  temboo="";
		public String temboosecret="";

   /*Registration*/
   @ApiOperation(value = "REGISTRATION",notes="function for registration",response=register.class)
   @RequestMapping(value ="/register", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
   public @ResponseBody
   register reg(@RequestBody register r) {
  
	   register rs=service.registerdetails(r);
    
      return rs;
    
   }
   
   
   
   
  



				/**Oauth Tumblr */
	     @ApiOperation(value = "Tumblr")
	    @RequestMapping(value = "/tumblr", method = RequestMethod.GET)
	    public String tumblr(HttpSession hs,HttpServletResponse response) throws FileNotFoundException {
		  
		  try
			{
				// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
			 TembooSession session = new TembooSession("ramsvijay", "myFirstApp", "r1g00lDVEe7MHbWHncw7tmwjUQx9kZCd");

				InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);

				// Get an InputSet object for the choreo
				InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();

				// Set inputs
				initializeOAuthInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
				initializeOAuthInputs.set_ForwardingURL("http://localhost:8080/PublishSocialMedia/tumblr1");
				initializeOAuthInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");

			InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
			String ots=initializeOAuthResults.get_OAuthTokenSecret();
			String aurl=initializeOAuthResults.get_AuthorizationURL();
			String call=initializeOAuthResults.get_CallbackID();
			hs.setAttribute("tots", ots);
			hs.setAttribute("tcall", call);
			hs.setAttribute("tses", session);
			response.sendRedirect(aurl);
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
	
 
		  
		
	        return null;
	    }
	    
   
   
   /**redirect oauth 
 * @throws TembooException 
 * @throws IOException */
	  @ApiOperation(value = "Tumblr1")
	    @RequestMapping(value = "/tumblr1", method = RequestMethod.GET)
	    public String tumblr1(HttpSession hs,HttpServletRequest req,HttpServletResponse res) throws TembooException, IOException {
			
		try
		{
		  String ots=(String) hs.getAttribute("tots");
		  String call= (String) hs.getAttribute("tcall");
		// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
		TembooSession session = (TembooSession)hs.getAttribute("tses");

		FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);

		// Get an InputSet object for the choreo
		FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();

		// Set inputs
		finalizeOAuthInputs.set_OAuthTokenSecret(ots);
		finalizeOAuthInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
		finalizeOAuthInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");
		finalizeOAuthInputs.set_CallbackID(call);
		
		// Execute Choreo
		FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
		temboo=finalizeOAuthResults.get_AccessToken();
		temboosecret=finalizeOAuthResults.get_AccessTokenSecret();	
	hs.setAttribute("tumsession", session);
		res.sendRedirect("tumblr.jsp");
		}catch(Exception e)
		{
			
		}
		  return null;
		 
	  }
   
   
   
	  
	  /**post tumblr 
	 * @throws TembooException */

	  @ApiOperation(value = "posttumblr")
	    @RequestMapping(value = "/tum/{status}", method = RequestMethod.POST)
	    public String postTumblr(@PathVariable String status,HttpSession hs) throws IOException, TembooException {
		  try
		  {
		  TembooSession session = (TembooSession)hs.getAttribute("tumsession");
		  GetUserInformation getUserInformationChoreo = new GetUserInformation(session);
// Get an InputSet object for the choreo
GetUserInformationInputSet getUserInformationInputs = getUserInformationChoreo.newInputSet();
// Set inputs

getUserInformationInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
getUserInformationInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");
getUserInformationInputs.set_AccessToken(temboo);
getUserInformationInputs.set_AccessTokenSecret(temboosecret);
// Execute Choreo
GetUserInformationResultSet getUserInformationResults = getUserInformationChoreo.execute(getUserInformationInputs);
System.out.println(getUserInformationResults.get_Response());
String ob=getUserInformationResults.get_Response();
System.out.println(getUserInformationResults.get_Response());
String[] res=ob.split("\"");
try
{

System.out.println(res[15]);



}catch(Exception e)
{
	System.out.println(e);
}

	CreateTextPost createTextPostChoreo = new CreateTextPost(session);

	// Get an InputSet object for the choreo
	CreateTextPostInputSet createTextPostInputs = createTextPostChoreo.newInputSet();
  
	// Set inputs
	createTextPostInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
	createTextPostInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");
	createTextPostInputs.set_AccessToken(temboo);
	createTextPostInputs.set_BaseHostname(res[15]+".tumblr.com");
	createTextPostInputs.set_AccessTokenSecret(temboosecret);
	createTextPostInputs.set_Body(status);

	// Execute Choreo
	CreateTextPostResultSet createTextPostResults = createTextPostChoreo.execute(createTextPostInputs);
	
		  }catch(Exception e)
		  {
			System.out.println(e);  
		  }
		  
		return "success"; 
	  
		  
	  }
	  
	  
	  /** three social media facebookTweetTumblr 
	 * @throws TembooException */
	  
		
	  @ApiOperation(value = "BOTH FACEBOOK AND TWITTER")
	    @RequestMapping(value = "/facebookTweetTumblr/{status}", method = RequestMethod.POST)
	    public String postFacebookTwitterTumblr(@PathVariable String status,HttpSession hs) throws TembooException {
		  
		  
		  //Facebook
		  System.out.println("done");
			@SuppressWarnings("deprecation")
			FacebookClient fbClient= new DefaultFacebookClient(accessToken);
			FacebookType response=fbClient.publish("me/feed", FacebookType.class,Parameter.with("message", status));
	      System.out.println("facebook"+response.getId());
		  
	      String postid=response.getId();
	        Status1 s=new Status1();
	        s.setFacebookid(postid);
	        s.setStatus(status);
	       
	      
	      
	      
	      
	      //Twitter
		  ConfigurationBuilder cb = new ConfigurationBuilder();
	        
	        cb.setDebugEnabled(true)
	             .setOAuthConsumerKey("nUiZZ0iBMyUrkZrZBhMPao8Ae")
	             .setOAuthConsumerSecret("9fpYueJwtdqepeVPqEJ6rAifkQOgBxwWUQO5bKN4zLbIhfPLQm")
	             .setOAuthAccessToken(a)
	             .setOAuthAccessTokenSecret(b);
	        
	  TwitterFactory tf=new TwitterFactory(cb.build());  
	  
	  twitter4j.Twitter tw=tf.getInstance();

	  try 
	  {
		Status stat= tw.updateStatus(status);
		long l=stat.getId();
		 y=stat.getUser().getName();
		String te=String.valueOf(l);
		s.setTwitterid(te);
		s.setUsername(y);
		 TembooSession session = (TembooSession)hs.getAttribute("tumsession");
		  GetUserInformation getUserInformationChoreo = new GetUserInformation(session);
//Get an InputSet object for the choreo
GetUserInformationInputSet getUserInformationInputs = getUserInformationChoreo.newInputSet();
//Set inputs

getUserInformationInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
getUserInformationInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");
getUserInformationInputs.set_AccessToken(temboo);
getUserInformationInputs.set_AccessTokenSecret(temboosecret);
//Execute Choreo
GetUserInformationResultSet getUserInformationResults = getUserInformationChoreo.execute(getUserInformationInputs);
System.out.println(getUserInformationResults.get_Response());
String ob=getUserInformationResults.get_Response();
String[] res=ob.split("\"");
System.out.println("18 s:"+res[18]);
String tum1=res[18];
String tum2=res[20];
StringBuffer sb1=new StringBuffer(tum1);
hs.setAttribute("tumlike",sb1.substring(1, sb1.length()-1));
StringBuffer sb2=new StringBuffer(tum2);
hs.setAttribute("tumfollower",sb2.substring(1, sb2.length()-1));

CreateTextPost createTextPostChoreo = new CreateTextPost(session);

// Get an InputSet object for the choreo
CreateTextPostInputSet createTextPostInputs = createTextPostChoreo.newInputSet();

// Set inputs
createTextPostInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
createTextPostInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");
createTextPostInputs.set_AccessToken(temboo);
createTextPostInputs.set_BaseHostname(res[15]+".tumblr.com");
createTextPostInputs.set_AccessTokenSecret(temboosecret);
createTextPostInputs.set_Body(status);

// Execute Choreo
CreateTextPostResultSet createTextPostResults = createTextPostChoreo.execute(createTextPostInputs);

 System.out.println("siva"+createTextPostResults.getId());
  
	String txtid=createTextPostResults.getId();
		s.setTumblrid(txtid);
		service.both(s);
	  } 
	  catch (TwitterException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 
  
		  
	  
	   
	      return "success";
 }  
	  
	  
	 /** tumblrdetails  
	 * @throws TembooException */
   
	  
	
	  @ApiOperation(value = "tumdetails")
	    @RequestMapping(value = "/tumblrdetails", method = RequestMethod.GET,produces = "text/plain")
	    public String tumblrdetails(HttpServletResponse res,HttpSession hs) throws IOException, TembooException {
		  TembooSession session = (TembooSession)hs.getAttribute("tumsession");
		  GetUserInformation getUserInformationChoreo = new GetUserInformation(session);
//Get an InputSet object for the choreo
GetUserInformationInputSet getUserInformationInputs = getUserInformationChoreo.newInputSet();
//Set inputs

getUserInformationInputs.set_APIKey("lgc7VWDsH16krJkvMJiWbiGZfWA4INPOl3b73s2vKwBTCoCMFo");
getUserInformationInputs.set_SecretKey("MRtGZLlar0cpoHfKwl3v8nkU0deQpyyTUIl7MbJAjTWlDqhOLT");
getUserInformationInputs.set_AccessToken(temboo);
getUserInformationInputs.set_AccessTokenSecret(temboosecret);
//Execute Choreo
GetUserInformationResultSet getUserInformationResults = getUserInformationChoreo.execute(getUserInformationInputs);
System.out.println(getUserInformationResults.get_Response());
String ob=getUserInformationResults.get_Response();


		  return ob;
		
	  }
	  
	  
	  
	  
	  
	  
	  
	  
   
   
   
   
   /* login verify*/
   
   
	  @ApiOperation(value = "Login")
	    @RequestMapping(value = "/login/{email},{password}", method = RequestMethod.GET,produces = "text/plain")
	    public String login(@PathVariable String email,@PathVariable String password,HttpServletResponse res,HttpSession hs) throws IOException {
		try
		{
		  register r=service.login(email,password);
		  String at=r.getAccesstoken();
		  String ats=r.getAccesstokensecret();
		  String p=r.getPassword();
		  String us=r.getEmail();
		
		  if(p.equals(password))
		  {
			 
		  if(at!=null && ats!=null)
		  {
			 a=at;
			 b=ats;
		
			 return "publish";
		  }
		  else
		  {
			 

			   hs.setAttribute("username", us);
			   return "twitpublish";
			  
			   
		  }
		  }
		}catch(Exception e)
		{
			
		}
		  
			  return "error";
		  
	       
	    }
	    
	  
	  /**oauth facebook verify 
	 * @throws IOException */
   
	 
   
	  
	  @ApiOperation(value = "FacebookOAuth")
	    @RequestMapping(value = "/fboauth", method = RequestMethod.GET)
	    public String getFBAuthUrl(HttpServletResponse res) throws IOException {
		    
		
		  String fbLoginUrl = "";
			try {
				fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
						+ FB_APP_ID + "&redirect_uri="
						+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
						+ "&scope=email,publish_actions,public_profile,user_status,user_posts,user_likes,user_photos,user_work_history,user_about_me,user_relationship_details,user_relationships,user_location";
				
				System.out.println("first");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println(fbLoginUrl);
			res.sendRedirect(fbLoginUrl);
			return fbLoginUrl;
	    }
	     
	  
	  
	  
	  
	  @ApiOperation(value = "redirectFb")
	    @RequestMapping(value = "/redirectfb", method = RequestMethod.GET)
	    public String redirectfb(HttpServletRequest req,HttpServletResponse res) throws IOException {
		 code = req.getParameter("code");
		System.out.println(code);
		System.out.println("six");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");}
	URL fbGraphURL = new URL("https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + FB_APP_ID + "&redirect_uri="
				+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
				+ "&client_secret=" + FB_APP_SECRET + "&code=" + code);
		  URLConnection	fbConnection = fbGraphURL.openConnection();
		BufferedReader	in = new BufferedReader(new InputStreamReader(
				fbConnection.getInputStream()));
		String inputLine;
		StringBuffer	b = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			b.append(inputLine + "\n");
	String	access = b.toString();
		if (access.startsWith("{")) {
			throw new RuntimeException("ERROR: Access Token Invalid: "
					+ access);}
		System.out.println("accessToken :"+access);
		String[] ab=access.split("=");
	String Token=ab[1];
	String[] yz=Token.split("&");
	
	
		accessToken=yz[0];
		System.out.println(accessToken);
		res.sendRedirect("twitpublish.jsp");
   return null;
	  }
   
   
   
	  
	  /**oauth facebook verify 
	 * @throws IOException */
   
	 
   
	  
	  @ApiOperation(value = "FacebookOAuths")
	    @RequestMapping(value = "/fboauths", method = RequestMethod.GET)
	    public String getFBAuthUrls(HttpServletResponse res) throws IOException {
		    
		
		  String fbLoginUrl = "";
			try {
				fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
						+ FB_APP_ID + "&redirect_uri="
						+ URLEncoder.encode(REDIRECT_URIs, "UTF-8")
						+ "&scope=email,publish_actions,public_profile,user_status,user_posts,user_likes,user_photos,user_work_history,user_about_me,user_relationship_details,user_relationships,user_location";
				
				System.out.println("first");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			try
			{
			System.out.println(fbLoginUrl);
			res.sendRedirect(fbLoginUrl);
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return fbLoginUrl;
	    }
	     
	  
	  
	  
	  
	  @ApiOperation(value = "redirectFbs")
	    @RequestMapping(value = "/redirectfbs", method = RequestMethod.GET)
	    public String redirectfbs(HttpServletRequest req,HttpServletResponse res) throws IOException {
		    
		
   
	  code = req.getParameter("code");
		System.out.println(code);
		System.out.println("six");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
	//	FBConnection fbConnection = new FBConnection();
		//String accessToken = fbConnection.getAccessToken(code);
		
		
		URL fbGraphURL = new URL("https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + FB_APP_ID + "&redirect_uri="
				+ URLEncoder.encode(REDIRECT_URIs, "UTF-8")
				+ "&client_secret=" + FB_APP_SECRET + "&code=" + code);
		  
		
		URLConnection	fbConnection = fbGraphURL.openConnection();
		
		BufferedReader	in = new BufferedReader(new InputStreamReader(
				fbConnection.getInputStream()));

		
		String inputLine;
		StringBuffer	b = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			b.append(inputLine + "\n");

	String	access = b.toString();
		if (access.startsWith("{")) {
			throw new RuntimeException("ERROR: Access Token Invalid: "
					+ access);
		}

		System.out.println(accessToken);
		String[] ab=access.split("=");
		
		String Token=ab[1];
		String[] yz=Token.split("&");
		
		System.out.println("multi first :"+yz[0]);
		try{
		System.out.println("multi second :"+yz[1]);
		}catch(Exception e)
		{
			System.out.println(e);
		}
			accessToken=yz[0];
			
		
		
		res.sendRedirect("publish.jsp");
   return null;
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   //login with facebook oauth
   
   /** print table id and status 
     * @throws IOException */
	  
	  @ApiOperation(value = "twitterlogin")
	    @RequestMapping(value = "/signin", method = RequestMethod.GET)
	    public String signin(HttpServletRequest request,HttpServletResponse response,HttpSession hs) throws IOException {
		 try
		 {
		  ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("nUiZZ0iBMyUrkZrZBhMPao8Ae")
			  .setOAuthConsumerSecret("9fpYueJwtdqepeVPqEJ6rAifkQOgBxwWUQO5bKN4zLbIhfPLQm");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			request.getSession().setAttribute("twitter", twitter);
			

			  StringBuffer callbackURL = request.getRequestURL();
			    int index = callbackURL.lastIndexOf("/");
			    callbackURL.replace(index, callbackURL.length(), "").append("/callback");
			

			    try
			    {
			    RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
			    System.out.println(requestToken);
			    request.getSession().setAttribute("requestToken", requestToken);

			    response.sendRedirect(requestToken.getAuthenticationURL());

			    System.out.println(requestToken.getAuthenticationURL());
			    }catch(Exception e)
			    {
			    	System.out.println(e);
			    }
			
		 }catch(Exception e)
		 {
			 
		 }
	        return "success";
	    }
	    
    
    
	  
	  @ApiOperation(value = "redirect")
	    @RequestMapping(value = "/callback", method = RequestMethod.GET)
	    public String callback(HttpServletRequest request,HttpServletResponse response,HttpSession hs) throws FileNotFoundException {

		  
			Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
			RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
			String verifier = request.getParameter("oauth_verifier");
	try
	{
			    AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			    request.getSession().removeAttribute("requestToken");
			    a=accessToken.getToken();
			    b=accessToken.getTokenSecret();
			    if(hs.getAttribute("username")!=null)
				{
			    String z=(String) hs.getAttribute("username");
			  
		    	String m=service.updatetoken(a,b,z);
					System.out.println(m);
				//hs.invalidate();
				}
			    
			    response.sendRedirect("publish.jsp");
	}catch(Exception e)
	{
		System.out.println(e);
	}
		  
	        return "call";
	    }
	    
	  
	  
	  
	  
	  //login with twitter oauth
	  
	  /** print table id and status 
	     * @throws IOException */
		  
		  @ApiOperation(value = "twitterlogin")
		    @RequestMapping(value = "/signins", method = RequestMethod.GET)
		    public String signins(HttpServletRequest request,HttpServletResponse response,HttpSession hs) throws IOException {
			   
			  ConfigurationBuilder cb = new ConfigurationBuilder();
				cb.setDebugEnabled(true)
				  .setOAuthConsumerKey("nUiZZ0iBMyUrkZrZBhMPao8Ae")
				  .setOAuthConsumerSecret("9fpYueJwtdqepeVPqEJ6rAifkQOgBxwWUQO5bKN4zLbIhfPLQm");
				TwitterFactory tf = new TwitterFactory(cb.build());
				Twitter twitter = tf.getInstance();
				request.getSession().setAttribute("twitter", twitter);
				

				  StringBuffer callbackURL = request.getRequestURL();
				    int index = callbackURL.lastIndexOf("/");
				    callbackURL.replace(index, callbackURL.length(), "").append("/callbacks");
				

				    try
				    {
				    RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
				    System.out.println(requestToken);
				    request.getSession().setAttribute("requestToken", requestToken);

				    response.sendRedirect(requestToken.getAuthenticationURL());

				    System.out.println(requestToken.getAuthenticationURL());
				    }catch(Exception e)
				    {
				    	System.out.println(e);
				    }
				

		        return "success";
		    }
		    
	    
	    
		  
		  @ApiOperation(value = "redirect")
		    @RequestMapping(value = "/callbacks", method = RequestMethod.GET)
		    public String callbacks(HttpServletRequest request,HttpServletResponse response,HttpSession hs) throws FileNotFoundException {

			  
				Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
				RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
				String verifier = request.getParameter("oauth_verifier");
		try
		{
				    AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
				    request.getSession().removeAttribute("requestToken");
				    a=accessToken.getToken();
				    b=accessToken.getTokenSecret();
				    if(hs.getAttribute("username")!=null)
					{
				    String z=(String) hs.getAttribute("username");
				  
			    	String m=service.updatetoken(a,b,z);
						System.out.println(m);
					//hs.invalidate();
					}
				    
				    response.sendRedirect("fbpublish.jsp");
		}catch(Exception e)
		{
			System.out.println(e);
		}
			  
		        return "call";
		    }
		    
		  
	  
	  
	  
	  
	/*	  facebook IMAGE posting control here	
			
		  @ApiOperation(value = "Facebook",notes="function for image")
		    @RequestMapping(value = "/faceimage", method = RequestMethod.POST)
		    public String postFacebookImages(@RequestParam CommonsMultipartFile file,HttpSession session) throws IOException {
			
			  ServletContext context = session.getServletContext();  
			    String path = context.getRealPath(UPLOAD_DIRECTORY);  
			    String filename = file.getOriginalFilename();  
			  
			    System.out.println(path+" "+filename); 

		//	File f=new File(file);
		
			//System.out.println("File Path"+f.getParent());
			//System.out.println("name :"+f.getParentFile());
			//FileReader f1=new FileReader( file);
			
			
			
		//		@SuppressWarnings("deprecation")
			//	FacebookClient fbClient= new DefaultFacebookClient(accessToken);
			//	FileInputStream fin=new FileInputStream(new File(images));
			//	FacebookType response=fbClient.publish("me/photos", FacebookType.class,BinaryAttachment.with("image.jpg",fin),Parameter.with("message", "status"));
		  //      System.out.println(response.getId());
		    //    String postid=response.getId();
		        
		        return "fb";
		    }
		      
	  */
	  
	  
	  
	  
    
	
	/*facebook posting control here	*/
	
	  @ApiOperation(value = "Facebook")
	    @RequestMapping(value = "/face/{status}", method = RequestMethod.POST)
	    public String postFacebook(@PathVariable String status) throws IOException {
		
		  System.out.println("done"); 
		
	   
		
			@SuppressWarnings("deprecation")
			FacebookClient fbClient= new DefaultFacebookClient(accessToken);
			FacebookType response=fbClient.publish("me/feed", FacebookType.class,Parameter.with("message", status));
	        System.out.println(response.getId());
	        String postid=response.getId();
	        
		    Post post = fbClient.fetchObject(postid,
					  Post.class,
					  Parameter.with("fields", "from,to,likes.limit(10).summary(true),comments.limit(10).summary(true),shares.limit(10).summary(true)"));
	        
	        Status1 s=new Status1();
	        s.setFacebookid(postid);
	        s.setStatus(status);
	        service.statusUpdate(s);
	        
	        return "fb";
	    }
	    
	  /*Twitter posting control here	*/
		
	  @ApiOperation(value = "TWITTER")
	    @RequestMapping(value = "/tweet/{status}", method = RequestMethod.POST)
	    public String postTwitter(@PathVariable String status) {
		 
		  
	      //Twitter
		  ConfigurationBuilder cb = new ConfigurationBuilder();
	        
	        cb.setDebugEnabled(true)
	             .setOAuthConsumerKey("nUiZZ0iBMyUrkZrZBhMPao8Ae")
	             .setOAuthConsumerSecret("9fpYueJwtdqepeVPqEJ6rAifkQOgBxwWUQO5bKN4zLbIhfPLQm")
	           .setOAuthAccessToken(a)
               .setOAuthAccessTokenSecret(b);
       
	  TwitterFactory tf=new TwitterFactory(cb.build());  
	  
	  twitter4j.Twitter tw=tf.getInstance();
	  
	  System.out.println(a);
	  System.out.println(b);
	
	   try {
		Status stat= tw.updateStatus(status);
		long l=stat.getId();
	System.out.println(stat.getUser().getName());
 y=stat.getUser().getName();
		String s=String.valueOf(l);
		
		Status1 n=new Status1();
		n.setTwitterid(s);
		n.setStatus(status);
		n.setUsername(y);
		service.twit(n);
	
	} catch (TwitterException e) {
		e.printStackTrace();
	}
	      System.out.println("Twitter updated");
	     
	      return "success";
	       
	    }
	    
	  /*Both Facebook and Twitter posting control here	*/
		
	  @ApiOperation(value = "BOTH FACEBOOK AND TWITTER")
	    @RequestMapping(value = "/facebookTweet/{status}", method = RequestMethod.POST)
	    public String postFacebookTwitter(@PathVariable String status,HttpSession hs) {
		  
		  
		  //Facebook
		  System.out.println("done");
			@SuppressWarnings("deprecation")
			FacebookClient fbClient= new DefaultFacebookClient(accessToken);
			FacebookType response=fbClient.publish("me/feed", FacebookType.class,Parameter.with("message", status));
	      System.out.println(response.getId());
		  
	      String postid=response.getId();
	        Status1 s=new Status1();
	        s.setFacebookid(postid);
	        s.setStatus(status);
	       
	      
	      
	      
	      
	      //Twitter
		  ConfigurationBuilder cb = new ConfigurationBuilder();
	        
	        cb.setDebugEnabled(true)
	             .setOAuthConsumerKey("nUiZZ0iBMyUrkZrZBhMPao8Ae")
	             .setOAuthConsumerSecret("9fpYueJwtdqepeVPqEJ6rAifkQOgBxwWUQO5bKN4zLbIhfPLQm")
	             .setOAuthAccessToken(a)
	             .setOAuthAccessTokenSecret(b);
	        
	  TwitterFactory tf=new TwitterFactory(cb.build());  
	  
	  twitter4j.Twitter tw=tf.getInstance();

	  try 
	  {
		Status stat= tw.updateStatus(status);
		long l=stat.getId();
		 y=stat.getUser().getName();
		String te=String.valueOf(l);
		s.setTwitterid(te);
		s.setUsername(y);
		service.both(s);
	  } 
	  catch (TwitterException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	      return "success";
 }
	  /** print table id and status */
	  
	  @ApiOperation(value = "FacebookDetails")
	    @RequestMapping(value = "/view", method = RequestMethod.GET)
	    public ViewList view(HttpSession hs) throws FileNotFoundException {
		    
		
	        return new ViewList(new ArrayList<Status1>(service.view(y)));
	    }
	    
	  
	  
	  @ApiOperation(value = "FacebookDetails")
	    @RequestMapping(value = "/details/{id}", method = RequestMethod.POST,produces="text/plain")
	    public String details(@PathVariable Integer id,HttpSession hs) throws FileNotFoundException, NumberFormatException, TwitterException {
		   

		  System.out.println("details");
			@SuppressWarnings("deprecation")
			FacebookClient fbClient= new DefaultFacebookClient(accessToken);
	
			Status1 s=service.detail(id);
			String r=s.getFacebookid();
		
		    Post post = fbClient.fetchObject(r,
					  Post.class,
					  Parameter.with("fields", "from,to,likes.limit(10).summary(true),comments.limit(10).summary(true),shares.limit(10).summary(true)"));

	    
		    
	long l=post.getLikesCount();
	long f=post.getCommentsCount();
	int f1=(int)f;
	System.out.println(f1);
	if(f1>0)
	{
		for(int f2=0;f2<f1;f2++)
			
		{
			hs.setAttribute("cname"+f2, post.getComments().getData().get(f2).getFrom().getName());
		
      		
		}
	}
	//System.out.println(post.getFrom().getName());
	int i=(int)l;
		String q=s.getTwitterid();
	if(i>0){	
		for(int j=0;j<i;j++){
			hs.setAttribute("lname"+j,post.getLikes().getData().get(j).getName());
		}
		
	/*	
		long w=post.getCommentsCount();
		int x=(int)w;
		for(int k=0;k<x;k++)
		{
			hs.setAttribute("cname"+k,post.getActions().get(k).getName());
             System.out.println(hs.getAttribute("cname"));
		}
	*/
				long likes=post.getLikesCount();
					long share= post.getSharesCount();
					long comments= post.getCommentsCount();
		
	
	hs.setAttribute("likes", likes);
	hs.setAttribute("comments", comments);
	hs.setAttribute("shares", share);
		    
	}
	
	/*twitter likes and comments session*/
	
	
    //Twitter
	  ConfigurationBuilder cb = new ConfigurationBuilder();
      
      cb.setDebugEnabled(true)
           .setOAuthConsumerKey("nUiZZ0iBMyUrkZrZBhMPao8Ae")
           .setOAuthConsumerSecret("9fpYueJwtdqepeVPqEJ6rAifkQOgBxwWUQO5bKN4zLbIhfPLQm")
           .setOAuthAccessToken(a)
           .setOAuthAccessTokenSecret(b);
      
TwitterFactory tf=new TwitterFactory(cb.build());  

twitter4j.Twitter tw=tf.getInstance();


//System.out.println("fav "+tw.getFavorites(Long.parseLong(q)).get(0).getUser().getName());

Status stat= tw.showStatus(Long.parseLong(q));
 int c=stat.getFavoriteCount();
 int d=stat.getRetweetCount();

hs.setAttribute("tlike", c);
 hs.setAttribute("tshare", d);
 
 if(d>0)
	{

 for(int z=0;z<d;z++)
 {
	 //tw.getFavorites(Long.parseLong(q)).get(0).getUser().getName();
    hs.setAttribute("tw"+z,tw.getRetweets(Long.parseLong(q)).get(z).getUser().getName());
 
 }	
	}
 /*if(c>0)
	{

 for(int v=0;v<c;v++)
 {
	// tw.getFavorites(Long.parseLong(q)).get(z).getUser().getName();
    hs.setAttribute("tweet"+v,tw.getFavorites(Long.parseLong(q)).get(v).getUser().getName());
 
 }
     
	}
	  
*/
 
	return "done";
	    }
	    
	  
	  
              /** logout 
            * @throws IOException */
	  
	    @ApiOperation(value = "logout")
	    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public String logout(HttpSession hs,HttpServletResponse res ) throws IOException {
		 try
		 {
	    	hs.invalidate();
		  res.sendRedirect("login.jsp");
		 }catch(Exception e)
		 {
			 
		 }
	      return "logout";
	       }
	    
	  
	  
	  
	  
	  
	  
	  }
