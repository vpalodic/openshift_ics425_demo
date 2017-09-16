/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.controller;

import edu.metrostate.ics425.vjp071.prodmaint.model.ProductBean;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The ProductServlet is the controller for handling requests that involve the
 * ProductBean and associated views.
 *
 * @author Vincent
 */
@WebServlet(name = "Product", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Get information from request
        String action = request.getParameter("action");

        LinkedList<String> messages = new LinkedList<>();
        ProductBean productBean = new ProductBean();
        String url = "/views/productEntry.jsp"; // Default to the entry form.

        // 2. validate data
        validateParameters(request, response, productBean, messages);

        // 3. "do it"
        if (action == null || action.equals("home")) {
            url = "/views/productHome.jsp";
        } else if (action.equals("add")) {
            if (messages.isEmpty()) {
                // We can "add" the record to the database.
                messages.add("New record has been added.");
                url = "/views/productInfo.jsp";
            }
        } else {
            // Reset the messages as we don't care about
            // any errors at this point.
            messages.clear();
                    
            // We can "load" the record from the database into the bean.
            messages.add("Here is the requested record:");
            url = "/views/productInfo.jsp";
        }
        
        // 4. store information on request
        request.setAttribute("productBean", productBean);
        
        if (messages.size() > 0) {
            request.setAttribute("messages", messages);
        }
        
        // 5. forward control (in this case to the resource defined in url)
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void validateParameters(HttpServletRequest request, HttpServletResponse response, ProductBean productBean, LinkedList<String> messages) {
        // get parameters from the request
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String stringPrice = request.getParameter("price");
        String stringReleaseDate = request.getParameter("releaseDate");

        // validate the parameters
        if (code == null || code.trim().isEmpty()) {
            messages.add("Product Code is required and cannot be blank.");
        } else {
            productBean.setCode(code.trim());
        }

        if (description == null || description.trim().isEmpty()) {
            messages.add("Title is required and cannot be blank.<br />");
        } else {
            productBean.setDescription(description.trim());
        }

        try {
            double price = Double.parseDouble(stringPrice.trim());

            if (price < 0.00) {
                messages.add("Price must be greater than or equal to 0.");
            } else {
                productBean.setPrice(price);
            }
        } catch (NumberFormatException | NullPointerException ex) {
            messages.add("Price is missing or is not a valid number.");
        }

        try {
            LocalDate releaseDate = LocalDate.parse(stringReleaseDate.trim());
            productBean.setReleaseDate(releaseDate);
        } catch (DateTimeParseException | NullPointerException ex) {
            messages.add("Release Date is missing or is not a valid date.");
        }
    }
}
