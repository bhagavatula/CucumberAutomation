$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/resources/features/gmail.feature");
formatter.feature({
  "line": 2,
  "name": "Launching the Banking Application",
  "description": "",
  "id": "launching-the-banking-application",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@BOFAZELLTRANSTESTS"
    }
  ]
});
formatter.before({
  "duration": 6707290300,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 4,
      "value": "#  Background:"
    },
    {
      "line": 5,
      "value": "#    Given user launching the gmail application"
    },
    {
      "line": 6,
      "value": "#    And user enter the login credentials"
    },
    {
      "line": 7,
      "value": "#    When User click on login button"
    },
    {
      "line": 8,
      "value": "#    Then User navigates to home page"
    },
    {
      "line": 9,
      "value": "#    Given user launching the grantsPayment application"
    },
    {
      "line": 10,
      "value": "#    And user eneter the PaymentUserRole user name"
    },
    {
      "line": 11,
      "value": "#    And user eneter the PaymentUserRole user password"
    },
    {
      "line": 12,
      "value": "#    When user click on signinbutton"
    },
    {
      "line": 13,
      "value": "#    Then user validate the grantspayments page displayed"
    },
    {
      "line": 14,
      "value": "#"
    }
  ],
  "line": 17,
  "name": "login to the gmail page",
  "description": "",
  "id": "launching-the-banking-application;login-to-the-gmail-page",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 16,
      "name": "@TestPositve"
    },
    {
      "line": 16,
      "name": "@SmkoeTest"
    },
    {
      "line": 16,
      "name": "@RegressionTest"
    }
  ]
});
formatter.step({
  "line": 18,
  "name": "user launching the gmail application",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "user enter the user name and click next",
  "keyword": "And "
});
formatter.match({
  "location": "GmailPageValidataion.user_launching_the_gmail_application()"
});
formatter.result({
  "duration": 4744191700,
  "status": "passed"
});
formatter.match({
  "location": "GmailPageValidataion.username()"
});
formatter.result({
  "duration": 867282700,
  "status": "passed"
});
formatter.after({
  "duration": 700000,
  "error_message": "java.lang.NullPointerException\r\n\tat com.myproject.cucumber.stepdefs.Hooks.tearDown(Hooks.java:62)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\r\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\r\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\r\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\r\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\r\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\r\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\r\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:69)\r\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\r\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:221)\r\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:54)\r\n",
  "status": "failed"
});
});