/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wudoumi.batter.volley;

import com.wudoumi.batter.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * Data and headers returned from {@link com.wudoumi.batter.volley.Network#performRequest(com.wudoumi.batter.volley.Request)}.
 */
public class NetworkResponse {
    /**
     * Creates a new network response.
     * @param statusCode the HTTP status code
     * @param data Response body
     * @param headers Headers returned with this response, or null for none
     * @param notModified True if the server returned a 304 and the data was already in cache
     */
    public NetworkResponse(int statusCode, byte[] data, Map<String, String> headers,
            boolean notModified,String result) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
        this.result = result;
    }
    
    public NetworkResponse(int statusCode, byte[] data, Map<String, String> headers,boolean notModified) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
        this.result = getResult(data, headers);
    }
    

    public NetworkResponse(byte[] data) {
        this(HttpStatus.SC_OK, data, Collections.<String, String>emptyMap(), false);
    }

    public NetworkResponse(byte[] data, Map<String, String> headers) {
        this(HttpStatus.SC_OK, data, headers, false);
    }

    /** The HTTP status code. */
    public final int statusCode;

    /** Raw data from this response. */
    public final byte[] data;

    /** Response headers. */
    public final Map<String, String> headers;

    /** True if the server returned a 304 (Not Modified). */
    public final boolean notModified;
    
    
    
    
    public final String result;
    
    
    public NetworkResponse(String result) {
        this(HttpStatus.SC_OK, new byte[0], Collections.<String, String>emptyMap(), false,result);   
    }
    
    
    private String getResult(byte[] data,Map<String, String> headers){
    	String parsed;
		try {
            parsed = new String(data, HttpHeaderParser.parseCharset(headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(data);
        }
		
		return parsed;
    }
    
    
    
}