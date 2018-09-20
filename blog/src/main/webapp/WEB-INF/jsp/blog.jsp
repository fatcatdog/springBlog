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
<body style="background-color: #5CDB95">
	<div class="everything">
	<%@ include file="components/header.jsp" %>

<div class="blog_header">
<h1>${title}</h1>
<p>By ${authorName}</p>
<p>Comments: ${listOfCommentsSize} | Upvotes: ${tempUpvoteCount}</p>
<p><a href="/vote/up/${blogId}" style="color:white">Upvote</a> <a href="/vote/down/${blogId}" style="color:white">Downvote</a>

<c:if test="${crudRights}">
<a href="/delete/${blogId}" style="color:white">Delete</a> <a href="/edit/${blogId}" style="color:white">Edit</a>
</c:if>
</p>

</div>
				<div class="blog_style" style="text-align:center">

	    <c:if test="${content.length() <= 65}">
				<!-- <div class="blog_style" style="text-align:center"> -->
				<p>Content:</p>
				<p>${content}</p>
			<!-- </div> -->
			</c:if>
	    <c:if test="${content.length() > 65}">
				<!-- <div class="blog_style"> -->
					<p>${content}</p>
<!-- 				</div>  -->
			</c:if>
				</div>

<div class="comment_section">
<h1 class="blog_header">Comments</h1>
<p>${commentListEmpty}</p>
	  <c:forEach items="${coms}" var="comment" varStatus="status">
			<span class="text_in_c_out">
	    <c:out value="${comment.content} - Written by ${ourCommentAuthors[status.index]}"></c:out>
			</span>
			<c:if test="${(comment.author_id == currentUserId)}">
			<c:url var="delete_comment_url"  value="/deleteComment" />
		<form action="${delete_comment_url}" method="post">
		    <input type="hidden" name="id" value="${comment.id}" />
		    <input class="button" type="submit" value="Delete Comment" />
		</form>
		</c:if>
		<c:if test="${!(comment.author_id == currentUserId)}">
	    <Br/>
	    </c:if>
	    <Br/>
	  </c:forEach>
		<h1 class="blog_header">What do you think?</h1><br/>
<c:url var="save_comment_url"  value="/saveComment/${blogId}" />
<form action="${save_comment_url}" method="post">
       <textarea id="content" name="content" rows="5" cols="30"></textarea>
			 <br/>
			 <br/>
    <input class="button" type="submit" value="Publish Comment" />
</form>
<Br/>
<p class="everything">Author can be contacted at: <a href="mailto:${authorEmail}" style="color:white">${authorEmail}</a></p>
<p class="everything">Currently Signed in as ${currentUserEmail}</p>
<Br/>
</div>
</div>
<%@ include file="components/footer.jsp" %>
</body>

</html>
