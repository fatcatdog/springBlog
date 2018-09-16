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
<%@ include file="components/header.jsp" %>  

<body>
    <div>
<h5>Please Log In</h5>

<form action="login" method="post">
        Email: <input type="text" name="email">
    <br />
                 <br />
    
         Password: <input type="password" name="password" />
             <br />
                      <br />
                      <br />
         
    <input type="submit" value="Login" />
</form>
<h5>Or Sign Up</h5>

    <form action="signup" method="GET">    
    <input type="submit" value="Sign Up" />
    </form>
        </div>
                 <br />
                 <br />
                 <br />
                 <br />
</body>
<%@ include file="components/footer.jsp" %>  
</html>