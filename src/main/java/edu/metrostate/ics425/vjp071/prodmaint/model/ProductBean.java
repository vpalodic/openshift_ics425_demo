/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.model;

import edu.metrostate.ics425.prodmaint.model.Product;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The ProductBean holds information about a product.
 * @author Vincent
 */
public class ProductBean implements edu.metrostate.ics425.prodmaint.model.Product {
    private static final long serialVersionUID = 2017092201L;
    private String code;
    private String description;
    private Double price;
    private LocalDate releaseDate;

    /**
     * Initializes this ProductBean to an empty bean.
     */
    public ProductBean() {
        this(null);
    }
    
    /**
     * Initializes this ProductBean to the values of the specified Product.
     * If product is null it is ignored and this ProductBean will be empty.
     * Otherwise, this ProductBean will contain the same values as product.
     * 
     * @param product The Product to use to initialize this ProductBean with.
     */
    public ProductBean(Product product) {
        super();
        if (product != null) {
            code = product.getCode();
            description = product.getDescription();
            price = product.getPrice();
            releaseDate = product.getReleaseDate();
        }
    }
    
    /**
     * Returns the code of this product
     * @return product code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Returns the description of this product
     * @return product description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of this product
     * @return product price
     */
     @Override
    public Double getPrice() {
        return price;
    }

    /**
     *  Returns the release date of this product
     * @return product release date
     */
    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Returns the number of years since the release date of this product
     * @return number of years
     */
    @Override
    public int getYearsReleased() {
        int answer = 0;

        if (releaseDate == null) {
            answer = -2;
        } else if (releaseDate.isAfter(LocalDate.now()) == true) {
            answer = -1;
        } else {
            Period span = Period.between(releaseDate, LocalDate.now());

            if (span != null) {
                answer = span.getYears();
            }
        }

        return answer;
    }

     /**
     * Sets the product code.
     * @param code
     */
   @Override
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the description.
     * @param description 
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the price.
     * @param price 
     */
    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Sets the release date.
     * @param date 
     */
    @Override
    public void setReleaseDate(LocalDate date) {
        releaseDate = date;
    }

    /**
     * Returns a String representation of this product.
     * @return A String representation of this product.
     */
    @Override
    public String toString() {
        return String.format("Code: %s\nDescription: %s\nPrice: %s\nRelease Date: %s\nYears Released: %d",
                code, description, price != null ? NumberFormat.getCurrencyInstance().format(price) : null, releaseDate != null ? releaseDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) : null, getYearsReleased());
    }

     /**
     * Returns the hash code for this Product. If two Products are equal, their
     * hash codes will be equal.
     * @return A hash code for this Product.
     */
   @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(code != null ? code.toLowerCase() : null);
        return hash;
    }

    /**
     * Returns true if two Products have the same product code. When comparing
     * the codes for equality, case is not used.
     * 
     * @param obj The other Product to compare to.
     * @return Returns true if the two Products are considered to be equal.
     */
    @Override
    public boolean equals(Object obj) {
        boolean answer = false;
        
        if (this == obj) {
            answer = true;
        } else if (code != null && obj != null && getClass() == obj.getClass()) {
            final ProductBean other = (ProductBean) obj;
            if (String.CASE_INSENSITIVE_ORDER.compare(code, other.code) == 0) {
                answer = true;
            }
        }
        return answer;        
    }
}
