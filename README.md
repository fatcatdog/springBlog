By Jacob Duchen

This project, "CityBlog", is a blog system built with SpringBoot, SpringMVC, Spring Security, JDBC, JPA, MySql/Oracle, and the views are JSPs. The blog system has full CRUD functionality, upvotes can be created and deleted, but comments currently can only be created (this is still a work in progress).

I am in the process of removing JPA from the security configuration as JDBC is required by assignment. JPA is used to configure Spring Security settings. JDBC is used for the rest of the projects CRUD functionality.

I used mySql during the development of the project, and plan on replacing it with Oracle as requested by assignment.

Here is an image of our current schema:
![mysql schema of database](images/dbSchemaImage.png)

Here are some other screenshots of the projects in its crude state:
![image of home screen after login](images/home.png)
Home view
![login screenshot](images/login.png)
Login
![signup screenshot](images/signup.png)
Signup
![view a blog image](images/blogWithComments.png)
View of a Blog
