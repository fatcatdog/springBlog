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

<h1>${title}</h1>
<p>Upvotes: ${tempUpvoteCount}</p>
<h4>By ${authorName}</h4>

<c:url var="commit_update_blog_url"  value="/updateBlog"></c:url>
<form action="${commit_update_blog_url}" method="post">
<input type="hidden" name="id" value="${blogId}" />
		<p>Current State of Title Which You Can Update</p>
        <input type="text" name="title" size="50" value="${title}"/>
        <br>
      <p>Previous Content</p>
      ${content}
      <p>(Please note that you have to copy and paste you old content into new content because Jacob hasnt fixed this problem yet)</p>
      <p>Your new Content<br>
       	<textarea id="content" name="content" rows="5" cols="30"></textarea>
      </p>
      
	<input type="submit" value="Commit Edit Of Your Blog" />
</form>       






</div>
</body>
<%@ include file="components/footer.jsp" %>  

</html>