Sample project for the use of <a href="http://www.mbtest.org/">Mountebank</a>

- Spring Boot application providing contact information
- other Spring Boot application consuming contact information via synchronous HTTP call
- Mountebank is used as Test double for contract testing

- Step 0: install and start Mountebank (http://www.mbtest.org/docs/gettingStarted)
- Step 1: configure/start the Provider Spring Boot app
- Step 2: run the tests in the consumer project
- Step 3: try out behaviour after changing consumer expectations and/or provider response
