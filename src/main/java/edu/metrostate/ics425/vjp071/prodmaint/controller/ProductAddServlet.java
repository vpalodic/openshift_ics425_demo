/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.controller;

import edu.metrostate.ics425.prodmaint.data.ProductCatalog;
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
 * The ProductAddServlet is the controller for adding new Products to the catalog.
 * New products can only be added by HTTP POST requests. All GET requests and 
 * POST requests containing errors, will forward to the add product form. If the
 * product addition is successful, the request is forwarded to the product 
 * catalog.
 *
 * @author vincent
 */
@WebServlet(name = "ProductAdd", urlPatterns = {"/product/add"})
public class ProductAddServlet extends HttpServlet {

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
        // 1. Get information from request - n/a as we simply send the form
        String url = "/views/productAdd.jsp"; 

        // 2. validate data n/a
        // 3. "do it" n/a
        // 4. store information on request n/a
        // 5. forward control (in this case to the resource defined in url)
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
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
        // 1. Get information from the request
        LinkedList<String> messages = new LinkedList<>();
        ProductBean productBean = new ProductBean();
        
        String url = "/catalog"; // Default to product listing page.
        
        // 2. Validate parameters!
        validateParameters(request, productBean, messages);
        
        // 3. Do it!
        if (messages.isEmpty()) {
            // No errors so add the product to the ProductCatalog
            final ProductCatalog catalog = ProductCatalog.getInstance();
            
            catalog.insertProduct(productBean);
            messages.add("Product " + productBean.getCode() + " has been added to the catalog.");
        } else {
            url = "/views/productAdd.jsp";
        }
        
        // 4. Store information on the request
        request.setAttribute("productBean", productBean);
        request.setAttribute("messages", messages);
        
        // 5. Forward control (in this case to the resource defined in url)
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Initializes this servlet so that it is able to process requests
     * @throws ServletException 
     */
    @Override
    public void init() throws ServletException {
        super.init();
        
        String dataFile = getServletContext().getRealPath(ProductCatalogServlet.PRODUCT_CATALOG_FILE);
        if (!ProductCatalog.init(dataFile) && ProductCatalog.getInstance() == null) {
            throw new ServletException("Unable to initialize the product catalog.");
        }
    }

    private void validateParameters(HttpServletRequest request, ProductBean productBean, LinkedList<String> messages) {
        // get parameters from the request
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String stringPrice = request.getParameter("price");
        String stringReleaseDate = request.getParameter("releaseDate");

        // validate the parameters
        if (code == null || code.trim().isEmpty()) {
            messages.add("Product Code is required and cannot be blank.");
        } else {
            final ProductCatalog catalog = ProductCatalog.getInstance();
            String trimmedCode = code.trim();
            
            if (!catalog.exists(trimmedCode)) {
                productBean.setCode(trimmedCode);
            } else {
                messages.add("Product Code " + trimmedCode + " already exists. Please try again with a different code.");
            }
        }

        if (description == null || description.trim().isEmpty()) {
            messages.add("Title is required and cannot be blank.");
        } else {
            productBean.setDescription(description.trim());
        }

        try {
            Double price = Double.parseDouble(stringPrice.trim());

            if (price < 0.00) {
                messages.add("Price must be greater than or equal to 0.");
            } else {
                productBean.setPrice(price);
            }
        } catch (NumberFormatException | NullPointerException ex) {
            messages.add("Price is missing or is not a valid number.");
        }

        if (stringReleaseDate != null && !stringReleaseDate.trim().isEmpty()) {
            try {
                LocalDate releaseDate = LocalDate.parse(stringReleaseDate.trim());
                productBean.setReleaseDate(releaseDate);
            } catch (DateTimeParseException ex) {
                messages.add("Release Date is not a valid date.");
            }
        }
    }
}
