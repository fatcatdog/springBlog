<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>

<h1>Create Comment</h1>

<form action="saveComment" method="post">
        <input type="hidden" name="*{id}"/>

         Content: <input type="text" name="content" />
   <br />
         
    <input type="submit" value="Publish Comment" />
</form>
    <form action="signup" method="GET">
    <input type="submit" value="Sign Up" />
    </form>

</body>

</html>