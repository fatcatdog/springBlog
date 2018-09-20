<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="en">

<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">

</head>
<%@ include file="components/header.jsp" %>  
<body>
<div class="mainContent">

<h1>Are you sure you want to delete this blog?</h1>

<h2>Title: ${title}</h2>
<h2>Upvotes: ${tempUpvoteCount}</h2>
<h2>Comments: ${listOfCommentsSize}</h2>

<h4>By ${authorName}</h4>
<Br/>

<c:url var="delete_action_url"  value="/deleteTheBlog" />
<form action="${delete_action_url}" method="post">
    <input type="hidden" name="id" value="${id}" />
    <input type="submit" value="Delete" />
</form>

<c:url value="/home" var="homeLink"></c:url>
<a href="${homeLink}">Home</a>

<p>${content}</p>

<h4>Author can be contacted at <a href="mailto:${authorEmail}">${authorEmail}</a></h4>
</div>
</body>
<%@ include file="components/footer.jsp" %>  

</html>