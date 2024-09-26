/*
 * Copyright (c) 2024. Designed by Chirag Singh
 */

package com.freeautomation.stepdefinations;

import com.freeautomation.base.APIFactoryImplementation;
import com.freeautomation.constants.VariableName;
import com.freeautomation.pojo.Data;
import com.freeautomation.pojo.Product;
import com.freeautomation.utils.Constants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WireMockStepDefination {

    private APIFactoryImplementation helperObj;

    public WireMockStepDefination()
    {
        helperObj = new APIFactoryImplementation();
    }

    @Given("I am mocking different request")
    public void i_am_mocking_different_request(DataTable dataTable) {
        List<Map<String, String>> dataTableMaps = dataTable.asMaps();
        String methodType = dataTableMaps.get(0).get("Method_Type");
        String responseFile = dataTableMaps.get(0).get("Response_Value");
        String requestValue = dataTableMaps.get(0).get("Request_Value");
        if(methodType.equalsIgnoreCase("GET"))
        {
            helperObj.mockGetWireMockResponse("/api/getData/"+requestValue,responseFile);
        } else if (methodType.equalsIgnoreCase("post")) {
            helperObj.mockPostWireMockResponse("/api/postData",requestValue,responseFile);
        }
    }

    @Given("I am executing mock method request")
    public void i_am_executing_different_request(DataTable dataTable) {
        List<Map<String, String>> dataTableMaps = dataTable.asMaps();
        String methodType = dataTableMaps.get(0).get("Method_Type");
        String requestValue = dataTableMaps.get(0).get("Request_Value");
        if(methodType.equalsIgnoreCase("GET"))
        {
            Map<String,String> pathParam = new HashMap<>();
            pathParam.put("id",requestValue);
            helperObj.setPathParamHeader(pathParam);
            helperObj.executeAPIMethod("GET",helperObj.getWireMockURI()+"/api/getData/{id}");
        } else if (methodType.equalsIgnoreCase("post")) {
            helperObj.setContentType("json");
            helperObj.setRequestBody(helperObj.readFileAsString("src/test/resources/wiremock/__files/"+requestValue+".json"));
            helperObj.executeAPIMethod("POST",helperObj.getWireMockURI()+"/api/postData");
        }
    }
}
