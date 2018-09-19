<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

</head>
<h1><a href="/home">BlogCity</a></h1>

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
    <p>${msg}</p>
        </div>
</body>
         <br />
<div>
<h1><a href="/home">BlogCity</a></h1>
<h4>Created by Jacob Duchen</h4>
<p><a href="mailto:duchenjacob@gmail.com">Feedback</a> <c:url value="/home" var="homeLink"></c:url>
</div>
</html>