/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.model.tests;

import java.time.LocalDate;
import org.junit.Test;

import edu.metrostate.ics425.vjp071.prodmaint.model.ProductBean;

import static org.junit.Assert.*;

/**
 *
 * @author Vincent
 */
public class ProductBeanTest {
    
    public ProductBeanTest() {
    }

    @Test
    public void testBeanProperties() {
        ProductBean productA = new ProductBean();
        ProductBean productB = new ProductBean();
        
        assertNotNull(productA);
        assertNotNull(productB);
        
        productA.setCode("C");
        
        assertEquals("C", productA.getCode());
        
        productB.setCode("c");
        
        assertEquals("c", productB.getCode());
        
        assertTrue(productA.equals(productB));
        
        productA.setDescription("Product A");
        assertEquals("Product A", productA.getDescription());
        
        productB.setDescription("Product B");
        assertEquals("Product B", productB.getDescription());
        
        productA.setReleaseDate(LocalDate.of(1977, 9, 1));
        productB.setReleaseDate(LocalDate.of(1978, 9, 7));
        
        productA.setPrice(19.99);
        productB.setPrice(14.99);
        
        System.out.println(productA.toString());
        System.out.println(productB.toString());
    }
    
}
