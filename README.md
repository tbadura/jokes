# jokes

This is web application that displays a random joke from The Internet Chuck
Norris Database (http://www.icndb.com/). This web application was written
in Java and uses the Spring Framework.

This project was created using IntelliJ IDEA (IDE). Load project by opening
pom.xml file. To build the project without the IDE, go to the directory where
pom.xml is located and run: mvn package

The war file created by maven has been tested on Tomcat versions 7 and 8. 

Logging to sqllite database is currently disabled. If you want to try to 
deploy and run with database logging for each request, you can uncomment out
the line of code that inserts to the database in HomeController.java

If you want to configure to use your sqlite database, change the datasource 
connection string for url property value in source code path
src/main/webapp/WEB-INF/spring/root-context.xml and
src/main/resources/testApplicationContext.xml. Then execute the following SQL
in your database:

create table if not exists ACTIVITYLOG (
         id INTEGER PRIMARY KEY AUTOINCREMENT,
         activity_date TIMESTAMP NOT NULL,
         ip_address text NOT NULL
        );











