/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.controller;

import edu.metrostate.ics425.prodmaint.data.ProductCatalog;
import edu.metrostate.ics425.prodmaint.model.Product;
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
 * The ProductEditServlet is the controller for adding updating existing Products in the catalog.
 * Products can only be updated by HTTP POST requests. All GET requests and 
 * POST requests containing errors, will forward to the edit product form. If the
 * product update is successful, the request is forwarded to the product catalog.
 * 
 * @author vincent
 */
@WebServlet(name = "ProductEdit", urlPatterns = {"/product/edit"})
public class ProductEditServlet extends HttpServlet {

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
        LinkedList<String> messages = new LinkedList<>();
        ProductBean productBean = null;
        
        String url = "/views/productEdit.jsp"; 

        // 2. validate data n/a
        String code = getExistingCodeFromRequest(request, messages);
        
        // 3. "do it" n/a
        if (messages.isEmpty() && code != null) {
            // No errors so get the product from the catalog
            final ProductCatalog catalog = ProductCatalog.getInstance();
            
            productBean = new ProductBean(catalog.selectProduct(code));
        }           
        
        // 4. store information on request n/a
        request.setAttribute("productBean", productBean);
        request.setAttribute("messages", messages);
        
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
            // No errors so update the product in the ProductCatalog
            final ProductCatalog catalog = ProductCatalog.getInstance();
            Product product = catalog.selectProduct(productBean.getCode());
            boolean changeDetected = false;
            String code = product.getCode();
            String description = product.getDescription();
            Double price = product.getPrice();
            LocalDate releaseDate = product.getReleaseDate();
            
            if ((description != null && !description.equals(productBean.getDescription())) ||
                    (description == null && productBean.getDescription() != null)) {
                product.setDescription(productBean.getDescription());
                changeDetected = true;
            }
            if ((price != null && !price.equals(productBean.getPrice())) ||
                    (price == null && productBean.getPrice() != null)) {
                product.setPrice(productBean.getPrice());
                changeDetected = true;
            }
            if ((releaseDate != null && !releaseDate.equals(productBean.getReleaseDate())) ||
                    (releaseDate == null && productBean.getReleaseDate() != null)) {
                product.setReleaseDate(productBean.getReleaseDate());
                changeDetected = true;
            }
            
            if (changeDetected) {
                catalog.updateProduct(product);
                messages.add("Product " + code + " has been updated.");
            } else {
                messages.add("Product " + code + " has not been updated due to no changes.");
            }
        } else {
            url = "/views/productEdit.jsp";
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

    private String getExistingCodeFromRequest(HttpServletRequest request, LinkedList<String> messages) {
        // get parameters from the request
        String code = request.getParameter("code");

        // validate the parameters
        if (code == null || code.trim().isEmpty()) {
            messages.add("Product Code is required and cannot be blank.");
            code = null;
        } else {
            final ProductCatalog catalog = ProductCatalog.getInstance();
            String trimmedCode = code.trim();
            
            if (!catalog.exists(trimmedCode)) {
                messages.add("Product Code " + trimmedCode + " does not exist.");
                code = null;
            } else {
                code = trimmedCode;
            }
        }
        
        return code;
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
                messages.add("Product Code " + trimmedCode + " does not exist.");
            } else {
                productBean.setCode(trimmedCode);
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
