<%-- 
    Document   : productInfo
    Created on : Sep 7, 2017, 10:19:39 PM
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="edu.metrostate.ics425.vjp071.prodmaint.model.ProductBean" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:import url="/includes/header.jsp" />
<table>
    <tr>
        <th colspan="2">
            <h1>Delete Product Confirmation</h1>
        </th>
    </tr>
    <c:if test="${!empty messages}">
        <tr>
            <td class="error" colspan="2">
                <ul>                           
                    <c:forEach var="message" items="${messages}">
                        <li>
                            <span>${message}</span>
                        </li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:if>
    <c:choose>
        <c:when test="${empty productBean}">
            <%-- Invalid Product Code--%>
            <c:set var="invalid" value="${true}"/>
        </c:when>
        <c:otherwise>
            <c:set var="invalid" value="${false}"/>
        </c:otherwise>
    </c:choose>
    <c:if test="${not invalid}">
        <tr>
            <td class="alignCenter" colspan="2">
                <span class="warning" id="warning">*** This operation cannot be undone! ***</span>
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="code">Product Code:</label>
            </td>
            <td>
                <span class="output" id="code">${productBean.code}</span>
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="description">Title:</label>
            </td>
            <td>
                <span class="output" id="description">${productBean.description}</span>
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="price">Price:</label>
            </td>
            <td>
                <span class="output" id="price"><fmt:formatNumber value="${productBean.price}" type="currency"/></span>
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="releaseDate">Release Date:</label>
            </td>
            <td>
                <span class="output" id="releaseDate"><tags:localDate date="${productBean.releaseDate}" /></span>
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                <label for="yearsReleased">Years in Release:</label>
            </td>
            <td>
                <c:choose>
                    <c:when test="${productBean.yearsReleased == ProductBean.UNSET_RELEASE_DATE}">
                        <c:set var="yearsReleased" value="No Release Date" />
                    </c:when>
                    <c:when test="${productBean.yearsReleased == ProductBean.FUTURE_RELEASE_DATE}">
                        <c:set var="yearsReleased" value="Future Release Date" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="yearsReleased" value="${productBean.yearsReleased}" />
                    </c:otherwise>
                </c:choose>
                <span class="output" id="yearsReleased">${yearsReleased}</span>
            </td>
        </tr>
        <tr>
            <td class="alignRight" colspan="2">
                <form action="product/delete" method="post">
                    <input class="submit" type="submit" value="Delete" />
                    <span class="submit"><a href="catalog">Cancel</a></span>
                    <input class="submit" type="reset" value="Reset" />
                    <input type="hidden" name="confirmed" value="true" />
                    <input type="hidden" name="code" value="${productBean.code}" />
                </form>
            </td>
        </tr>
    </c:if>
</table>
<c:import url="/includes/footer.jsp" />
