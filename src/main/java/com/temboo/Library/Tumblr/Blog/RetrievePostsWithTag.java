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
RetrievePostsWithTag

Retrieves posts that have a given tag.
*/
public class RetrievePostsWithTag extends Choreography {

	/**
	Create a new instance of the RetrievePostsWithTag Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public RetrievePostsWithTag(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/Tagged/RetrievePostsWithTag"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return RetrievePostsWithTagInputSet
	*/
	public RetrievePostsWithTagInputSet newInputSet() {
		return new RetrievePostsWithTagInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public RetrievePostsWithTagResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new RetrievePostsWithTagResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the RetrievePostsWithTag
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class RetrievePostsWithTagInputSet extends InputSet {
		/** 
		Set the value of the APIKey input for this Choreo. 

		@param String - (required, string) The API Key provided by Tumblr (AKA the OAuth Consumer Key).
		*/
		public void set_APIKey(String value) {
			this.setInput("APIKey", value);
		}


		/** 
		Set the value of the Before input for this Choreo. 

		@param String - (optional, string) The timestamp of when you'd like to see posts before (e.g. 1363716547).
		*/
		public void set_Before(String value) {
			this.setInput("Before", value);
		}


		/** 
		Set the value of the Filter input for this Choreo. 

		@param String - (optional, string) Specifies the post format to return. Valid values are: text (plain text, no html) or raw (as entered by the user).
		*/
		public void set_Filter(String value) {
			this.setInput("Filter", value);
		}


		/** 
		Set the value of the Limit input for this Choreo. 

		@param Integer - (optional, integer) The number of posts to return: 1- 20. Defaults to 20.
		*/
		public void set_Limit(Integer value) {
			this.setInput("Limit", value);
		}

		/** 
		Set the value of the Limit input for this Choreo as a String. 

		@param String - (optional, integer) The number of posts to return: 1- 20. Defaults to 20.
		*/
		public void set_Limit(String value) {
			this.setInput("Limit", value);	
		}
		/** 
		Set the value of the Offset input for this Choreo. 

		@param Integer - (optional, integer) The post number to start at. Defaults to 0.
		*/
		public void set_Offset(Integer value) {
			this.setInput("Offset", value);
		}

		/** 
		Set the value of the Offset input for this Choreo as a String. 

		@param String - (optional, integer) The post number to start at. Defaults to 0.
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
		Set the value of the Tag input for this Choreo. 

		@param String - (required, string) The tag on the posts you'd like to retrieve.
		*/
		public void set_Tag(String value) {
			this.setInput("Tag", value);
		}


	}
	
	
	/**
		A ResultSet with methods tailored to the values returned by the RetrievePostsWithTag Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class RetrievePostsWithTagResultSet extends ResultSet {
		public RetrievePostsWithTagResultSet(JSONObject doc) throws TembooException {
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
