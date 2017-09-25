/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.controller;

import edu.metrostate.ics425.prodmaint.data.ProductCatalog;
import edu.metrostate.ics425.prodmaint.model.Product;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The ProductCatalogServlet is the controller for listing the Products in the catalog.
 * Products can be listed by both HTTP POST and GET requests. This servlet also acts as a
 * proxy for adding, editing, and deleting products in the catalog.
 * 
 * @author Vincent
 */
@WebServlet(name = "ProductCatalog", urlPatterns = {"/catalog","/catalog/*"})
public class ProductCatalogServlet extends HttpServlet {
    /**
     * The location of the product catalog relative to this application.
     */
    public static final String PRODUCT_CATALOG_FILE = "/WEB-INF/data/productcatalog.dat";
    
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
        String pathInfo = request.getPathInfo();
        
        String url = "/views/catalogHome.jsp"; // Default to the catalog view.

        // 2. validate data n/a
        
        // 3. "do it"
        if (pathInfo != null && !pathInfo.trim().isEmpty()) {
            String[] pathParts = pathInfo.split("/");
            
            if (pathParts.length > 1) {
                String path = pathParts[1];
                
                if (path != null && !path.trim().isEmpty()) {
                    switch (path) {
                        case "add":
                            url = "/product/add";
                            break;
                        case "edit":
                            url = "/product/edit";
                            break;
                        case "delete":
                            url = "/product/delete";
                            break;
                        default:
                            break;
                    }
                }
            }
        } 
        
        // 4. store information on request
        if (url.toLowerCase().contains("home")) {
            Collection<Product> productCatalog = ProductCatalog.getInstance().selectAllProducts();
            request.setAttribute("productCatalog", productCatalog);
        }
        
        // 5. forward control (in this case to the resource defined in url)
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Initializes this servlet so that it is able to process requests
     * @throws ServletException 
     */
    @Override
    public void init() throws ServletException {
        super.init();
        
        String dataFile = getServletContext().getRealPath(PRODUCT_CATALOG_FILE);
        if (!ProductCatalog.init(dataFile) && ProductCatalog.getInstance() == null) {
            throw new ServletException("Unable to initialize the product catalog.");
        }
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
}
