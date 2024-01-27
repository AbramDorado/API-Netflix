# CMSC121-Exam-Netflix
This is Our Final exam in CMSC 121. We made a system API that acts like a netflix streaming services. This was done in the second semester of my second year. 

Springboot Setup:
1. Go to Spring Initializr https://start.spring.io/
2. Fill up project details maven, java, 3.0.10(or higher), rename artifact to "demo.springboot", Jar, 11
3. Add dependencies (include Spring Web, Spring Data JPA, PostgreSQL Driver, and Spring Boot Dev Tools) 
4. Generate 
5. Unzip file -> and place to user folder for safe keeping
6. Open Eclipse 
7. Import File as Maven/Gradle project
    //File > Import > Maven > Existing maven projects 
    //browse click demo.springboot extracted folder
    //Punta sa src > Main > java > Application tapos run 
    //The SPRING will come out in the console

8. Wait for the dependencies to install 

9. Setup properties for DB, go to > applications.properties file in the folder in eclipse
/* 
    spring.main.banner-mode=off
    logging.level.org.springframework=ERROR
    spring.jpa.hibernate.ddl-auto=none

    # Information below are needed for connecting to DB server in this case PostgreSQL
    spring.datasource.initialization-mode=always
    spring.datasource.platform=postgres
    spring.jpa.properties.hibernate.default_schema = springsample
    spring.datasource.url=jdbc:postgresql://localhost:5432/
    spring.datasource.username=postgres
    spring.datasource.password=abrampostgres
    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
*/
    //copy  paste this to application.properties
    //change the password to your own password (abrampostgres)
    //change the schema to the name of your database (ex: springsample)
    //in database this is under postgres -> schema -> springsample

10. Type in the browser: http://localhost:8080 and a Whitelabel error page will appear
(this setup is for springboot purposes only)

Database Setup:
1. DL pgadmin 4
2. DL postgresql

Postman Setup:
1. DL Postman
2. Login my account, the localhost things are save there in hello

Program Setup:
1. Extract the package folder then open folder in IDE
2. Open folder in eclipse
4. Copy paste the sql query to pgadmin to the postgresql data base
    -run the sql queries
3. Run the DemoApplication.java
4. Run the postman queries
5. Done