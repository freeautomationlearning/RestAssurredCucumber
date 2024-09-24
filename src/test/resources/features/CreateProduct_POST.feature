Feature: Create Product

  @post
  Scenario Outline: Creating Product by using POST Method

    Given I am creating a new Product
      | ExpectedResult   |
      | <ExpectedResult> |

    When I am getting Status Code
      | StatusCode   |
      | <StatusCode> |

    Then Response should have Expected Result
      | ExpectedResult   |
      | <ExpectedResult> |

    Examples:
      | StatusCode | ExpectedResult       |
      | 200        | Apple MacBook Pro 18 |