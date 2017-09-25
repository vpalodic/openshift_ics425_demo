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
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The ProductDeleteServlet is the controller for deleting Products from the catalog.
 * Products can only be removed by HTTP POST requests. All GET requests and 
 * POST requests containing errors, will forward to the delete product form. If the
 * product deletion is successful, the request is forwarded to the product catalog.
 * 
 * @author vincent
 */
@WebServlet(name = "ProductDelete", urlPatterns = {"/product/delete"})
public class ProductDeleteServlet extends HttpServlet {

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
        
        String url = "/views/productDelete.jsp"; 

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
        String code = getExistingCodeFromRequest(request, messages);
        
        // 3. Do it!
        if (messages.isEmpty() && code != null) {
            // User has confirmed so delete the product in the catalog.
            final ProductCatalog catalog = ProductCatalog.getInstance();
            Product product = catalog.selectProduct(code);
            catalog.deleteProduct(product);
            messages.add("Product " + code+ " has been deleted.");
        } else {
            url = "/views/productDelete.jsp";
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

    private String getExistingCodeFromRequest(HttpServletRequest request, LinkedList<String> messages, boolean needConfirmation) {
        // get parameters from the request
        String code = request.getParameter("code");
        String confirmed = request.getParameter("confirmed");

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
        
        if (needConfirmation && (confirmed == null || !confirmed.equals("true"))) {
            messages.add("Confirmation is required in order to delete a product.");
            code = null;
        }
        
        return code;
    }

    private String getExistingCodeFromRequest(HttpServletRequest request, LinkedList<String> messages) {
        return getExistingCodeFromRequest(request, messages, false);
    }
}
