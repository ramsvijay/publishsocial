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
EditChatPost

Updates a specified chat post on a Tumblr blog.
*/
public class EditChatPost extends Choreography {

	/**
	Create a new instance of the EditChatPost Choreo. A TembooSession object, containing a valid
	set of Temboo credentials, must be supplied.
	*/
	public EditChatPost(TembooSession session) {
		super(session, TembooPath.pathFromStringNoException("/Library/Tumblr/Post/EditChatPost"));
	}


	/** 
	Obtain an InputSet object, used to define inputs for an execution of this Choreo.
	
	@return EditChatPostInputSet
	*/
	public EditChatPostInputSet newInputSet() {
		return new EditChatPostInputSet();
	}
	
	
	/**
	 * Execute the Choreo using the specified InputSet as parameters, wait for the Choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	 @Override
	public EditChatPostResultSet execute(InputSet choreoInputs) throws TembooException {
		JSONObject result = super.executeWithResults(choreoInputs);
		return new EditChatPostResultSet(result);
	}
	
	
	/**
		An InputSet with methods appropriate for specifying the inputs to the EditChatPost
        Choreo. The InputSet object is used to specify input parameters when executing this choreo.
	*/
	public static class EditChatPostInputSet extends InputSet {
		/** 
		Set the value of the Conversation input for this Choreo. 

		@param String - (required, multiline) The text of the conversation/chat, with dialogue labels (ex: Speaker: Dialogue.)(no HTML). Use separate lines for new lines as well as for a new speaker (include dialogue labels).
		*/
		public void set_Conversation(String value) {
			this.setInput("Conversation", value);
		}


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
		Set the value of the BaseHostname input for this Choreo. 

		@param String - (required, string) The standard or custom blog hostname (i.e. temboo.tumblr.com).
		*/
		public void set_BaseHostname(String value) {
			this.setInput("BaseHostname", value);
		}


		/** 
		Set the value of the Date input for this Choreo. 

		@param String - (optional, date) The GMT date and time of the post. Can be an epoch timestamp in milliseconds or formatted like: Dec 8th, 2011 4:03pm. Defaults to NOW().
		*/
		public void set_Date(String value) {
			this.setInput("Date", value);
		}


		/** 
		Set the value of the ID input for this Choreo. 

		@param Integer - (required, integer) The ID of the post you want to edit.
		*/
		public void set_ID(Integer value) {
			this.setInput("ID", value);
		}

		/** 
		Set the value of the ID input for this Choreo as a String. 

		@param String - (required, integer) The ID of the post you want to edit.
		*/
		public void set_ID(String value) {
			this.setInput("ID", value);	
		}
		/** 
		Set the value of the Markdown input for this Choreo. 

		@param Boolean - (optional, boolean) Indicates whether the post uses markdown syntax. Defaults to false. Set to 1 to indicate true.
		*/
		public void set_Markdown(Boolean value) {
			this.setInput("Markdown", value);
		}

		/** 
		Set the value of the Markdown input for this Choreo as a String. 

		@param String - (optional, boolean) Indicates whether the post uses markdown syntax. Defaults to false. Set to 1 to indicate true.
		*/
		public void set_Markdown(String value) {
			this.setInput("Markdown", value);	
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
		Set the value of the Slug input for this Choreo. 

		@param String - (optional, string) Adds a short text summary to the end of the post URL.
		*/
		public void set_Slug(String value) {
			this.setInput("Slug", value);
		}


		/** 
		Set the value of the State input for this Choreo. 

		@param String - (optional, string) The state of the post. Specify one of the following:  published, draft, queue. Defaults to published.
		*/
		public void set_State(String value) {
			this.setInput("State", value);
		}


		/** 
		Set the value of the Tags input for this Choreo. 

		@param String - (optional, string) Comma-separated tags for this post.
		*/
		public void set_Tags(String value) {
			this.setInput("Tags", value);
		}


		/** 
		Set the value of the Title input for this Choreo. 

		@param String - (optional, string) The title of the chat.
		*/
		public void set_Title(String value) {
			this.setInput("Title", value);
		}


		/** 
		Set the value of the Tweet input for this Choreo. 

		@param String - (optional, string) Manages the autotweet (if enabled) for this post. Set to "off" for no tweet. Enter text to override the default tweet.
		*/
		public void set_Tweet(String value) {
			this.setInput("Tweet", value);
		}


	}
	
	
	/**
		A ResultSet with methods tailored to the values returned by the EditChatPost Choreo.
        The ResultSet object is used to retrieve the results of a Choreo execution.
	*/
	public static class EditChatPostResultSet extends ResultSet {
		public EditChatPostResultSet(JSONObject doc) throws TembooException {
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
