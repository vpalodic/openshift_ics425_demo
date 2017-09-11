/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.metrostate.ics425.vjp071.prodmaint.model.propertyeditor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This property editor is used to convert a String in to a LocalDate object and
 * for setting a LocalDate variable as a value in JSP pages.
 *
 * The format of the string is the ISO_DATE format (YYYY-mm-dd).
 *
 * If an invalid date is passed in, no exception is thrown. Instead, the
 * LocaleDate object is simply null.
 *
 * @author Vincent
 */
public class LocalDatePropertyEditor extends java.beans.PropertyEditorSupport {

    private LocalDate localDate;

    /**
     * Called when converting a String into the LocalDate object for a bean
     * property. If text is null or text is not a valid date then the LocalDate
     * property is set to null.
     * 
     * @param text The date to be converted in to a LocalDate object.
     */
    @Override
    public void setAsText(String text) {
        if (text != null) {
            if (text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    localDate = LocalDate.parse(text);
                } catch (DateTimeParseException ex) {
                    Logger.getLogger(LocalDatePropertyEditor.class.getName()).log(Level.INFO, null, ex);
                }
            }
        }
    }

    /**
     * Returns a reference to the LocalDate object or null if a valid date is
     * not set.
     *
     * @return A reference to the LocalDate object or null if a valid date is
     * not set.
     */
    @Override
    public Object getValue() {
        return localDate;
    }
}
