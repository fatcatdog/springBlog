By Jacob Duchen

This project, "CityBlog", is a blog system built with SpringBoot, SpringMVC, Spring Security, JDBC, JPA, MySql/Oracle, and the views are Thymeleaf/JSPs. The blog aspect of the web app has full CRUD functionality, upvotes can be created and deleted, but comments currently can only be created (this is still a work in progress). As I am currently learning Spring, I experimented getting functionality working in different technologies (Oracle and mySql, JPA and JDBC, JSP and Thymeleaf)

Things that I would still like to get done:

-The UX and CSS is very poor and needs to be updated

-I am in the process of removing JPA from the security configuration as JDBC is wanted. JPA is used to configure Spring Security and datasource currently. JDBC is used for the rest of the projects CRUD functionality.

-I used mySql during the development of the project and plan on replacing it with Oracle.

-Comment delete functionality

-Comment to Comment relationship functionality

Here is an image of our current schema:
![mysql schema of database](images/dbSchemaImage.png)

Here are some other screenshots of the projects in its crude state:
![image of home screen after login](images/home.png)

![login screenshot](images/login.png)

![signup screenshot](images/signup.png)

![view a blog image](images/blogWithComments.png)
