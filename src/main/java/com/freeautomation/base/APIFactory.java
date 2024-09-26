/*
 * Copyright (c) 2024. Created by Chirag Singh.
 */

package com.freeautomation.base;

import com.github.tomakehurst.wiremock.WireMockServer;
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
    public void invokeWireMockServer();
    public void closeWireMockServer();
    public void mockGetWireMockResponse(String uri, String response);
    public void mockPostWireMockResponse(String uri, String body,String response);
    public WireMockServer getWireMockServer();
    public void setWireMockServer(WireMockServer wireMockServer);
    public String getWireMockURI();
    public void setWireMockURI(String wireMockURI);
}
