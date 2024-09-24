/*
 * Copyright (c) 2024. Designed by Chirag Singh
 */

package com.freeautomation.constants;

import lombok.Getter;
import lombok.Setter;

public class VariableName {
    public static ThreadLocal<String> productID = new InheritableThreadLocal<String>();

    public static void setProductID(String productId)
    {
        productID.set(productId);
    }

    public static String getProductID()
    {
        return productID.get();
    }
}
