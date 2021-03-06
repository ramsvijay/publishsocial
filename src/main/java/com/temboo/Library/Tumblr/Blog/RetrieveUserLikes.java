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
RetrieveUserLikes

Retrieves the liked posts for the user that corresponds to the OAuth credentials provided.
*/
public class RetrieveUserLikes extends Choreography {

	/**
	Create a new instance of the RetrieveUserLikes Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public RetrieveUserLikes(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/User/RetrieveUserLikes"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return RetrieveUserLikesInputSet
	*/
	public RetrieveUserLikesInputSet newInputSet() {
		return new RetrieveUserLikesInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public RetrieveUserLikesResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new RetrieveUserLikesResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the RetrieveUserLikes
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class RetrieveUserLikesInputSet extends InputSet {
		/** 
		Set the value of the APIKey input for this Choreo. 

		@param String - (required, string) The API Key provided by Tumblr (AKA the OAuth Consumer Key).
		*/
		public void set_APIKey(String value) {
			this.setInput("APIKey", value);
		}


		/** 
		Set the value of the AccessToken input for this Choreo. 

		@param String - (required, string) The Access Token retrieved during the OAuth process.
		*/
		public void set_AccessToken(String value) {
			this.setInput("AccessToken", value);
		}


		/** 
		Set the value of the AccessTokenSecret input for this Choreo. 

		@param String - (required, string) The Access Token Secret retrieved during the OAuth process.
		*/
		public void set_AccessTokenSecret(String value) {
			this.setInput("AccessTokenSecret", value);
		}


		/** 
		Set the value of the Limit input for this Choreo. 

		@param Integer - (optional, integer) The number of results to return: 1 - 20. Defaults to 20.
		*/
		public void set_Limit(Integer value) {
			this.setInput("Limit", value);
		}

		/** 
		Set the value of the Limit input for this Choreo as a String. 

		@param String - (optional, integer) The number of results to return: 1 - 20. Defaults to 20.
		*/
		public void set_Limit(String value) {
			this.setInput("Limit", value);	
		}
		/** 
		Set the value of the Offset input for this Choreo. 

		@param Integer - (optional, integer) The result to start at. Defaults to 0.
		*/
		public void set_Offset(Integer value) {
			this.setInput("Offset", value);
		}

		/** 
		Set the value of the Offset input for this Choreo as a String. 

		@param String - (optional, integer) The result to start at. Defaults to 0.
		*/
		public void set_Offset(String value) {
			this.setInput("Offset", value);	
		}
		/** 
		Set the value of the ResponseFormat input for this Choreo. 

		@param String - (optional, string) The format that the response should be in. Can be set to xml or json. Defaults to json.
		*/
		public void set_ResponseFormat(String value) {
			this.setInput("ResponseFormat", value);
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
		A ResultSet with methods tailored to the values returned by the RetrieveUserLikes Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class RetrieveUserLikesResultSet extends ResultSet {
		public RetrieveUserLikesResultSet(JSONObject doc) throws TembooException {
			super(doc);
		}

		/** 
		Retrieve the value for the "Response" output from this Choreo execution

		@return String - The response from Tumblr. Default is JSON, can be set to XML by entering 'xml' in ResponseFormat.
		*/
		public String get_Response() {
			return this.getResultString("Response");
		}
	}
	
}
