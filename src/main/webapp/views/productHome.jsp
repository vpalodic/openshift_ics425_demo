<%-- 
    Document   : productHome
    Created on : Sep 8, 2017, 11:32:35 AM
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="func" uri="/WEB-INF/tlds/functions.tld"%>

<c:import url="/includes/header.jsp" />
<table>
    <tr>
        <th colspan="2">
            <h1>Product Maintenance Demo</h1>
        </th>
    </tr>
    <tr>
        <td>
            <ul>
                <li>
                    <a href="views/productEntry.jsp">
                        Manually add a new record.
                    </a>
                </li>
                <li>
                    <a href="product?action=add&code=C&description=Help&price=14.99&releaseDate=${func:now()}">
                        Auto add a new record.
                    </a>
                </li>
                <li>
                    <a href="product?action=view&code=C&description=Help&price=19.985&releaseDate=1977-12-31">
                        View an existing record.
                    </a>
                </li>
            </ul>
        </td>
    </tr>
</table>
<c:import url="/includes/footer.jsp" />
