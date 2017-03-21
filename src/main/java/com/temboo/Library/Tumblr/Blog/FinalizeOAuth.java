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
FinalizeOAuth

Completes the OAuth process by retrieving a Tumblr access token and access token secret for a user, after they have visited the authorization URL returned by the InitializeOAuth Choreo and clicked "allow."
*/
public class FinalizeOAuth extends Choreography {

	/**
	Create a new instance of the FinalizeOAuth Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public FinalizeOAuth(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/OAuth/FinalizeOAuth"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return FinalizeOAuthInputSet
	*/
	public FinalizeOAuthInputSet newInputSet() {
		return new FinalizeOAuthInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public FinalizeOAuthResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new FinalizeOAuthResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the FinalizeOAuth
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class FinalizeOAuthInputSet extends InputSet {
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
		Set the value of the CallbackID input for this Choreo. 

		@param String - (required, string) The callback token returned by the InitializeOAuth Choreo. Used to retrieve the callback data after the user authorizes.
		*/
		public void set_CallbackID(String value) {
			this.setInput("CallbackID", value);
		}


		/** 
		Set the value of the OAuthTokenSecret input for this Choreo. 

		@param String - (required, string) The oauth_token_secret retrieved during the OAuth process. This is returned by the InitializeOAuth Choreo.
		*/
		public void set_OAuthTokenSecret(String value) {
			this.setInput("OAuthTokenSecret", value);
		}


		/** 
		Set the value of the SecretKey input for this Choreo. 

		@param String - (required, string) The Secret Key provided by Tumblr (AKA the OAuth Consumer Secret).
		*/
		public void set_SecretKey(String value) {
			this.setInput("SecretKey", value);
		}


		/** 
		Set the value of the SuppressErrors input for this Choreo. 

		@param Boolean - (optional, boolean) When set to true, errors received during the OAuth redirect process will be suppressed and returned in the ErrorMessage output.
		*/
		public void set_SuppressErrors(Boolean value) {
			this.setInput("SuppressErrors", value);
		}

		/** 
		Set the value of the SuppressErrors input for this Choreo as a String. 

		@param String - (optional, boolean) When set to true, errors received during the OAuth redirect process will be suppressed and returned in the ErrorMessage output.
		*/
		public void set_SuppressErrors(String value) {
			this.setInput("SuppressErrors", value);	
		}
		/** 
		Set the value of the Timeout input for this Choreo. 

		@param Integer - (optional, integer) The amount of time (in seconds) to poll your Temboo callback URL to see if your app's user has allowed or denied the request for access. Defaults to 20. Max is 60.
		*/
		public void set_Timeout(Integer value) {
			this.setInput("Timeout", value);
		}

		/** 
		Set the value of the Timeout input for this Choreo as a String. 

		@param String - (optional, integer) The amount of time (in seconds) to poll your Temboo callback URL to see if your app's user has allowed or denied the request for access. Defaults to 20. Max is 60.
		*/
		public void set_Timeout(String value) {
			this.setInput("Timeout", value);	
		}
	}
	
	
	/**
		A ResultSet with methods tailored to the values returned by the FinalizeOAuth Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class FinalizeOAuthResultSet extends ResultSet {
		public FinalizeOAuthResultSet(JSONObject doc) throws TembooException {
			super(doc);
		}

		/** 
		Retrieve the value for the "AccessToken" output from this Choreo execution

		@return String - (string) The Access Token retrieved during the OAuth process.
		*/
		public String get_AccessToken() {
			return this.getResultString("AccessToken");
		}
		/** 
		Retrieve the value for the "AccessTokenSecret" output from this Choreo execution

		@return String - (string) The Access Token Secret retrieved during the OAuth process.
		*/
		public String get_AccessTokenSecret() {
			return this.getResultString("AccessTokenSecret");
		}
		/** 
		Retrieve the value for the "ErrorMessage" output from this Choreo execution

		@return String - (string) Contains an error message if an error occurs during the OAuth redirect process and if SuppressErrors is set to true.
		*/
		public String get_ErrorMessage() {
			return this.getResultString("ErrorMessage");
		}
	}
	
}
