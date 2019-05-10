package ch.makery.address.date;

/**
 * Verifica a Validade.
 * Tem como responsabilidade única verificar a validade de uma data.
 *
 * @author Sávio Cardoso
 * @version 1.0
 */
public class ValidaData {
    
    /**
     * Checa se o String é uma data válida.
     * 
     * @param dateString A data como String
     * @return true se o String é uma data válida
     */
    public static boolean validDate(String dateString) {
        // Tenta converter o String.
        return FormataData.parse(dateString) != null;
    }
}
