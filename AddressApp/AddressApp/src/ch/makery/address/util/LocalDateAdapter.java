package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adaptador (para JAXB) para converter entre LocalDate e representação String 
 * ISO 8601 da data como '2012-12-03'.
 * 
 * @author Sávio Cardoso, Marco Jakob
 * @version 2.0
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Retorna uma a Data Local no formato parse 
     *
     * @param v
     * @return LocalDate
     * @throws java.lang.Exception
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * Retorna uma a Data Local no formato de texto
     *
     * @param v
     * @return LocalDate
     * @throws java.lang.Exception
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}