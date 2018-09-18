<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>

<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
</head>

<%@ include file="components/header.jsp" %>  

<body>

    <div class="mainContent">
    <div>
  <h2 align="center">Welcome back ${userName}</h2>
    <form action="searchBlogs" method="GET">
    	<input type="text" name="searchedWords">
  		<input type="submit" value="Search Blogs" />
    </form> 
    <form action="create" method="GET">
  		<input type="submit" value="Create Blog" />
    </form>       
    <Br/>
    </div>
	  <c:forEach items="${blogs}" var="blog" varStatus="status">
	  
		<a href="/blog/${blog.id}">

	    <c:out value="${blog.title} - by ${authorNames[status.index]} - Upvotes: ${upvotes[status.index]} - Comments: ${comments[status.index]}"/><br /><br />
	  </a>
	  </c:forEach>	
     </div>
	    
  
</body>

<%@ include file="components/footer.jsp" %>  

</html>