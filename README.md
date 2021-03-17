# Imburse Tech Test - Tony Skinner

This is the automation tech test submission of Tony Skinner for Imburse.
This framework runs some tests against the requirements specified in the given document.

## Dependencies
- Java 8
- Gradle

## Usage
To run the tests please use an IDE or run any of the following commands from the command line:
``./gradlew regressionTests`` - Runs all tests
``./gradlew negativeTests`` - Runs only the negative scenarios
``./gradlew orderTests`` - Runs just the order endpoint tests
``./gradlew instructionTests`` - Runs just the instruction creation tests

## Implementation Notes

### Foreword
Given my previous Java experience, I have brushed up on my Java to use Cucumber JVM. I prefer Gradle to Maven hence I chose to use it. 
I have not used Java in anger since the summer of 2020, so I may be a little rusty in the code.

From previous experience I know that the official documentation on how to use Cucumber JVM with Gradle is not the best in comparison to using Maven.
On one of my previous projects I set about solving some of those challenges and wrote some Medium articles. I have taken the learnings from some of those
articles and applied them to this framework. If you are interested in reading the articles then the links are below.

https://medium.com/swlh/using-cucumber-jvm-with-gradle-and-junit-a0dc6e195c94
https://medium.com/swlh/executing-ios-and-android-appium-java-tests-in-the-same-framework-d03944633377
https://medium.com/swlh/using-java-properties-files-to-switch-between-devices-for-appium-test-runs-3f2aa145ba06
https://tjskinner1989.medium.com/using-gradle-to-switch-between-local-and-cloud-appium-java-runs-81ef4f49f9db

### Gradle Setup
The feature files have been mapped to Gradle tasks which are invoked (see usage section). All tasks contain a regex to locate the Cucumber runner, and then certain
Cucumber values are overridden using Gradle's `systemProperty` feature. The tasks have also been grouped together so that they display nicely if you run the `./gradlew tasks` command.

### Cucumber Setup
The feature files are located at ``src/test/resources/features`` and they have been split into 2 to concentrate on the 2 endpoints in question (orders and instructions).
The step definitions are located at ``src/test/java/stepdefinitions``, and the runner is located at `src/test/java/imbursetestrunner`.

Given that all requests will require a Bearer token, I have created a Before hook to handle the HMAC generation/ Bearer swap. The logic is that on first launch it will
complete this process and then store the token and expiry timestamp. If the timestamp expires we then repeat the process to recycle the tokens.

The report is available in 2 formats (html and json) and is available within the `target` folder. The report will be updated each time that one of the Gradle tasks in the Usage
section is run. The JSON output is more designed for if this was to be plugged into a CI. It is an extra one liner in the runner to add it, so I thought why not? :)

### Framework Architecture
Given that I have come from a predominant UI testing and automation background, I am sure that it is no surprise to hear that this is the first time that I have wrote proper automation for APIs.
I have therefore opted to use Rest Assured becuase it plays very nicely with Cucumber. This has been the bulk of my learning whilst finshing this tech test, and I am very happy that I now know how to 
use the basics of it :)

I like to make the tests as easy to read as possible, and I also like to modularise as much of the components as I can. To that end I have created separate packages
to handle the API interactions and utility methods. I have also created a common package to handle things such as constants and country code lookups. I couldn't find 
any decent Country or Currency code libraries which had the required ISO standards, so I wrote my own Enums instead :)

### Additional Tests
The tests which have been written mostly check for things upon response codes. I like the assertions on offer from the AssertJ library, so I have opted to use them.
If I had more time then an additional improvement which I would make to the tests would be to sift through the response bodies to see if there was any additinal verifications which we could do. 
An example would be to check that the error messages in the nested json array for failed attempts are correct etc.




