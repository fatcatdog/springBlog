<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>
<h1>Are you sure you want to delete this blog?</h1>
<h2>${title} - ${tempUpvoteCount}</h2>
<h4>By ${authorName}</h4>
<Br/>

<%-- <c:url var="commit_update_blog_url"  value="/updateBlog" />
<form action="${commit_update_blog_url}" method="post">
    <input type="hidden" name="id" value="${blogId}" /> --%>

<c:url var="delete_action_url"  value="/deleteTheBlog" />
<form action="${delete_action_url}" method="post">
    <input type="hidden" name="id" value="${id}" />
    <input type="submit" value="Delete" />
</form>

<c:url value="/home" var="homeLink"></c:url>
<a href="${homeLink}">nevermind</a>

<p>${content}</p>

<h4>Author can be contacted at <a href="mailto:${authorEmail}">${authorEmail}</a></h4>

</body>
<%@ include file="components/footer.jsp" %>  

</html>