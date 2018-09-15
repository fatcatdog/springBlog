<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>

<h1>${title} - ${tempUpvoteCount}</h1>
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

</body>
<%@ include file="components/footer.jsp" %>  

</html>