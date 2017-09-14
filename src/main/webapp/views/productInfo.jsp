<%-- 
    Document   : productInfo
    Created on : Sep 7, 2017, 10:19:39 PM
    Author     : Vincent
--%>
        <table>
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
                    <span class="output" id="price">${String.format("$%02.2f", productBean.price)}</span><br />
                </td>
            </tr>                    
            <tr>
                <td class="alignRight">
                    <label for="releaseDate">Release Date:</label>
                </td>
                <td>
                    <span class="output" id="releaseDate">${productBean.releaseDate}</span><br />
                </td>
            </tr>
            <tr>
                <td class="alignRight">
                    <label for="yearsReleased">Years in Release:</label>
                </td>
                <td>
                    <span class="output" id="yearsReleased">${productBean.yearsReleased}</span><br />
                </td>
            </tr>
        </table>
