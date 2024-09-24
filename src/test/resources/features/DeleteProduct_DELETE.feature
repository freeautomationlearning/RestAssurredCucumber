Feature: Delete Product

  @delete
  Scenario Outline: Deleting Product by using DELETE Method

    Given I am creating a new Product
      | ExpectedResult       |
      | Apple MacBook Pro 18 |

    When I am getting Status Code
      | StatusCode |
      | 200        |

#Deleting the same product which created above

    Given I am deleting a Product

    When I am getting Status Code
      | StatusCode |
      | 200        |

    Given I am executing GET method request
      | Request_Type | Param_Value  |
      | PathParam    | AutoGenerate |

    Then I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Examples:
      | StatusCode |
      | 404        |