package com.temboo.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.*;

/**
 * Proxies execution requests from the Temboo Javascript Library.
 *
 * Allowed choreographies and javascript-writeable inputs must be whitelisted.
 * Authentication to Temboo is internal to the proxy object, via a standard
 * TembooSession. Authenticating the JS library client request to the Java
 * process containing the proxy is left up to the Java server implementation.
 */
public class TembooProxy {

    protected Map<String, ProxifiedChoreography> choreos = new HashMap<String, ProxifiedChoreography>();

    /**
     * Whitelist a choreography for the JavaScript SDK
     * @param name
     * @param choreo
     * @throws TembooException
     */
    public void addChoreo(String name, Choreography choreo)
        throws TembooException {
        addChoreo(name, choreo, new HashMap<String, String>());
    }

    /**
     * Whitelist a choreography for the JavaScript SDK
     * @param name
     * @param choreo
     * @param inputs
     * @param allowedUserInputs
     * @throws TembooException
     */
    public void addChoreo(String name, Choreography choreo, Map<String, String> inputs, String... allowedUserInputs)
        throws TembooException {

        Choreography.InputSet choreoInputs = new Choreography.InputSet();

        String inputName = null;
        Object inputValue = null;

        for(Map.Entry<String,String> input : inputs.entrySet()){
            inputName = input.getKey();
            inputValue = input.getValue();

            if(inputValue instanceof String)
                choreoInputs.setInput(inputName, (String)inputValue);
            else if(inputValue instanceof BigDecimal)
                choreoInputs.setInput(inputName, (BigDecimal)inputValue);
            else if(inputValue instanceof Integer)
                choreoInputs.setInput(inputName, (Integer)inputValue);
            else if(inputValue instanceof Boolean)
                choreoInputs.setInput(inputName, (Boolean)inputValue);
            else
                throw new TembooException("Unrecognized data type for input: " + name);
        }

        addChoreo(name, choreo, choreoInputs, allowedUserInputs);
    }

    /**
     * Whitelist a choreography for the JavaScript SDK
     * @param name
     * @param choreo
     * @param inputs
     * @param allowedUserInputs
     * @throws TembooException
     */
    public void addChoreo(String name, Choreography choreo, Choreography.InputSet inputs, String... allowedUserInputs)
        throws TembooException {
        // Add the choreo to our list of known choreos
        choreos.put(name, new ProxifiedChoreography(choreo));

        // Set the default inputs for the choreo
        setDefaultInputs(name, inputs);

        // Set user inputs
        for(String s : allowedUserInputs)
            allowUserInputs(name, (String)s);
    }

    /**
     * Whitelist a choreography input to be set via the JavaScript SDK
     * @param name
     * @param input
     * @throws TembooException
     */
    public void allowUserInputs(String name, String input)
        throws TembooException {

        if(!choreos.containsKey(name)) {
            throw new TembooNotFoundException("Proxied choreography not found: " + name);
        }

        choreos.get(name).allowUserInputs(input);
    }

    /**
     * Execute a whitelisted choreography
     * @param request
     * @param jsonEncode
     * @return
     * @throws JSONException
     * @throws TembooException
     */
    public Object execute(String request, Boolean jsonEncode)
        throws JSONException, TembooException {
        // Convert String request to JSONObject and execute on that
        return execute(new JSONObject(request), jsonEncode);
    }

    /**
     * Execute a whitelisted choreography
     * @param request
     * @param jsonEncode
     * @return
     * @throws JSONException
     * @throws TembooException
     */
    public Object execute(JSONObject request, Boolean jsonEncode)
        throws JSONException {
        return execute(JSONHelper.jsonToMap(request), jsonEncode);
    }

    /**
     * Execute a whitelisted choreography
     * @param request
     * @param jsonEncode
     * @return
     * @throws JSONException
     * @throws TembooException
     */
    public Object execute(Map<String, Object> request, Boolean jsonEncode)
        throws JSONException {
        
        Map<String, Object> response = new HashMap<String, Object>();

        try{
            if(!request.containsKey("name")){
                throw new TembooException("No choreography was specified in the request");
            }

            if(!request.containsKey("version")){
                throw new TembooException("Missing required version string");
            }

            String name = (String)request.get("name");
            String jsClientVersion = (String)request.get("version");

            if(!choreos.containsKey(name)){
                throw new TembooNotFoundException("Proxied choreography not found: " + name);
            }

            // Pass through user inputs, if any
            Map<String, Object> inputs = request.containsKey("inputs")
                    ? (HashMap<String, Object>)request.get("inputs")
                    : new HashMap<String, Object>();

            // Pass through output filters, if any
            Map<String, Map<String, String>> outputFilters = request.containsKey("outputFilters")
                    ? (HashMap<String, Map<String, String>>)request.get("outputFilters")
                    : new HashMap<String, Map<String, String>>();

            // Execute the choreo
            Map<String, Object> results = choreos.get(name).execute(inputs, outputFilters, jsClientVersion);
            
            // Build the response
            response.put("success", true);
            response.put("outputs", results);
            
            // Done - return encoded or not, based on what was requested
            return jsonEncode ? new JSONObject(response) : response;
        }
        catch(TembooJSProxyException e) {
            // Delegate to the exception to build itself out in the correct format
            return e.getJSResponse(jsonEncode);
        }
        catch(TembooException e) {
            response.put("error", true);
            response.put("type", "Temboo");
            response.put("message", e.getMessage());
            return jsonEncode ? new JSONObject(response) : response;
        }
        catch(Exception e){
            response.put("error", true);
            response.put("type", "Server");
            response.put("nativeType", e.getClass().getName());
            response.put("message", e.getMessage());
            return jsonEncode ? new JSONObject(response) : response;
        }
    }

    /**
     * Set the default inputs for a whitelisted choreography
     * @param name
     * @param inputs
     * @throws TembooException
     */
    public void setDefaultInputs(String name, Choreography.InputSet inputs)
        throws TembooException {

        if(!choreos.containsKey(name)){
            throw new TembooException("Proxied choreography not found: " + name);
        }

        choreos.get(name).setDefaultInputs(inputs);
    }


    /**
     * Simple helper class to assist with JSON manipulation
     */
    private static class JSONHelper {

        /**
         * Convert a JSONObject to a HashMap
         * @param obj
         * @return
         * @throws JSONException
         */
        public static Map<String, Object> jsonToMap(JSONObject obj)
            throws JSONException {

            Map<String, Object> map = new HashMap<String, Object>();

            if(JSONObject.NULL.equals(obj)){
                Iterator<String> keyIterator = obj.keys();

                String key = null;
                Object value = null;

                while(keyIterator.hasNext()){
                    key = keyIterator.next();
                    value = obj.get(key);

                    if(value instanceof JSONArray){
                        value = JSONHelper.jsonToList((JSONArray) value);
                    }
                    else if(value instanceof JSONObject){
                        value = JSONHelper.jsonToMap((JSONObject) value);
                    }

                    map.put(key, value);
                }
            }

            return map;
        }

        /**
         * Convert a JSONArray to an ArrayList
         * @param arr
         * @return
         * @throws JSONException
         */
        private static List<Object> jsonToList(JSONArray arr)
            throws JSONException {
            List<Object> list = new ArrayList<Object>();
            for(int i=0; i<arr.length(); i++)
                list.add(arr.get(i));
            return list;
        }
    }


    /**
     * Wrapper class representing a choreography which has been whitelisted
     * to be run from the JavaScript SDK
     */
    private static class ProxifiedChoreography {

        protected Choreography choreo;
        protected Choreography.InputSet inputs = null;
        protected HashSet<String> allowedUserInputs = new HashSet<String>();

        /**
         * Constructor
         * @param choreo
         */
        public ProxifiedChoreography(Choreography choreo){
            this.choreo = choreo;
        }

        /**
         * Whitelist an input to be set from the JavaScript SDK
         * @param inputName
         */
        public void allowUserInputs(String inputName){
            if(!allowedUserInputs.contains(inputName))
                allowedUserInputs.add(inputName);
        }

        /**
         * Clone an InputSet by taking advantage of the fact that it implements Serializable
         * @param inputs The inputs to clone
         * @return A clone of the provided InputSet
         * @throws TembooException if de/serialization fails
         */
        protected Choreography.InputSet cloneInputs(Choreography.InputSet inputs)
            throws TembooException {
            try {
                // Serialize
                java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
                oos.writeObject(inputs);
                oos.close();
                byte serialized[] = baos.toByteArray();

                // Deserialize
                java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(serialized);
                java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
                Choreography.InputSet theClone = (Choreography.InputSet)ois.readObject();
                ois.close();

                // Done
                return theClone;
            } catch (Exception e) {
                throw new TembooException("Failed to clone InputSet");
            }
        }

        /**
         * Execute the underlying choreography with the given set of user inputs
         * @param userInputs
         * @return
         * @throws TembooException
         */
        public Map<String, Object> execute(Map<String, Object> userInputs, Map<String, Map<String,String>>outputFilters, String jsClientVersion)
            throws TembooException {

            String name = null;
            Object value = null;

            Choreography.InputSet fullInputs = cloneInputs(inputs);

            // Set inputs
            for(Map.Entry<String, Object> entry : userInputs.entrySet()){
                name = entry.getKey();
                value = entry.getValue();

                if(!allowedUserInputs.contains(name)){
                    throw new TembooDisallowedInputException("Illegal input specified", name);
                }

                if(value instanceof String)
                    fullInputs.setInput(name, (String)value);
                else if(value instanceof BigDecimal)
                    fullInputs.setInput(name, (BigDecimal) value);
                else if(value instanceof Integer)
                    fullInputs.setInput(name, (Integer)value);
                else if(value instanceof Boolean)
                    fullInputs.setInput(name, (Boolean)value);
                else {
                    throw new TembooException("Unrecognized data type for input: " + name);
                }
            }

            // Add output filters
            Map<String, String> props = null;
            for(Map.Entry<String, Map<String, String>> entry : outputFilters.entrySet()){
                name = entry.getKey();
                props = entry.getValue();
                fullInputs.addOutputFilter(name, props.get("path"), props.get("variable"));
            }

            // Execute, forwarding JS client version string
            return choreo.execute(fullInputs, jsClientVersion).getOutputs();
        }

        /**
         * Set the default inputs for this choreography
         * @param inputs
         */
        public void setDefaultInputs(Choreography.InputSet inputs){
            this.inputs = inputs;
        }
    }

}