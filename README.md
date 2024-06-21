Don't forget to give a ⭐ if you find the project useful.

This project demonstrates the use of Selenium WebDriver with Java to automate a web Application.

Tech Stack:
1. JDK 1.17
2. Maven Build tool
3. TestNG for test runner and parallel execution
4. Allure Report and Extent Report for reporting
5. log4j for logging
6. CI/CD pipeline using Jenkins(Job and BlueOcean Pipeline)
7. Selenium Grid setup
8. Selenoid Docker grid Execution

🚀 Getting Started
🚧 Prerequisites
Before you can run this project, you must have the following software installed on your computer:

Java Development Kit (JDK) version 11 or later
Apache Maven
Google Chrome web browser (Cross browser has been implemented in this project)

🔗 Dependencies
This project uses the following dependencies:

Selenium Java version 4.15.0
TestNG version 6.14.3
org-apache-poi version 5.2.3
com.aventstack.extentreport version 5.1.0
io.qameta.allure version 2.12.0
log4j version 2.23.0

🛠️ Installation
Clone this repository to your local machine.
git clone https://github.com/TapashiRoy/UIAutomationProject-OpenCart.git
Navigate to the project directory using the command line.
Install the dependencies and run mvn clean install

🌐 Application under test
https://naveenautomationlabs.com/opencart/

Note that this website is being used for testing purposes, and I, the tester, acknowledge that I do not own or have any rights to this website. Testing activities are for demo purposes only.

👨🏼‍🔬Tests
This project contains 5 sample test classes that demonstrate how to use Selenium to interact with web pages.
All the assertions are maintained under Tests and page actions are maintained under Page classes. ElementUtil, JSUtil has been maintained for code reusablility

🧪 Test classes - 
1. AccountPageTest - Contains all the TCs for Opencart Account Page (Verifying Title, Page URL, All the links are displayed, Headers count, Footers count,search functionality, verifying dropdown etc)
2. LandingPageTest - Contains all the TCs for Opencart Landing Page (Verifying Title, Page URL, All the fields are displayed, Headers menu items, Footers menu items etc)
3. LoginPage Test -  Contains TCs for Opencart Login Page(Verifying Title, Page URL,links for navigation to other pages, login functionality)
4. ProductDetailsPageTest - Contains TCs for Opencart Product Details Page(Verifying Title, Page URL,search for different products,Add To cart functionality, )
5. RegisterPageTest - Contains TCs for Opencart Registration Page(Verifying Title, Page URL, Registration functionality, )

📝 Test Runners 
testng_sanity.xml: Includes one test case that covers the basic functionality of the web app.
testng_regression.xml: Includes set of test cases that checks for overall stability and functionality of the existing features.

🏃🏽How to run the tests
🚦 Running a test case
Navigate to the project directory using command line.
Run the following command but replace <test_case> with the name of the test case.
mvn test -Dtest=<test_case>
E.g. mvn test -Dtest=TC004_Logout
🚦 Running a test plan
Navigate to the project directory using command line.
Run the following command but replace <test_plan> with the file path of the test suite xml files.
mvn test -DsuiteXmlFiles=<test_plan>
E.g. mvn test -DsuiteXmlFiles=src/test/java/web_saucedemo/tests/testng/testplans/TP-002_Regression.xml





