<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
<center>

<h1>Sign Up</h1>
	<form action="signup" method="post">
        First Name: <input type="text" name="firstname">
        <br />
        Last Name: <input type="text" name="lastname">
	    <br />
        Email: <input type="text" name="email">
    	<br />
         Password: <input type="password" name="password" />
         <br />
          Confirm Password: <input type="password" name="confirmpassword" />
         <br />
    <input type="submit" value="Register" />
	</form>

    <form action="login" method="GET">
    	<input type="submit" value="Login" />
    </form>

</center>

</body>

</html>