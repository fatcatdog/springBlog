<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>

<h1>Create Blog</h1>

<form action="saveBlog" method="post">
        <input type="hidden" name="*{id}"/>

        Title: <input type="text" name="title">
    <br />
         Content: <input type="text" name="content" />
   <br />
         
    <input type="submit" value="Publish" />
</form>
    <form action="signup" method="GET">
    <input type="submit" value="Sign Up" />
    </form>

</body>

</html>