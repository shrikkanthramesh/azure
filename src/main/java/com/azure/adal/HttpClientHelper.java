/*******************************************************************************
 * Copyright © Microsoft Open Technologies, Inc.
 * 
 * All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * THIS CODE IS PROVIDED *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * ANY IMPLIED WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A
 * PARTICULAR PURPOSE, MERCHANTABILITY OR NON-INFRINGEMENT.
 * 
 * See the Apache License, Version 2.0 for the specific language
 * governing permissions and limitations under the License.
 ******************************************************************************/
package com.azure.adal;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is Helper class for all RestClient class.
 * 
 * @author Azure Active Directory Contributor
 * 
 */
public class HttpClientHelper {

    public HttpClientHelper() {
        super();
    }

    public static String getResponseStringFromConn(HttpURLConnection conn, boolean isSuccess) throws IOException {

        BufferedReader reader = null;
        if (isSuccess) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }

        return stringBuffer.toString();
    }


    /**
     * for good response
     * 
     * @param responseCode
     * @param responseMsg
     * @return
     * @throws JSONException
     */
    public static JSONObject processGoodRespStr(int responseCode, String goodRespStr) throws JSONException {
        JSONObject response = new JSONObject();
        response.put("responseCode", responseCode);
        if (goodRespStr.equalsIgnoreCase("")) {
            response.put("responseMsg", "");
        } else {
            response.put("responseMsg", new JSONObject(goodRespStr));
        }

        return response;
    }


}
