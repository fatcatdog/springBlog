<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
<style>
body {
    color: maroon;
	 margin: auto;
    width: 75%;
    border: 5px solid black;
    padding: 10px;
    text-align: center;
  	padding: 20px;
	font-family: Arial, Helvetica, sans-serif;

    }
</style>
</head>

<h1>BlogCity</h1>

<body>
<div>

<h1>Sign Up</h1>
	<form action="signup" method="post">
        First Name: <input type="text" name="firstname">
        <br />
                         <br />
        
        Last Name: <input type="text" name="lastname">
	    <br />
	                     <br />
	    
        Email: <input type="text" name="email">
    	<br />
    	                 <br />
    	
         Password: <input type="password" name="password" />
         <br />
                          <br />
         
          Confirm Password: <input type="password" name="confirmPassword" />
         <br />
                          <br />
         
    <input type="submit" value="Register" />
	</form>
	                 <br />
	
<c:url value="/home" var="homeLink"></c:url>
<a href="${homeLink}">Login</a>
<p>${msg}</p>

</div>
</body>
         <br />
         <br />
<div>
<h1>BlogCity</h1>
<h4>Created by Jacob Duchen</h4>
<p><a href="mailto:duchenjacob@gmail.com">Feedback</a> <c:url value="/home" var="homeLink"></c:url>
<a href="${homeLink}">Login</a></p>
</div>
</html>