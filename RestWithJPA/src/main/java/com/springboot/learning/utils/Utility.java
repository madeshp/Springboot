package com.springboot.learning.utils;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom Utility class
 */
public final class Utility {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Utility() {
    }

    /**
     * Method to fetch Stack Trace
     *
     * @param t
     *          - Throwable object
     * @return sw - String object
     */
    public static String getStackTrace(final Throwable t) {
        final StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    /**
     * Method to create XMLGregorianCalendar as datetime.
     *
     */
    public static XMLGregorianCalendar getCurrentDateTimeAsXMLGregorianCalendar() {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        final GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.now());
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        }
        catch (final DatatypeConfigurationException e) {
            LOGGER.error("Failed converting date to XMLGregorianCalendar", e);
        }
        return xmlGregorianCalendar;
    }

    /**
     * Method to create XMLGregorianCalendar as time.
     *
     */
    public static XMLGregorianCalendar getCurrentTimeOnlyAsXMLGregorianCalendar() {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar =
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalTime.now().format(timeFormatter));
        }
        catch (final DatatypeConfigurationException e) {
            LOGGER.error("Failed converting LocalTime to XMLGregorianCalendar", e);
        }
        return xmlGregorianCalendar;
    }

}