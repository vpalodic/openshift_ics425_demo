<%-- 
    Document   : header
    Created on : Sep 8, 2017, 11:20:45 AM
    Author     : Vincent
--%>

<%@page import="java.time.LocalDate, java.beans.PropertyEditorManager, edu.metrostate.ics425.vjp071.prodmaint.model.propertyeditor.LocalDatePropertyEditor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:scriptlet>
    PropertyEditorManager.registerEditor(LocalDate.class, LocalDatePropertyEditor.class);
</jsp:scriptlet>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/product.css"/>
        <jsp:useBean id="productBean" scope="request" class="edu.metrostate.ics425.vjp071.prodmaint.model.ProductBean" >
            <jsp:setProperty name="productBean" property="*"/>
        </jsp:useBean>
        <title>Product Maintenance Demo</title>
    </head>
    <body>
        <table border="0" width="100%">
            <tr>
                <td class="alignCenter" colspan="2">
                    <a href="index.jsp?action=add">
                        New
                    </a>
                    | 
                    <a href="index.jsp?action=home">
                        Home
                    </a>
                </th>
            </tr>
        </table>
