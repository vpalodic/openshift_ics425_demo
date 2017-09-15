<%-- 
    Document   : localDate
    Created on : Sep 14, 2017, 10:58:45 PM
    Author     : Vincent

    Original idea came from this StackOverflow post: 
    https://stackoverflow.com/questions/30230517/taglib-to-display-java-time-localdate-formatted
--%>

<%@tag body-content="empty" trimDirectiveWhitespaces="true" description="Format Java 8's LocalDate object" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="date" required="true" type="java.time.LocalDate" %>
<%@attribute name="pattern" required="false" type="java.lang.String" %>

<%-- any content can be specified here e.g.: --%>
<c:if test="${empty pattern}">
    <c:set var="pattern" value="MM/dd/yyyy"/>
</c:if>

<fmt:parseDate value="${date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
<fmt:formatDate value="${parsedDate}" type="date" pattern="${pattern}"/>