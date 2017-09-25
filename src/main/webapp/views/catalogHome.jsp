<%-- 
    Document   : productHome
    Created on : Sep 8, 2017, 11:32:35 AM
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.metrostate.ics425.vjp071.prodmaint.model.ProductBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:import url="/includes/header.jsp" />
<table>
    <tr>
        <th colspan="7">
            <h1>Product Maintenance</h1>
        </th>
    </tr>
    <c:if test="${!empty messages}">
        <tr>
            <td class="info" colspan="7">
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
    <tr>
        <th>
            <span>Code</span>
        </th>            
        <th>
            <span>Description</span>
        </th>            
        <th>
            <span>Release Date</span>
        </th>            
        <th>
            <span>Years Released</span>
        </th>            
        <th>
            <span>Price</span>
        </th>            
        <th colspan="2">
        </th>
    </tr>
    <c:if test="${!empty productCatalog}">
        <c:forEach var="product" items="${productCatalog}" varStatus="loop">
            <tr>
                <td>
                    ${product.code}
                </td>
                <td>
                    ${product.description}
                </td>
                <td>
                    <tags:localDate date="${product.releaseDate}" />
                </td>
                <td>
                    <c:choose>
                        <c:when test="${product.yearsReleased == ProductBean.UNSET_RELEASE_DATE}">
                            <c:set var="yearsReleased" value="No Release Date" />
                        </c:when>
                        <c:when test="${product.yearsReleased == ProductBean.FUTURE_RELEASE_DATE}">
                            <c:set var="yearsReleased" value="Future Release Date" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="yearsReleased" value="${product.yearsReleased}" />
                        </c:otherwise>
                    </c:choose>
                    <span class="output" id="yearsReleased">${yearsReleased}</span>
                </td>
                <td>
                    <fmt:formatNumber value="${product.price}" type="currency"/>
                </td>
                <td>
                    <a href="catalog/edit?code=${product.code}">Edit</a>
                </td>
                <td>
                    <a href="catalog/delete?code=${product.code}">Delete</a>
                </td>
            </tr>
        </c:forEach>        
    </c:if>
    <tr>
        <td colspan="7">
            <form action="catalog/add">
                <input class="submit" type="submit" value="Add Product" />
            </form>
        </td>
    </tr>
</table>
<c:import url="/includes/footer.jsp" />
