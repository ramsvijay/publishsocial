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
RetrievePublishedPosts

Retrieves published posts using various search and filter parameters.
*/
public class RetrievePublishedPosts extends Choreography {

	/**
	Create a new instance of the RetrievePublishedPosts Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public RetrievePublishedPosts(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/Post/RetrievePublishedPosts"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return RetrievePublishedPostsInputSet
	*/
	public RetrievePublishedPostsInputSet newInputSet() {
		return new RetrievePublishedPostsInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public RetrievePublishedPostsResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new RetrievePublishedPostsResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the RetrievePublishedPosts
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class RetrievePublishedPostsInputSet extends InputSet {
		/** 
		Set the value of the APIKey input for this Choreo. 

		@param String - (required, string) The API Key provided by Tumblr (AKA the OAuth Consumer Key).
		*/
		public void set_APIKey(String value) {
			this.setInput("APIKey", value);
		}


		/** 
		Set the value of the BaseHostname input for this Choreo. 

		@param String - (required, string) The standard or custom blog hostname (i.e. temboo.tumblr.com).
		*/
		public void set_BaseHostname(String value) {
			this.setInput("BaseHostname", value);
		}


		/** 
		Set the value of the Format input for this Choreo. 

		@param String - (optional, string) Specifies the post format to return. Valid values are: text (Plain text, no HTML), raw (As entered by user). HTML is returned when left null.
		*/
		public void set_Format(String value) {
			this.setInput("Format", value);
		}


		/** 
		Set the value of the ID input for this Choreo. 

		@param Integer - (optional, integer) The specified post ID in order to return a single post.
		*/
		public void set_ID(Integer value) {
			this.setInput("ID", value);
		}

		/** 
		Set the value of the ID input for this Choreo as a String. 

		@param String - (optional, integer) The specified post ID in order to return a single post.
		*/
		public void set_ID(String value) {
			this.setInput("ID", value);	
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
		Set the value of the NotesInfo input for this Choreo. 

		@param Boolean - (optional, boolean) Indicates whether to return notes information (specify true or false). Defaults to 0 (false).
		*/
		public void set_NotesInfo(Boolean value) {
			this.setInput("NotesInfo", value);
		}

		/** 
		Set the value of the NotesInfo input for this Choreo as a String. 

		@param String - (optional, boolean) Indicates whether to return notes information (specify true or false). Defaults to 0 (false).
		*/
		public void set_NotesInfo(String value) {
			this.setInput("NotesInfo", value);	
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
		Set the value of the ReblogInfo input for this Choreo. 

		@param Boolean - (optional, boolean) Indicates whether to return reblog information (specify 1 or 0). Defaults to 0 (false).
		*/
		public void set_ReblogInfo(Boolean value) {
			this.setInput("ReblogInfo", value);
		}

		/** 
		Set the value of the ReblogInfo input for this Choreo as a String. 

		@param String - (optional, boolean) Indicates whether to return reblog information (specify 1 or 0). Defaults to 0 (false).
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
		Set the value of the Tag input for this Choreo. 

		@param String - (optional, string) Limits the response to posts with the specified tag.
		*/
		public void set_Tag(String value) {
			this.setInput("Tag", value);
		}


		/** 
		Set the value of the Type input for this Choreo. 

		@param String - (optional, string) The type of post to return. Specify one of the following:  text, quote, link, answer, video, audio, photo. When null, all types are returned.
		*/
		public void set_Type(String value) {
			this.setInput("Type", value);
		}


	}
	
	
	/**
		A ResultSet with methods tailored to the values returned by the RetrievePublishedPosts Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class RetrievePublishedPostsResultSet extends ResultSet {
		public RetrievePublishedPostsResultSet(JSONObject doc) throws TembooException {
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
