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
<h1>Something went wrong</h1>
<p>Sorry about that. Please <a href="mailto:duchenjacob@gmail.com">tell the developer </a> what went wrong, <c:url value="/home" var="homeLink"></c:url><a href="${homeLink}">try login, or just try again</a>.</p>
<p><a href="mailto:duchenjacob@gmail.com">Feedback</a> which is greatly appreciated can be given to us <a href="mailto:duchenjacob@gmail.com">here</a>.</p>

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