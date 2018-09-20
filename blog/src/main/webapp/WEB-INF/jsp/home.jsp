<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="en">

<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR" rel="stylesheet">
</head>
<body style="background-color: #5CDB95;">
  <%@ include file="components/header.jsp" %>

  <div class="everything">
    <form action="searchBlogs" method="GET">
    	<input type="text" name="searchedWords">
    	<Br/>
    	<Br/>
  		<input class="button" type="submit" value="Search" />
    </form>
    <Br/>
    <form action="create" method="GET">
  		<input class="button" type="submit" value="Create Blog" />
    </form>
    <Br/>
    </div>
    <div class="our_list_of_links">
	  <c:forEach items="${blogs}" var="blog" varStatus="status">

		<a href="/blog/${blog.id}" style="color:white">
<span class="text_in_c_out">
	    <c:out value="${(status.index + 1)}. ${blog.title}"/>
	  </span>
	  </a>
	  <Br/>
	  <span class="text_in_c_out">
	    <c:out value="Upvotes: ${upvotes[status.index]} - Comments: ${comments[status.index]}"/>
  		</span>
  		<Br/>
 			  <span class="text_in_c_out">
 		  		<c:out value="By ${authorNames[status.index]}"/>
 		  		</span>

  		<Br/>
	    <Br/>
	  </c:forEach>
     </div>

     <%@ include file="components/footer.jsp" %>

</body>


</html>
