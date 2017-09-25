<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="func" uri="/WEB-INF/tlds/functions.tld"%>

<c:import url="/includes/header.jsp" />
<table>
    <tr>
        <th colspan="2">
            <h1>Product Maintenance</h1>
        </th>
    </tr>
    <tr>
        <td>
            <ul>
                <li>
                    <a href="catalog">
                        Product Catalog
                    </a>
                </li>
                <li>
                    <a href="catalog/add">
                        Add New Product
                    </a>
                </li>
            </ul>
        </td>
    </tr>
</table>
<c:import url="/includes/footer.jsp" />
