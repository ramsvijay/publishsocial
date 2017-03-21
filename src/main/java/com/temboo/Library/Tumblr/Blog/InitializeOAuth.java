package com.temboo.Library.Tumblr.Blog;

/*
Copyright 2014 Temboo, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


import com.temboo.core.*;
import java.math.BigDecimal;
import com.temboo.core.Choreography;
import com.temboo.core.Choreography.ResultSet;
import com.temboo.core.Choreography.InputSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooPath;
import com.temboo.core.TembooSession;

/** 
InitializeOAuth

Generates an authorization URL that an application can use to complete the first step in the OAuth process.
*/
public class InitializeOAuth extends Choreography {

	/**
	Create a new instance of the InitializeOAuth Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public InitializeOAuth(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/OAuth/InitializeOAuth"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return InitializeOAuthInputSet
	*/
	public InitializeOAuthInputSet newInputSet() {
		return new InitializeOAuthInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public InitializeOAuthResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new InitializeOAuthResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the InitializeOAuth
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class InitializeOAuthInputSet extends InputSet {
		/** 
		Set the value of the APIKey input for this Choreo. 

		@param String - (required, string) The API Key provided by Tumblr (AKA the OAuth Consumer Key).
		*/
		public void set_APIKey(String value) {
			this.setInput("APIKey", value);
		}


		/** 
		Set the value of the AccountName input for this Choreo. 

		@param String - (optional, string) Deprecated (retained for backward compatibility only).
		*/
		public void set_AccountName(String value) {
			this.setInput("AccountName", value);
		}


		/** 
		Set the value of the AppKeyName input for this Choreo. 

		@param String - (optional, string) Deprecated (retained for backward compatibility only).
		*/
		public void set_AppKeyName(String value) {
			this.setInput("AppKeyName", value);
		}


		/** 
		Set the value of the AppKeyValue input for this Choreo. 

		@param String - (optional, string) Deprecated (retained for backward compatibility only).
		*/
		public void set_AppKeyValue(String value) {
			this.setInput("AppKeyValue", value);
		}


		/** 
		Set the value of the ForwardingURL input for this Choreo. 

		@param String - (optional, string) The URL that Temboo will redirect your users to after they grant access to your application. This should include the "https://" or "http://" prefix and be a fully qualified URL.
		*/
		public void set_ForwardingURL(String value) {
			this.setInput("ForwardingURL", value);
		}


		/** 
		Set the value of the SecretKey input for this Choreo. 

		@param String - (required, string) The Secret Key provided by Tumblr (AKA the OAuth Consumer Secret).
		*/
		public void set_SecretKey(String value) {
			this.setInput("SecretKey", value);
		}


	}
	
	
	/**
		A ResultSet with methods tailored to the values returned by the InitializeOAuth Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class InitializeOAuthResultSet extends ResultSet {
		public InitializeOAuthResultSet(JSONObject doc) throws TembooException {
			super(doc);
		}

		/** 
		Retrieve the value for the "AuthorizationURL" output from this Choreo execution

		@return String - (string) The authorization URL that the application's user needs to go to in order to grant access to your application.
		*/
		public String get_AuthorizationURL() {
			return this.getResultString("AuthorizationURL");
		}
		/** 
		Retrieve the value for the "CallbackID" output from this Choreo execution

		@return String - (string) An ID used to retrieve the callback data that Temboo stores once your application's user authorizes.
		*/
		public String get_CallbackID() {
			return this.getResultString("CallbackID");
		}
		/** 
		Retrieve the value for the "OAuthTokenSecret" output from this Choreo execution

		@return String - (string) The temporary OAuth Token Secret that can be exchanged for permanent tokens using the FinalizeOAuth Choreo.
		*/
		public String get_OAuthTokenSecret() {
			return this.getResultString("OAuthTokenSecret");
		}
	}
	
}
