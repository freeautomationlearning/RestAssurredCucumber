/*
 * Copyright (c) 2024. Created by Chirag Singh.
 */
package com.freeautomation.reports;

import com.freeautomation.base.APIFactoryImplementation;
import com.freeautomation.utils.Constants;
import io.cucumber.java.*;

public class Hooks {

    private static APIFactoryImplementation helperObject;

    @BeforeAll
    public static void before_all()
    {
        if(helperObject==null)
        {
            helperObject = new APIFactoryImplementation();
            Boolean isMockEnabled = Boolean.valueOf(helperObject.getJSONValue(Constants.configFilePath,"isMockEnable"));
            if(isMockEnabled)
            {
                if(helperObject.getWireMockServer()==null){
                    helperObject.invokeWireMockServer();
                    String wireMockBaseUri = "http://"+helperObject.getJSONValue(Constants.configFilePath,"mockHost")+":"+helperObject.getJSONValue(Constants.configFilePath,"mockPort");
                    helperObject.setWireMockURI(wireMockBaseUri);
                }

            }

        }
    }

    @Before
    public  void setup(Scenario scenario)
    {
        if(helperObject!=null)
        {
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
    @AfterAll
    public static void after_all()
    {
        helperObject.closeWireMockServer();
    }
}
