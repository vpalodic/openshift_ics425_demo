<%-- 
    Document   : index
    Created on : Sep 8, 2017, 2:43:19 AM
    Author     : Vincent
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<jsp:include page="/includes/header.jsp" />
<%
    String action = (String) request.getParameter("action");

    if (action != null && action.equals("view")) {
%>
    <c:import url="/views/productInfo.jsp" />
<% } else if (action != null && action.equals("add")) { %>
    <c:import url="/views/productEntry.jsp" />
<% } else { %>
    <c:import url="/views/productHome.jsp" />
<% } %>
<jsp:include page="/includes/footer.jsp" />
