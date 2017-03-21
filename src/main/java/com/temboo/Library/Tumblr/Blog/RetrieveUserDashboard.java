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
RetrieveUserDashboard

Retrieves the dashboard of the user that corresponds to the OAuth credentials provided.
*/
public class RetrieveUserDashboard extends Choreography {

	/**
	Create a new instance of the RetrieveUserDashboard Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public RetrieveUserDashboard(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/User/RetrieveUserDashboard"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return RetrieveUserDashboardInputSet
	*/
	public RetrieveUserDashboardInputSet newInputSet() {
		return new RetrieveUserDashboardInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public RetrieveUserDashboardResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new RetrieveUserDashboardResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the RetrieveUserDashboard
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class RetrieveUserDashboardInputSet extends InputSet {
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
		Set the value of the NotesInfo input for this Choreo. 

		@param Boolean - (optional, boolean) Indicates whether to return notes information. Specify 1(true) or 0 (false). Defaults to 0.
		*/
		public void set_NotesInfo(Boolean value) {
			this.setInput("NotesInfo", value);
		}

		/** 
		Set the value of the NotesInfo input for this Choreo as a String. 

		@param String - (optional, boolean) Indicates whether to return notes information. Specify 1(true) or 0 (false). Defaults to 0.
		*/
		public void set_NotesInfo(String value) {
			this.setInput("NotesInfo", value);	
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
		Set the value of the ReblogInfo input for this Choreo. 

		@param Boolean - (optional, boolean) Indicates whether to return reblog information. Specify 1(true) or 0 (false). Defaults to 0.
		*/
		public void set_ReblogInfo(Boolean value) {
			this.setInput("ReblogInfo", value);
		}

		/** 
		Set the value of the ReblogInfo input for this Choreo as a String. 

		@param String - (optional, boolean) Indicates whether to return reblog information. Specify 1(true) or 0 (false). Defaults to 0.
		*/
		public void set_ReblogInfo(String value) {
			this.setInput("ReblogInfo", value);	
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


		/** 
		Set the value of the SinceId input for this Choreo. 

		@param Integer - (optional, integer) Return posts that have appeared after this ID. Used to page through results.
		*/
		public void set_SinceId(Integer value) {
			this.setInput("SinceId", value);
		}

		/** 
		Set the value of the SinceId input for this Choreo as a String. 

		@param String - (optional, integer) Return posts that have appeared after this ID. Used to page through results.
		*/
		public void set_SinceId(String value) {
			this.setInput("SinceId", value);	
		}
		/** 
		Set the value of the Type input for this Choreo. 

		@param String - (optional, string) The type of post to return. Specify one of the following:  text, photo, quote, link, chat, audio, video, answer.
		*/
		public void set_Type(String value) {
			this.setInput("Type", value);
		}


	}
	
	
	/**
		A ResultSet with methods tailored to the values returned by the RetrieveUserDashboard Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class RetrieveUserDashboardResultSet extends ResultSet {
		public RetrieveUserDashboardResultSet(JSONObject doc) throws TembooException {
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
