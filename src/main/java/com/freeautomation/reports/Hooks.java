/*
 * Copyright (c) 2024. Created by Chirag Singh.
 */
package com.freeautomation.reports;

import com.freeautomation.base.APIFactoryImplementation;
import com.freeautomation.utils.Constants;
import io.cucumber.java.*;

public class Hooks {

    private APIFactoryImplementation helperObject;

     @Before
    public  void setup(Scenario scenario)
    {
        if(helperObject==null)
        {
            helperObject = new APIFactoryImplementation();
            String baseURI =  helperObject.getJSONValue(Constants.configFilePath,"baseUri");
            helperObject.invokeAPI();
            helperObject.getRequest().baseUri(baseURI);
            helperObject.setLog(scenario);
        }
    }


    @After
    public void tearDown(Scenario scenario)
    {
        helperObject.releaseRequest();
    }
}
