<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>

<h1>${title} - Upvotes ${tempUpvoteCount} - Comments Count: ${listOfCommentsSize}</h1>
<h4>By ${authorName}</h4>
<Br/>
<p>${content}</p>
<Br/>

<a href="/vote/up/${blogId}">Upvote</a>
<Br/>
<a href="/vote/down/${blogId}">Downvote</a>
<Br/>
<a href="/delete/${blogId}">Delete</a>
<Br/>
<a href="/edit/${blogId}">Edit</a>
<Br/>

<h4>Author can be contacted at <a href="mailto:${authorEmail}">${authorEmail}</a></h4>
<Br/>
<h1>Comments</h1>
<Br/>



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

</body>
<%@ include file="components/footer.jsp" %>  

</html>