<%-- 
    Document   : productEntry
    Created on : Sep 7, 2017, 5:09:34 PM
    Author     : Vincent
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<form action="product" method="get">
    <table>
        <tr>
            <th colspan="2">
                <h1>Product Entry</h1>
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
        <tr>
            <td class="alignRight">
                <label for="code">Product Code:</label>
            </td>
            <td>
                <input class="input" type="text" id="code" name="code" value="${productBean.code}" required/><br />
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="description">Title:</label>
            </td>
            <td>
                <input class="input" type="text" id="description" name="description" value="${productBean.description}" required/><br />
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="price">Price:</label>
            </td>
            <td>
                <input class="input" type="text" id="price" name="price" value="${productBean.price}" required/><br />
            </td>
        </tr>                    
        <tr>
            <td class="alignRight">
                <label for="releaseDate">Release Date:</label>
            </td>
            <td>
                <input class="input" type="date" id="releaseDate" name="releaseDate" value="${productBean.releaseDate}" required/><br />
            </td>
        </tr>
        <tr>
            <td class="alignRight" colspan="2">
                <input class="submit" type="submit" value="Submit" />
                <input hidden="true" type="hidden" name="action" value="add" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
