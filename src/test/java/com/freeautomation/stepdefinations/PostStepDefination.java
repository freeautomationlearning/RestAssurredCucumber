/*
 * Copyright (c) 2024. Designed by Chirag Singh
 */

package com.freeautomation.stepdefinations;

import com.freeautomation.base.APIFactoryImplementation;
import com.freeautomation.constants.VariableName;
import com.freeautomation.pojo.Data;
import com.freeautomation.pojo.Product;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

public class PostStepDefination {

    APIFactoryImplementation helperObj;
    public PostStepDefination()
    {
        helperObj = new APIFactoryImplementation();
    }

    @Given("I am creating a new Product")
    public void i_am_creating_a_new_product(DataTable dataTable) {
        List<Map<String, String>> dataTableMaps = dataTable.asMaps();
        String productName = dataTableMaps.get(0).get("ExpectedResult");

        // Create json Object by using Pojo for Request
        Product product = new Product();
        product.setName(productName);
        Data data = new Data();
        data.setYear(2024);
        data.setPrice(1849.99);
        data.setCPUmodel("Intel Core i9");
        data.setHarddisksize("1 TB");
        product.setData(data);

        helperObj.setContentType("json");
        helperObj.setRequestBody(product);
        helperObj.executeAPIMethod("POST","/objects");

        //Getting Created Product ID
        String productID = helperObj.getResponseValue("product","productID");
        VariableName.setProductID(productID);
    }
}
