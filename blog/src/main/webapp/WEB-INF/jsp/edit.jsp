<!DOCTYPE html>
<link href="css/everythingelse.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link href="css/footer.css" rel="stylesheet">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>

<h1>${title} - ${tempUpvoteCount}</h1>
<h4>By ${authorName}</h4>
<Br/>
<Br/>

<c:url var="commit_update_blog_url"  value="/updateBlog" />
<form action="${commit_update_blog_url}" method="post">
    <input type="hidden" name="id" value="${blogId}" />

	 <p>Title<br>
        <input type="text" name="title" size="50" value="${title}"/>
      </p>
      <p>Content<br>
        <input type="text" name="content" size="200" value="${content}"/>
      </p>
      
	<input type="submit" value="Commit Edit Of Your Blog" />
</form>       







</body>
<%@ include file="components/footer.jsp" %>  

</html>