<%-- 
    Document   : header
    Created on : Sep 8, 2017, 11:20:45 AM
    Author     : Vincent
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
        <link rel="stylesheet" href="styles/product.css"/>
        <title>Product Maintenance Demo</title>
    </head>
    <body>
        <table>
            <tr>
                <td class="alignCenter" colspan="2">
                    <a href="index.jsp">
                        Home
                    </a>
                    | 
                    <a href="catalog">
                        Catalog
                    </a>
                    | 
                    <a href="product/add">
                        Add Product
                    </a>
                </td>
            </tr>
        </table>
