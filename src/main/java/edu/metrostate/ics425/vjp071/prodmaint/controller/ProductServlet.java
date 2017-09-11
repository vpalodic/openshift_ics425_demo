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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(name = "Product", description = "Handles product requests", urlPatterns = { "/product" })
/**
 * The ProductServlet is the controller for handling requests that involve the
 * ProductBean and associated views.
 *
 * @author Vincent
 */
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
        String url = "/index.jsp";

        String action = request.getParameter("action");

        if (action == null || action.equals("home")) {
            action = "home";
            url += "?action=" + action;
        } else if (action.equals("add")) {
            StringBuilder message = new StringBuilder();
            ProductBean productBean = new ProductBean();

            validateParameters(request, response, productBean, message);

            if (message.length() == 0) {
                // We can "add" the record to the database.
                message.append("New record has been added!<br />");
                action = "view";
                url += "?action=" + action;
            }

            request.setAttribute("productBean", productBean);
            request.setAttribute("message", message.toString());
        } else {
            // For now we will just follow the "add" logic.
            StringBuilder message = new StringBuilder();
            ProductBean productBean = new ProductBean();

            validateParameters(request, response, productBean, message);
            
            // Reset the messages as we don't care about any errors.
            message = new StringBuilder();

            if (message.length() == 0) {
                // We can "load" the record from the database.
                message.append("Here is the requested record:<br />");
                action = "view";
                url += "?action=" + action;
            }

            request.setAttribute("productBean", productBean);
            request.setAttribute("message", message.toString());
        }
        
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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

    private void validateParameters(HttpServletRequest request, HttpServletResponse response, ProductBean productBean, StringBuilder message) {
        // get parameters from the request
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String stringPrice = request.getParameter("price");
        double price = 0.0;
        String stringReleaseDate = request.getParameter("releaseDate");
        LocalDate releaseDate = null;

        // validate the parameters
        if (code == null || code.trim().isEmpty()) {
            message.append("Product Code is required and cannot be blank.<br />");
        } else {
            productBean.setCode(code.trim());
        }

        if (description == null || description.trim().isEmpty()) {
            message.append("Title is required and cannot be blank.<br />");
        } else {
            productBean.setDescription(description.trim());
        }

        if (stringPrice == null || stringPrice.trim().isEmpty()) {
            message.append("Price is required and cannot be blank.<br />");
        } else {
            try {
                price = Double.parseDouble(stringPrice.trim());

                if (price < 0.00) {
                    message.append("Price must be greater than or equal to 0.<br />");
                } else {
                    productBean.setPrice(price);
                }
            } catch (NumberFormatException ex) {
                message.append("Price must be a valid number.<br />");
                log("\"Price must be a valid number.", ex);
            }
        }

        if (stringReleaseDate == null || stringReleaseDate.trim().isEmpty()) {
            message.append("Release Date is required and cannot be blank.<br />");
        } else {
            try {
                releaseDate = LocalDate.parse(stringReleaseDate.trim());
                productBean.setReleaseDate(releaseDate);
            } catch (DateTimeParseException ex) {
                message.append("Release Date must be a valid date in this format: YYYY-MM-DD.<br />");
                log("Release Date must be a valid date in this format: YYYY-MM-DD.", ex);
            }
        }
    }
}
