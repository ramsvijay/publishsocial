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

public abstract class TembooResource {

	protected final TembooSession session;
	protected final TembooPath uri;

	public TembooResource(TembooSession session, TembooPath uri) {
		this.session = session;
		this.uri = uri;
	}

	protected String getPath() {
		return getBasePath() + uri.toString();
	}

	protected abstract String getBasePath();

	public TembooPath getURI() {
		return uri;
	}
}