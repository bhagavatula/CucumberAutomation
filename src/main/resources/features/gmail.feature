@BOFAZELLTRANSTESTS
Feature: Launching the Banking Application

#  Background:
#    Given user launching the gmail application
#    And user enter the login credentials
#    When User click on login button
#    Then User navigates to home page
#    Given user launching the grantsPayment application
#    And user eneter the PaymentUserRole user name
#    And user eneter the PaymentUserRole user password
#    When user click on signinbutton
#    Then user validate the grantspayments page displayed
#

  @TestPositve  @SmkoeTest @RegressionTest
  Scenario: login to the gmail page
    Given user launching the gmail application
    And user enter the user name and click next
#    And user enter the password
#    When user click on sigininButton
#    Then user validate home page is loading with "Welcome to Gmail"
#
#
#  @TestNagitive @RegressionTest
#  Scenario: Validate the error message is throwing for invalid login credentials
#    Given user launching the yahoo application
#    And user enter the user name
#    When user click on sigininButton
#    Then user validate error message  "username/password is wrong""
#
#
##    When User click on login button
##    Then User navigates to home page
##
#
##    @drwanPayment @smoketest @regression
##    Scenario: User login to grantpayment application and create drawdown payment reqeust
##    When user input the drawndown amount on  create payment page
##    Then user validate the message on "succesful payment"
###
##
##
##  @dradownforipackPayment @smoketest @ignore
##  Scenario: User login to grantpayment application and create  payment reqeuest for dradownforipackPayment
##    Then user validate the grantspayments page displayed
##    When user input the drawndown amount on  create payment page
##    Then user validate the message on "succesful payment"
##
##
##
#  @d@Automation3 @radownforipackPayment @smoketest @ignore
#  Scenario Outline: login to differnte email servers and validate home page
#    Given user given the granspayment application  "<UrL>"
#    And user enter the user name "<username>"
#    And user enter the password "<Password>"
#    When click on signin button
#    Then user validate the home page loaded with "<Message_Validate>"
#    Then user logs out from the application
#    Examples:
#      |UrL|username|Password|Message_Validate|
#      |https://www.gmail.com|Iamuser1|password1|Welcome to page|
#      |https://www.yahoo.com|Iamuser2|password1|Welcome to page|
#      |https://www.hotmail.com|Iamuser3|password1|Welcome to page|
#      |https://www.outlook.com|Iamuser4|password1|Welcome to page|

