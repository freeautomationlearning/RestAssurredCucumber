# RestAssuredCucumber

RestAssuredCucumber is a Java-based framework for API testing that leverages the power of Cucumber for behavior-driven development (BDD) and RestAssured for making HTTP requests. This framework is designed to facilitate the testing of RESTful APIs with ease, allowing for both mock and real service interactions..

## Features
- Behavior-Driven Development (BDD): Write clear and understandable tests using Gherkin syntax.
- API Testing: Easily test various HTTP methods (GET, POST, PUT, PATCH, DELETE).
- Support Parallel Execution: Easily execute each scenario parallel with different data.
- Mock Server Support: Utilize WireMock to mock API responses for testing without relying on external services.
- Dynamic Request Handling: Set query and path parameters dynamically.
- JSON Handling: Simplified methods for reading and writing JSON using POJOs.
- Extensive Reporting: Integration with ExtentReports for detailed test reporting.

## Prerequisites
- Java 11 or higher
- Maven
- An IDE (e.g., IntelliJ IDEA, Eclipse)

## Setup

- Clone the Repository [RestAssurredCucumber](https://github.com/freeautomationlearning/RestAssurredCucumber.git) to get the code on your local machine.

```
git clone https://github.com/yourusername/RestAssurredCucumber.git
cd RestAssurredCucumber
```
- Build the Project:
```
mvn clean install
```
- Configuration :
  Update the src/test/resources/config.json file with your API base URI and other configurations as needed.

## Running Tests

You can run the tests using Maven with specific tags or run all tests:

- To run all tests:

```
mvn clean test
```
- To run tests with specific tags (e.g., for POST requests):
```
mvn clean test -Dcucumber.filter.tags="@post"
```

## Directory Structure

```
RestAssurredCucumber
│
├── src
│   ├── main
|   |   └── java
│   |      ├── com
│   |      │   └── freeautomation
│   |      │       ├── base
│   |      │       ├── reports
│   |      │       └── utils
│   └── test
│       └── java
│           ├── com
│           │   └── freeautomation
│           │       ├── constants
│           │       ├── pojo
│           │       ├── runner
│           │       └── stepdefinitions
│           └── resources
│               ├── config.json
|               ├── nodelocators
|               ├── reportconfig
|               ├── extent.properties
│               ├── wiremock
│               |    └──__files
│               |        └── <mock response files>
│               └── features
│                    └── <feature files>
|
└── pom.xml
```
## Writing Tests
- Feature Files: Define your scenarios in .feature files located in the src/test/resources/features directory.

- Step Definitions: Implement the step definitions in Java classes located in the src/test/java/com/freeautomation/stepdefinitions package.

## Mocking with WireMock

- Ensure WireMock is configured in your config.json file.

- Use the provided methods in APIFactoryImplementation to set up mock responses.

## Reporting

The framework integrates with ExtentReports. After running tests, reports will be generated in the target>>Report directory. Generate the following reports.
- HTML format report.
- PDF format report.
- Excel format report.

## 🚀 About Me
I am Full Stack Tester. For more info please go to my channel on YouTube [Free Automation Learning](https://www.youtube.com/channel/UCFs7BfAeJI6MtdqzTXdA9Og)


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://freeautomationlearning.github.io/home/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/chirag-singh-freeautomationlearning/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/freeautomation)
