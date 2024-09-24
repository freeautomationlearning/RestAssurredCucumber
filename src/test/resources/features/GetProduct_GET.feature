Feature: Get Product

  @get
  Scenario Outline: GET request for Path Param for Validating Products

    Given I am executing GET method request
      | Request_Type   | Param_Value   |
      | <Request_Type> | <Param_Value> |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Then Response should have Expected Result
      | ExpectedResult   |
      | <ExpectedResult> |

    Examples:
      | Request_Type | Param_Value | StatusCode | ExpectedResult          |
      | PathParam    | 1           | 200        | Google Pixel 6 Pro      |
      | PathParam    | 3           | 200        | Apple iPhone 12 Pro Max |

  @get
  Scenario Outline: GET request for Query Param for Validating Products

    Given I am executing GET method request
      | Request_Type   | Query_Value   |
      | <Request_Type> | <Query_Value> |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Then Response Array should have Expected Result
      | ExpectedResult   | ExpectedResult1   |
      | <ExpectedResult> | <ExpectedResult1> |

    Examples:
      | Request_Type | Query_Value | StatusCode | ExpectedResult     | ExpectedResult1         |
      | QueryParam   | 1_3         | 200        | Google Pixel 6 Pro | Apple iPhone 12 Pro Max |
      | QueryParam   | 1_5         | 200        | Google Pixel 6 Pro | Apple iPhone 12 Pro Max |