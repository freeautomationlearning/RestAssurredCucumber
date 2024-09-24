/*
 * Copyright (c) 2024. Created by Chirag Singh.
 */

package com.freeautomation.base;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;


public interface APIFactory {

    public void invokeAPI();
    public void executeAPIMethod(String method, String uri);
    public int getStatusCode();
    public RequestSpecification getRequest();
    public void setRequest(RequestSpecification request);
    public Response getResponse();
    public void setResponse(Response response);
    public String getResponseValue(String fileName,String jsonKey);
    public void releaseRequest();
    public Scenario getLog();
    public void setLog(Scenario scenario);
    public void setQueryParamHeader(Map<String,?> queryParamHeader);
    public void setPathParamHeader(Map<String,?> pathParamHeader);
    public String readFileAsString(String fileName);
    public String getJSONValue(String filePath, String jsonPath);
    public String readJSON(String jsonPath,String text);
    public void setContentType(String contentType);
    public void setRequestBody(Object requestBody);

}
