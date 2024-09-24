/*
 * Copyright (c) 2024. Designed by Chirag Singh
 */

package com.freeautomation.stepdefinations;

import com.freeautomation.base.APIFactoryImplementation;
import com.freeautomation.constants.VariableName;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStepDefination {

    private APIFactoryImplementation helperObj;

    public GetStepDefination()
    {
        helperObj = new APIFactoryImplementation();
    }

    @Given("I am executing GET method request")
    public void i_am_executing_get_method_request(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        if (data.get(1).get(0).equalsIgnoreCase("PathParam"))
        {
            Map<String,String> pathParam = new HashMap<>();
            if(data.get(1).get(1).equalsIgnoreCase("AutoGenerate"))
            {
                pathParam.put("id",VariableName.getProductID());
            }else {
                pathParam.put("id",data.get(1).get(1));
            }
            helperObj.setPathParamHeader(pathParam);
            helperObj.executeAPIMethod("GET","/objects/{id}");
        } else if (data.get(1).get(0).equalsIgnoreCase("QueryParam")) {
            String queryValues[] = data.get(1).get(1).split("_");
            Map<String,List<String>> queryParam = new HashMap<>();
            queryParam.put("id", Arrays.asList(queryValues[0],queryValues[1]));
            helperObj.setQueryParamHeader(queryParam);
            helperObj.executeAPIMethod("GET","/objects");
        }

    }

    @When("I am getting Status Code")
    public void i_am_getting_status_code(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        Assert.assertEquals(helperObj.getStatusCode(),Integer.parseInt(data.get(1).get(0)));
    }

    @Then("Response should have Expected Result")
    public void response_should_have_expected_result(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Assert.assertEquals(helperObj.getResponseValue("product","productName"),data.get(0).get("ExpectedResult"));
    }

    @Then("Response Array should have Expected Result")
    public void response_array_should_have_expected_result(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        Assert.assertEquals(helperObj.getResponseValue("product","productNameFirst"),data.get(1).get(0));
        Assert.assertEquals(helperObj.getResponseValue("product","productNameSecond"),data.get(1).get(1));
    }
}
