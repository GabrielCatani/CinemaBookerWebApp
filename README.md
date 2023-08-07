# CinemaBookingWebApp
    
Project to study on how to user Spring Framework components
with Servlets (Filters, Listeners), JSP. It's a first stage of a bigger project idea, that for now, 
of a bigger project, that will implement a fully functional Cinema Ticket booking web page.

### Description

The Application consist of an Welcoming Page, that allows users to Sign Up, and to log in into the system.
Once Logged in, the user can upload Avatar images, check previously uploaded images, and have it's latest 
uploaded image displayed along side user information (email and name), and alos a list of the last 4 logins.

### Architecture

The application consist of specialized Servlets, for each user interaction (Sign Up, Log in, Upload Image). 
The application uses the Spring Framework, with dedicated services + respositories for each model necessary 
for the project logic.

For this first part of the project, we have the `User` and `UserLoggingInfo` models.
Each model, has a dedicated repository (Ex: `UserRepoImpl`), and a service (Ex: `UserServiceImpl`). Each service, 
interacts with each repo. implementing specific business logic to each model.

For Spring Framework, we have a config File `CinemaBookingWebAppConfig`, that declares all Beans needed by any component
of the application. Beans declared here, allow any class to take advantage of Dependency Injection, that simplifies
usage of other classes inside new classes.

For each route of the app, we have a servlet, that handles differente HTTP methods
and actions needed to be performed on the server side. Each Servlet has a correspondant 
Filter, that make accessory tasks (authenticate user, check current session info, etc.)

Finally, we have `SpringContextLoaderListener`, that we use to expose Spring Beans to all servlets, 
and declare any information needed globally, like the path to store User uploaded images.


### Technologies used

- Database: Postgres
- HikariCP: DBConnection trough JDBC
- Backend: Java (JDK 17) + Jakarta EE 9
- WebServer: Tomcat 10.xx
- Front: simples JSP + TagLib 
- Testes: JUnit 5
