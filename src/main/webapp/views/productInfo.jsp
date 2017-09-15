<%-- 
    Document   : productInfo
    Created on : Sep 7, 2017, 10:19:39 PM
    Author     : Vincent
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<table>
    <tr>
        <th colspan="2">
            <h1>Product Information</h1>
        </th>
    </tr>
    <c:if test="${!empty messages}">
        <tr>
            <td class="info" colspan="2">
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
        <td class="alignRight">
            <label for="code">Product Code:</label>
        </td>
        <td>
            <span class="output" id="code">${productBean.code}</span><br />
        </td>
    </tr>                    
    <tr>
        <td class="alignRight">
            <label for="description">Title:</label>
        </td>
        <td>
            <span class="output" id="description">${productBean.description}</span><br />
        </td>
    </tr>                    
    <tr>
        <td class="alignRight">
            <label for="price">Price:</label>
        </td>
        <td>
            <span class="output" id="price"><fmt:formatNumber value="${productBean.price}" type="currency"/></span><br />
        </td>
    </tr>                    
    <tr>
        <td class="alignRight">
            <label for="releaseDate">Release Date:</label>
        </td>
        <td>
            <span class="output" id="releaseDate"><tags:localDate date="${productBean.releaseDate}" /></span><br />
        </td>
    </tr>
    <tr>
        <td class="alignRight">
            <label for="yearsReleased">Years in Release:</label>
        </td>
        <td>
            <c:choose>
                <c:when test="${productBean.yearsReleased >= 0}">
                    <span class="output" id="yearsReleased">${productBean.yearsReleased}</span><br />
                </c:when>
                <c:when test="${productBean.yearsReleased == -1}">
                    <span class="output" id="yearsReleased">Future Release</span><br />
                </c:when>
                <c:otherwise>
                    <span class="output" id="yearsReleased">No Release Date</span><br />
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
