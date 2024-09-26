/*
 * Copyright (c) 2024. Created by Chirag Singh.
 */
package com.freeautomation.base;

import com.freeautomation.utils.Constants;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.jayway.jsonpath.DocumentContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class APIFactoryImplementation implements APIFactory{

    public static ThreadLocal<RequestSpecification> request = new InheritableThreadLocal<RequestSpecification>();
    public static ThreadLocal<Response> response = new InheritableThreadLocal<Response>();
    public static ThreadLocal<Scenario> log = new InheritableThreadLocal<Scenario>();
    public static ThreadLocal<WireMockServer> wireMock = new InheritableThreadLocal<>();
    public static ThreadLocal<String> wireMockURI = new InheritableThreadLocal<>();

    @Override
    public void invokeAPI() {
        if (getRequest()==null)
        {
            RequestSpecification request = RestAssured.given();
            setRequest(request);
        }

    }
    @Override
    public void executeAPIMethod(String method, String uri) {
        Response response = null;
        switch (method.toLowerCase()) {
            case "get":
                response = getRequest().get(uri);
                break;
            case "post":
                response = getRequest().post(uri);
                break;
            case "put":
                response = getRequest().put(uri);
                break;
            case "patch":
                response = getRequest().patch(uri);
                break;
            case "delete":
                response = getRequest().delete(uri);
                break;
            default:
                getLog().log("Given Method is not valid");
        }
        setResponse(response);
        getLog().log("RESPONSE :- \n"+getResponse().getBody().prettyPrint());
    }

    @Override
    public int getStatusCode() {
        return getResponse().getStatusCode();
    }

    @Override
    public RequestSpecification getRequest() {
        return this.request.get();
    }

    @Override
    public void setRequest(RequestSpecification request) {
        this.request.set(request);
    }

    @Override
    public Response getResponse() {
        return this.response.get();
    }

    @Override
    public void setResponse(Response response) {
        this.response.set(response);
    }

    @Override
    public String getResponseValue(String fileName,String jsonKey) {
        String jsonPath = getJSONValue(Constants.responseFilePath+fileName+".json",jsonKey);
        JsonPath jsonP = getResponse().jsonPath();
        return jsonP.get(jsonPath);
    }

    @Override
    public void releaseRequest() {
        this.request.remove();
    }
    @Override
    public Scenario getLog() {
        return this.log.get();
    }
    @Override
    public void setLog(Scenario scenario) {
        this.log.set(scenario);
    }
    @Override
    public void setQueryParamHeader(Map<String,?> queryParamHeader)
    {
        getRequest().queryParams(queryParamHeader);
    }
    @Override
    public void setPathParamHeader(Map<String,?> pathParamHeader)
    {
        getRequest().pathParams(pathParamHeader);
    }
    @Override
    public String readFileAsString(String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String getJSONValue(String filePath, String jsonPath) {
        String text = readFileAsString(filePath);
        return readJSON(jsonPath, text);
    }

    @Override
    public String readJSON(String jsonPath,String text) {
        DocumentContext jsonObject = com.jayway.jsonpath.JsonPath.parse(text);
        String textResult = jsonObject.read(jsonPath).toString();
        return textResult;

    }

    @Override
    public void setContentType(String contentType)
    {
        switch (contentType.toLowerCase()){
            case "json":
                getRequest().contentType(ContentType.JSON);
                break;
            case "xml":
                getRequest().contentType(ContentType.XML);
                break;
            case "text":
                getRequest().contentType(ContentType.TEXT);
                break;
            default:
                getLog().log("Content Type is not Valid!!");
        }
    }

    @Override
    public void setRequestBody(Object requestBody)
    {
        getRequest().body(requestBody);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);
            getLog().log("REQUEST : \n"+body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void invokeWireMockServer() {
        Integer wiremockPort = Integer.parseInt(getJSONValue(Constants.configFilePath,"mockPort"));
        String wiremockHost = getJSONValue(Constants.configFilePath,"mockHost");
        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(wiremockPort));
        wireMockServer.start();
        WireMock.configureFor(wiremockHost,wiremockPort);
        setWireMockServer(wireMockServer);
    }

    @Override
    public void closeWireMockServer() {
        if(getWireMockServer().isRunning() || getWireMockServer()!=null)
        {
            getWireMockServer().stop();
        }
    }
    @Override
    public void mockGetWireMockResponse(String uri, String response) {
        // Stub for GET request
        if(getWireMockServer()!=null)
        {
            stubFor(get(urlEqualTo(uri))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withBody(readFileAsString("src/test/resources/wiremock/__files/"+response+".json"))
                            .withHeader("Content-Type", "application/json")));
        }else
            getLog().log("WIREMOCK Server is not Running. Please check !!");
           // throw new RuntimeException("WIREMOCK Server is not Running. Please check !!");
    }

    @Override
    public void mockPostWireMockResponse(String uri, String body, String response) {
        // Stub for POST request
        if(getWireMockServer()!=null)
        {
            stubFor(post(urlEqualTo(uri))
                    .withRequestBody(equalToJson(readFileAsString("src/test/resources/wiremock/__files/"+body+".json")))
                    .willReturn(aResponse()
                            .withStatus(201)
                            .withBody(readFileAsString("src/test/resources/wiremock/__files/"+response+".json"))
                            .withHeader("Content-Type", "application/json")));
        }else {
            getLog().log("WIREMOCK Server is not Running.Please check !!");
         //   throw new RuntimeException("WIREMOCK Server is not Running. Please check !!");
        }

    }

    @Override
    public WireMockServer getWireMockServer() {
        return wireMock.get();
    }

    @Override
    public void setWireMockServer(WireMockServer wireMockServer) {
        wireMock.set(wireMockServer);
    }

    @Override
    public String getWireMockURI() {
        return wireMockURI.get();
    }

    @Override
    public void setWireMockURI(String wireMockUri) {
        wireMockURI.set(wireMockUri);
    }
}
