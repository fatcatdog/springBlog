<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
</head>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>
    <div class="mainContent">

<h1>Create Blog</h1>

<form action="saveBlog" method="post">
        <input type="hidden" name="*{id}"/>

        Title: <input type="text" name="title">
    <br />
        Content:<textarea id="content" name="content" rows="5" cols="30"></textarea>
         
   <br />
         
    <input type="submit" value="Publish" />
</form>
    <form action="signup" method="GET">
    <input type="submit" value="Sign Up" />
    </form>
</div>
</body>
<%@ include file="components/footer.jsp" %>  

</html>