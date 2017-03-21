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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TembooJSProxyException extends TembooException {

    private final String type;
    // Subclasses may act on this directly
    protected Map<String, Object> response;

    public TembooJSProxyException(String type, String message) {
        super(message);

        response = new HashMap<String, Object>();
        response.put("error", true);
    	response.put("type", type);
    	response.put("message", getMessage());
        
        this.type = type;
    }

    public Object getJSResponse(Boolean asJSON) {
    	return asJSON ? new JSONObject(response) : response;
    }

    public String getType() {
        return type;
    }
}