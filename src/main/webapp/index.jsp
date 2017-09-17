<%-- 
    Document   : index
    Created on : Sep 8, 2017, 2:43:19 AM
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />
<c:choose>
    <c:when test="${param.action == 'add'}">
        <c:redirect url="/views/productEntry.jsp" />
    </c:when>
    <c:when test="${param.action == 'view'}">
        <c:redirect url="/views/productInfo.jsp" />
    </c:when>
    <c:when test="${param.action == 'home'}">
        <c:redirect url="/views/productHome.jsp" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/views/productEntry.jsp" />
    </c:otherwise>
</c:choose>
<c:import url="/includes/footer.jsp" />
