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

public class TembooException extends Exception {

	public TembooException(String message) {
		super(message);
	}
	
	public TembooException(Throwable e) {
		super((e == null ? null : e.getMessage()), e);
	}

	private static final long serialVersionUID = 368864799315402620L;

}
