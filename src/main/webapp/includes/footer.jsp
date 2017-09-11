<%-- 
    Document   : footer
    Created on : Sep 8, 2017, 11:14:03 AM
    Author     : Vincent
--%>

<%@ page import="java.time.LocalDate" %>
<%  
    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
%>
<p class="alignCenter">&copy; Copyright <%= currentYear %> Vincent J. Palodichuk</p>
    </body>
</html>