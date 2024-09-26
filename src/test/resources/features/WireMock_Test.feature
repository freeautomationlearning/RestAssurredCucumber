Feature: Mock Product

  @wiremock_requests
  Scenario Outline: Different requests for mocking Products

    Given I am mocking different request
      | Method_Type   | Request_Value   | Response_Value   |
      | <Method_Type> | <Request_Value> | <Response_Value> |

    And I am executing mock method request
      | Method_Type   | Request_Value   |
      | <Method_Type> | <Request_Value> |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Then Response should have Expected Result
      | ExpectedResult   |
      | <ExpectedResult> |

    Examples:
      | Method_Type | Request_Value | StatusCode | ExpectedResult       | Response_Value |
      | GET         | 1             | 200        | Apple MacBook Pro 16 | getResponse    |
      | POST        | postRequest   | 201        | Apple iPhone Pro 16 | postResponse   |