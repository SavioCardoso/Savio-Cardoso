package ch.makery.address.date;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adaptador (para JAXB) para converter entre LocalDate e representação String 
 * ISO 8601 da data como '2012-12-03'.
 * 
 * @author Marco Jakob
 * @version 1.2
 */
public class AdaptadorDataLocal extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String data) throws Exception {
        return LocalDate.parse(data);
    }

    @Override
    public String marshal(LocalDate data) throws Exception {
        return data.toString();
    }
}