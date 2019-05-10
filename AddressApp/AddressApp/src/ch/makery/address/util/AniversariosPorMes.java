package ch.makery.address.util;

import ch.makery.address.model.Pessoa;
import java.util.List;

/**
 * Calcula a quantidade de Aniniversários por Mês.
 * Tem como responsabilidade única contar o número de pessoas que fazem 
 * aniversário em um mês específico.
 *
 * @author Sávio Cardoso
 * @version 1.0
 */
public class AniversariosPorMes {
    
    /**
     * Conta o número de pessoas tendo seus aniversários em um mês específico.
     * 
     * @param pessoas
     * @param contadorDeMes
     * 
     * @return contadorDeMes
     */
    public static int[] caluculaAniversariosPorMes(List<Pessoa> pessoas, int[] contadorDeMes) {
        for (Pessoa pessoa : pessoas) {
            int mes = pessoa.getAniversario().getMonthValue() - 1;
            contadorDeMes[mes]++;
        }
        return contadorDeMes;
    }
}
