<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>

  <h2 align="center">What is up ${userName}</span>, welcome back to BlogCity. To read, write, upvote, or comment(if jacob codes that)?</h2>
	<h3 align="center">    
	</h3>
	<center>
	
    <form action="create" method="GET">
  		<input type="submit" value="Create Blog" />
    </form>       
    </center>
    
	  <c:forEach items="${blogs}" var="blog" varStatus="status">
	  
		<a href="/blog/${blog.id}">Read</a>

	    <c:out value="${blog.title} - written by - ${authorNames[status.index]} - Upvotes: ${upvotes[status.index]}"/><br />
	  </c:forEach>	  
  
</body>
<%@ include file="components/footer.jsp" %>  

</html>