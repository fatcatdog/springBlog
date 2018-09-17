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

<h1>${title}</h1> 
<p>Comments: ${listOfCommentsSize}</p>
<p>Upvotes: ${tempUpvoteCount}</p>
<p>By ${authorName}</p>
<p><a href="/vote/up/${blogId}">Upvote</a> <a href="/vote/down/${blogId}">Downvote</a> <a href="/delete/${blogId}">Delete</a> <a href="/edit/${blogId}">Edit</a>
</p>
<p>Content:</p>
<p>${content}</p>

<h4>Author can be contacted at: <a href="mailto:${authorEmail}">${authorEmail}</a></h4>
<h1>Comments</h1>
<h4>${commentListEmpty}</h4>
	  <c:forEach items="${comments}" var="comment" varStatus="status">
	  
	    <c:out value="${comment} - By ${ourCommentAuthors[status.index]}" />
	    <Br/>
	    
	    
	  </c:forEach>	  

<Br/>

<h1>Create Comment</h1>


<c:url var="save_comment_url"  value="/saveComment/${blogId}" />
<form action="${save_comment_url}" method="post">
      <p>Comment Content<br>
       <input type="text" name="content">
      </p>
    <input type="submit" value="Publish Comment" />
</form>
<Br/>
<h3>Currently Signed in as ${currentUserEmail}</h3> 
<Br/>
</div>
</div>
</body>
<%@ include file="components/footer.jsp" %>  

</html>