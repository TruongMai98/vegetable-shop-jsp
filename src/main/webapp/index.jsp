<%@ page import="com.tm.store.configuratiuion.DataConnection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Shopping Cart</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<h1>${sessionScope.user}</h1>

<%@include file="includes/footer.jsp" %>
</body>
</html>