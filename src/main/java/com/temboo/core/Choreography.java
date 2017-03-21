package com.temboo.core;
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



import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;


public class Choreography extends TembooResource {

    /**
     * The JavaScript client version, used solely when proxying requests
     */
    protected String jsClientVersion = null;

	public Choreography(TembooSession session, TembooPath uri) {
		super(session, uri);
	}

	
	/**
	 * Execute the choreography using the specified InputSet as parameters, wait for the choreo to complete 
	 * and return a ResultSet containing the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	protected ResultSet execute(InputSet choreoInputs) throws TembooException {
		return execute(choreoInputs, null);
	}

    /**
     * Execute the choreography using the specified InputSet as parameters, wait for the choreo to complete
     * and return a ResultSet containing the execution results.
     * @param choreoInputs
     * @param jsClientVersion
     * @return
     * @throws TembooException
     */
    protected ResultSet execute(InputSet choreoInputs, String jsClientVersion) throws TembooException {
        this.jsClientVersion = jsClientVersion;
        JSONObject document = executeWithResults(choreoInputs);
        return new ResultSet(document);
    }

    /**
	 * Execute the choreography, using the specified InputSet as parameters, and return immediately, discarding the execution results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	public String executeNoResults(InputSet choreoInputs)
			throws TembooException {

		return executeNoResults(choreoInputs, false);
	}
	

	/**
	 * Execute the choreography asynchronously, using the specified InputSet as parameters, and return a ChoreographyExecution 
	 * object which may be used to monitor the status of the running execution, or to obtain choreography results.
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	public ChoreographyExecution executeAsync(InputSet choreoInputs)
			throws TembooException {
		
		String exec_id = executeNoResults(choreoInputs, true);
		try {
			return new ChoreographyExecution(session, exec_id);
		} catch (URISyntaxException e) {
			throw new TembooException(e);
		}
	}
	
	
	/**
	 * Execute the choreography synchronously, using the specified InputSet as parameters, and return a 
	 * Document object representing the result XML.
	 * 
	 * @param choreoInputs
	 * @return
	 * @throws TembooException
	 */
	protected JSONObject executeWithResults(InputSet choreoInputs)
			throws TembooException {
		if (null == choreoInputs) {
			choreoInputs = new InputSet();
		}
		byte[] xml = choreoInputs.formatInputs();
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("source_id", this.getSourceId());
		return session.postContent(getPath(), xml, paramMap);		
	}
	
	
	/**
	 * Internal convenience method used to perform asynchronous choreography executions
	 * @param choreoInputs
	 * @param storeChoreoResults
	 * @return
	 * @throws TembooException
	 */
	private String executeNoResults(InputSet choreoInputs, boolean storeChoreoResults) throws TembooException {
		String id = null;
		if (null == choreoInputs) {
			choreoInputs = new InputSet();
		}
		try {
			byte[] xml = choreoInputs.formatInputs();
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("mode", "async");
			paramMap.put("source_id", this.getSourceId());
			if(storeChoreoResults)
				paramMap.put("store_results", "true");
			
			JSONObject result = session.postContent(getPath(), xml, paramMap);
			id = (String) result.opt("id");

		} catch (Exception e) {
			throw new TembooException(e);
		}
		return id;
}

    /**
     * Get the value for the 'source_id' parameter sent as part of REST API requests
     *
     * @return String
     */
    protected String getSourceId(){
        // Append the jsClientVersion string iff this is a proxied request
        return session.CLIENT_IDENTIFIER + ((jsClientVersion != null) ? "-" + jsClientVersion : "");
    }

	static String getStaticBasePath() {
		return TembooSession.BASE_PATH + "/choreos";
	}

	protected String getBasePath() {
		return getStaticBasePath();
	}


	public static class InputSet implements Serializable{
		private final Map<String, String> inputs = new HashMap<String, String>();
        private final List<OutputFilter> outputFilters = new ArrayList<OutputFilter>();
		private String credential = null;
		static final DecimalFormat decimalFormat = new DecimalFormat("########################.########################");

		
		/**
		 * Specify the Credential object to be used when running this choreo.
		 * @param credentialName
		 */
        public void setCredential(String credentialName) { credential = credentialName; }

        /**
         * Add a Profile object to be used when running this choreo.
         * @param credentialName
         */
        public void addProfile(String credentialName) {
            credential = credentialName;
        }

		/**
		 * Set the named input to the specified value
		 * @param name
		 * @param value
		 */
		public void setInput(String name, String value) {
			inputs.put(name, value);
		}

		/**
		 * Set the named input to the specified value
		 * @param name
		 * @param value
		 */
		public void setInput(String name, BigDecimal value) {
			if (value == null) {
				inputs.put(name, "0");
			}
			String stringValue = decimalFormat.format(value.stripTrailingZeros());
			inputs.put(name, stringValue);
		}

		/**
		 * Set the named input to the specified value
		 * @param name
		 * @param value
		 */
		public void setInput(String name, Integer value) {
			if (value == null) {
				inputs.put(name, "0");
			}
			String stringValue = value.toString();
			inputs.put(name, stringValue);
		}

		/**
		 * Set the named input to the specified value
		 * @param name
		 * @param value
		 */
		public void setInput(String name, Boolean value) {
			if (value == null) {
				inputs.put(name, Boolean.toString(false));
			}
			String stringValue = value.toString();
			inputs.put(name, stringValue);
		}

        /**
         * Add an output filter to the results.
         * @param filterName
         * @param path
         * @param outputVariableSource
         */
        public void addOutputFilter(String filterName, String path, String outputVariableSource) {
            outputFilters.add(new OutputFilter(filterName, path, outputVariableSource));
        }

		private byte[] formatInputs() throws TembooException {
			try {

				HashMap<String, Object> allInputs = new HashMap<String, Object>();

				// coalesce everything into one hashmap
				ArrayList<Map<String, String>> inputList =
						new ArrayList<Map<String, String>>();

				if (null != credential) 
					allInputs.put("preset", credential);					
				
				for (Entry<String, String> entry : inputs.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue();
					HashMap<String, String> input = new HashMap<String, String>(1);
					input.put("name", name);
					input.put("value", value);
					inputList.add(input);
				}
				
				allInputs.put("inputs", inputList);

                ArrayList<Map<String, String>> outputList =
                        new ArrayList<Map<String, String>>();

                for (OutputFilter filter : outputFilters) {
                    HashMap<String, String> output = new HashMap<String, String>(1);
                    output.put("name", filter.getFilterName());
                    output.put("variable", filter.getOutputVariableSource());
                    output.put("path", filter.getPath());
                    outputList.add(output);
                }

                allInputs.put("outputFilters", outputList);

				return JSONObject.valueToString(allInputs).getBytes();
			
			} catch (Exception e) {
				throw new TembooException(e);
			}			
		}
	}

	public static class ResultSet {

		/**
		 * Represents the possible completion statuses of a choreo		 *
		 */
		public enum Status {
			SUCCESS, ERROR, TERMINATED_MANUALLY, TERMINATED_LIMIT, RUNNING
		}

		private String id = "";
		private Status status = Status.ERROR;
		private Date startTime = null, endTime = null;
		private Map<String, Object> outputs = new HashMap<String, Object>();

		public static Object getJSONFromString(String str) {
			try {
				if (str.trim().startsWith("{")) {
					return new JSONObject(str);
				} else {
					return new JSONArray(str);
				}
			} catch (JSONException e) {
				return null;
			}
		}

		public static JSONObject getJSONObject(Object json, String key) {
			JSONObject toReturn = null;

			try {
				if(json != null && (json instanceof JSONObject))
					toReturn = ((JSONObject) json).optJSONObject(key);					
			} catch(Exception e) {
			}
			if(toReturn == null)
				toReturn = new JSONObject();
			
			return toReturn;
		}

		protected ResultSet(JSONObject doc) throws TembooException {
			if (doc != null) {
				JSONObject execution = (JSONObject) doc.opt("execution");
				id = (String) execution.opt("id");
				String statusString = (String) execution.opt("status");

				status = Status.valueOf(statusString);
				String timeStr = (String) execution.opt("starttime");
				startTime = new Date(Long.parseLong(timeStr));
				timeStr = (String) execution.opt("endtime");
					endTime = new Date(Long.parseLong(timeStr));

				JSONObject outputList = (JSONObject) doc.opt("output");
			
				Set<String> keys = outputList.keySet();
				for(String k : keys) {
					outputs.put(k, outputList.opt(k));
				}
			}
		}

		// get the completion status of the choreo (success, error, manually
		// terminated, etc.)
		public Status getCompletionStatus() {
			return status;
		}

		// get the start time of the choreo (in UTC)
		public Date getStartTime() {
			return startTime;
		}

		// get the completion time of the choreo (in UTC)
		public Date getCompletionTime() {
			return endTime;
		}

		// get the execution id
		public String getId() {
			return id;
		}

		// get the exception that was thrown during execution, if any; returns
		// null if execution was successful
		public TembooException getException() {
			return null;
		}

        // get the value of the named output
        public String getResultString(String key) {
            return outputs.get(key).toString();
        }

        // get the value of the named output as list
        public JSONArray getResultList(String key) {
            final Object o = outputs.get(key);
            if (o instanceof JSONArray) {
                return (JSONArray) o;
            } else {
                JSONArray list = new JSONArray();
                list.put(o);
                return list;
            }
        }

        // get the set of output names for this execution
		public Iterator<String> keySet() {
			return outputs.keySet().iterator();
		}

		public Map<String, Object> getOutputs() {
			return outputs;
		}
	}

    private static class OutputFilter implements Serializable{
        final String filterName;
        final String path;
        final String outputVariableSource;
        protected OutputFilter(String theFilterName, String thePath, String theOutputVariableSource) {
            filterName = theFilterName;
            path = thePath;
            outputVariableSource = theOutputVariableSource;
        }

        public String getFilterName() {
            return filterName;
        }

        public String getPath() {
            return path;
        }

        public String getOutputVariableSource() {
            return outputVariableSource;
        }
    }
}
