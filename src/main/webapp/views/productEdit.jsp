<%-- 
    Document   : productEntry
    Created on : Sep 7, 2017, 5:09:34 PM
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<c:import url="/includes/header.jsp" />
<form action="product/edit" method="post">
    <table>
        <tr>
            <th colspan="2">
                <h1>Edit Product Form</h1>
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
                <td class="alignRight">
                    <label for="code">Product Code:</label>
                </td>
                <td>
                    <span>${param.code}</span>
                    <input type="hidden" id="code" name="code" value="${!empty param.code ? param.code : productBean.code}" />
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label for="description">Title:</label>
                </td>
                <td>
                    <input class="input" type="text" id="description" name="description" value="${!empty param.description ? param.description : productBean.description}" required/>
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label for="price">Price:</label>
                </td>
                <td>
                    <input class="input" type="text" id="price" name="price" value="${!empty param.price ? param.price : productBean.price}" required/>
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label for="releaseDate">Release Date:</label>
                </td>
                <td>
                    <input class="input" type="date" id="releaseDate" name="releaseDate" value="${!empty param.releaseDate ? param.releaseDate : productBean.releaseDate}"/>
                </td>
            </tr>
            <tr>
                <td class="alignRight" colspan="2">
                    <input class="submit" type="submit" value="Update" />
                    <span class="submit"><a href="catalog">Cancel</a></span>
                    <input class="submit" type="reset" value="Reset" />
                </td>
            </tr>
        </c:if>
    </table>
</form>
<c:import url="/includes/footer.jsp" />
