Feature: Update Product

  @put
  Scenario Outline: Updating Product by using PUT Method
    #Create the Product first
    Given I am creating a new Product
      | ExpectedResult       |
      | Apple MacBook Pro 18 |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    #Update the same product which created above
    Given I am updating a Product
      | ExpectedResult   |
      | <ExpectedResult> |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Then Response should have Expected Result
      | ExpectedResult   |
      | <ExpectedResult> |

    Examples:
      | StatusCode | ExpectedResult      |
      | 200        | Apple iPhone Max 16 |

  @patch
  Scenario Outline: Partially Updating Product by using PATCH Method
    #Create the Product first
    Given I am creating a new Product
      | ExpectedResult       |
      | Apple MacBook Pro 18 |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    #Partially Update the same product which created above
    Given I am partially updating a Product
      | ExpectedResult   |
      | <ExpectedResult> |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Then Response should have Expected Result
      | ExpectedResult   |
      | <ExpectedResult> |

    Examples:
      | StatusCode | ExpectedResult      |
      | 200        | Apple Tablet Pro 16 |