## List of technologies integrated with rationale
1) Maven
Java library dependency management and building source code. Maven is the most popular build tool with Gradle. Just for back-end, Maven is enough.
2) Spring Boot
Easy to build production-ready Spring application integrated with Spring Web/Data/Security. It include embedded WAS and make the executable jar file. Don’t need to package as war and deploy to the specific WAS. And also it is really easy to deploy Cloud environment. There are lots of PaaS platform which let developers deploy their Spring Boot web application, easily.
3) Spring Web
Easy to build Rest API server with a few code.
4) Spring Data JPA
Don’t need to write SQL statements inside Java code. Using ORM(Object-Relation Mapping) concept between Java entity class and database table.
5) Spring Security
Easy to add authentication feature and ssl configuration.
6) Spring Test
Doing Spring module unit test for loading Spring context with same configuration.

## List of design patterns and principle used with rational
1) TDD(Test-driven development)
Writing unit test code first and then write production code. It is help to design modules and separate concern between layers. Writing test data and try to run all test case run independently, it drive to write loosed coupling cde
2) DI(Dependency Inversion) & AoP(Aspect-oriented programming)
Spring context will create all object when spring context is loading. Don’t need to make own object using `new` keywords. This can help developers write cross cutting logic for all or some of codes like logging, exception processing, etc.
3) DRY(Don’t repeat yourself)
Of course, should not repeat same code at several places as possible as you can.
4) OCP(Open-closed principle)
Need to open for extension but closed for modification, Define interface first and write implementation code.

## Instructions to install and configure prerequisites or dependencies
### For Windows user
1) Internet should be able to access
2) JDK 8 should be installed and add JVM\bin folder in ‘path’ on your system variable (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[notice] It is fully tested with JDK 1.8.0_91
3) Open command prompt and type `javac -version', check your JDK 8 is in the path, correctly

## Instructions to create and initialize the database
This project use HSQL, embedded in-memory DB, so, don’t need to install any database. When you run this app, database will be initialized by itself using `techtrial\src\main\resources\data.sql`.

## Assumptions you have made
1) This project’s goal is building back-end framework, so UI is not validated from user, before. Just reference.
2) One CRUD process will be the sample code for junior developers.
3) Should use SSL but right now, turn of b/c of certificate is not exist in private laptop.
4) This application need to deploy to cloud easily. Building cloud-native application.

## Requirements that you have not covered in your implementation
1) OAuth (Spring Sequrity: https://spring.io/guides/tutorials/spring-boot-oauth2/)
2) SMTP (spring boot + JavaMail:  http://docs.spring.io/spring/docs/4.2.7.RELEASE/spring-framework-reference/htmlsingle/#mail)
3) PDF creation(Spring with wkhtmltopdf: http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-creating-pdf-documents-with-wkhtmltopdf/)
4) Payment(Consider using commercial product, stripe: https://stripe.com/us/features)
5) Features (Reservation, Online checkin/cancellation)

## Instructions to configure and prepare the source code to build and run properly
1) Unzip Chris_Cho_CA_Air2.zip file on convenient directory
2) Open command prompt from 'techtrial' directory
3) Insert `bin\mvnw clean package` on command prompt. This will do below step, automatically.
    Step1. Download all libraries on your laptop’s maven repository once
    Step2. Compile java source code to class
    Step3. Do all unit tests (Repository Test & APIController Test)
    Step4. If all tests passed, package as a executable jar file in ‘target’ folder
4) Insert `java -jar target\techtrial-0.0.1-SNAPSHOT.jar` for running spring-boot application with 8080 port
5) Open web browser and visit `http://localhost:8080`
6) Login information is below (from techtrial\src\main\resources\application.yml)
    ID: isi.cho@gmail.com
    PWD: 1234

## Issues you have faced while completing the assignment, if any
- It is not easy to handle all within couple of days. The architecture what I decided was too huge to do in 3 days
- I couldn't handle front-end tech skills. My understanding of front-end is not same level of Java, but also have lots of experience. If I have more time, I can write all front-end unit test code and writing integration test with headless browser(like phantomjs), and make it run automatically within CI/CD environment

## Any feedback you wish to give for improving the assignment and our process
- I apply ‘Chief Software Architect - JAVA’ and was little bit confused I need to write UI code. If you write description little bit more in details, it will help to set exact scope.
- I like this trial project. It is not possible to do this trial w/o specific experience. Much better than algorithms coding test with time/space complexity.

## Unit Test Type and test data
1) Rest Controller test using Fake repository
2) Repository unit test using HSQL

