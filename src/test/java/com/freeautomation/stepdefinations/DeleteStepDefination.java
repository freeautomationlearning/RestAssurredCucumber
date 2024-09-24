/*
 * Copyright (c) 2024. Designed by Chirag Singh
 */

package com.freeautomation.stepdefinations;

import com.freeautomation.base.APIFactoryImplementation;
import com.freeautomation.constants.VariableName;
import com.freeautomation.pojo.Data;
import com.freeautomation.pojo.Product;
import io.cucumber.java.en.Given;

public class DeleteStepDefination {

    APIFactoryImplementation helperObj;
    public DeleteStepDefination()
    {
        helperObj = new APIFactoryImplementation();
    }

    @Given("I am deleting a Product")
    public void i_am_deleting_a_product() {
        String productID = VariableName.getProductID();
        helperObj.executeAPIMethod("DELETE","/objects/"+productID);
    }
}
