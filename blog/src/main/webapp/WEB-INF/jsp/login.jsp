<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="css/header.css"/>

<html lang="en">
<%@ include file="components/header.jsp" %>  

<body>

<form action="login" method="post">
        Email: <input type="text" name="email">
    <br />
         Password: <input type="password" name="password" />
    <input type="submit" value="Submit" />
    </form>
</body>
<%@ include file="components/footer.jsp" %>  
</html>