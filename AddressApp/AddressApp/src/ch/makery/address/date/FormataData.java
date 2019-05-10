package ch.makery.address.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formatar a Data.
 * Tem como responsabilidade única formatar uma data.
 *
 * @author Sávio Cardoso, Marco Jakob
 * @version 1.5
 */
public class FormataData {
    
    /** O padrão usado para conversão. 
      * dd = Dia
      * MM = Mês
      * yyyy = Ano (com 4 algarismos)
    */
    private static final String DATA_MODELO = "dd.MM.yyyy";
    
    /** O formatador de data. */
    private static final DateTimeFormatter DATA_FORMATO = 
            DateTimeFormatter.ofPattern(DATA_MODELO);
    
    /**
     * Retorna os dados como String formatado. 
     * O padrão de data que é utilizado.
     * 
     * @param date A data a ser retornada como String
     * @return String formadado
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATA_FORMATO.format(date);
    }

    /**
     * Converte um String no formato definido para um objeto.
     * 
     * Retorna null se o String não puder se convertido.
     * 
     * @param dateString a data como String
     * @return o objeto data ou null se não puder ser convertido
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATA_FORMATO.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}