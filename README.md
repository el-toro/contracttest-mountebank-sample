Sample project for the use of [Mountebank]("http://www.mbtest.org/")

- Spring Boot application providing contact information
- other Spring Boot application consuming contact information via synchronous HTTP call
- Mountebank is used as test double for contract testing

Steps:

- Step 1: [install and start Mountebank](http://www.mbtest.org/docs/gettingStarted)
- Step 2: configure/start the Provider Spring Boot app either by 
    - Run Configuration of your IDE or 
    - Command line: 
      - `./gradlew :provider:clean :provider:build` 
      - `java -Dspring.profiles.active=contract-test-mode -jar provider/build/libs/provider-1.0-SNAPSHOT.jar`
- Step 3: run the tests in the consumer project either by
    - Test runner of your IDE
    - Command line: `./gradlew :consumer:clean :consumer:test`  
- Step 4: explore behaviour after changing consumer expectations and/or provider response
