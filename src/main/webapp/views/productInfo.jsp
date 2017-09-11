<%-- 
    Document   : response
    Created on : Sep 7, 2017, 10:19:39 PM
    Author     : Vincent
--%>

        <table border="0" width="100%">
            <tr>
                <th colspan="2">
                    <h1>Product Information</h1>
                </th>
            </tr>
                <%
                    String message = (String) request.getAttribute("message");
                    
                    if (message != null) {
                %>
                <tr>
                    <td class="info" colspan="2">
                        <span>${message}</span>
                    </td>
                </tr>
                <% } %>
            <tr>
                <td class="alignRight">
                    <label>Product Code:</label>
                </td>
                <td>
                    <span class="output" name="code">${productBean.code}</span><br />
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label>Title:</label>
                </td>
                <td>
                    <span class="output" name="description">${productBean.description}</span><br />
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label>Price:</label>
                </td>
                <td>
                    <span class="output" name="price">${String.format("$%02.2f", productBean.price)}</span><br />
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label>Release Date:</label>
                </td>
                <td>
                    <span class="output" name="releaseDate">${productBean.releaseDate}</span><br />
                </td>
            </tr>
            <tr>
                <td class="alignRight">
                    <label>Years in Release:</label>
                </td>
                <td>
                    <span class="output" name="yearsReleased">${productBean.yearsReleased}</span><br />
                </td>
            </tr>
        </table>
