package ch.makery.address.controllers;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Pessoa;
import ch.makery.address.util.AniversariosPorMes;

/**
 * Classe Controle para EstatisticasDeAniversarios.
 * Tem como responsabilidade única controlar a view EstatisticasDeAniversarios.
 *
 * @author Sávio Cardoso, Marco Jakob
 * @version 1.5
 */
public class ControleDeEstatisticasDeAniversarios {

    @FXML
    private BarChart<String, Integer> graficoDeBarras;

    @FXML
    private CategoryAxis eixoX;

    private ObservableList<String> nomesMeses = FXCollections.observableArrayList();

    /**
     * Inicializa a classe controller. 
     * Este método é chamado após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Obtém um array com nomes dos meses em Inglês.
        String[] meses = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Converte o array em uma lista e adiciona em nossa ObservableList de meses.
        nomesMeses.addAll(Arrays.asList(meses));
        
        // Associa os nomes de mês como categorias para o eixo horizontal.
        eixoX.setCategories(nomesMeses);
    }

    /**
     * Adiciona Dados Ao Grafico EstatisticasDeAniversarios.
     * 
     * @param pessoas
     */
    public void adicionaDadosAoGrafico(List<Pessoa> pessoas) {
        int[] contadorDeMes = new int[12];
        
        contadorDeMes=AniversariosPorMes.caluculaAniversariosPorMes(pessoas,
                contadorDeMes);
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Cria um objeto XYChart.Data para cada mês. Adiciona ele às séries.
        for (int i = 0; i < contadorDeMes.length; i++) {
            series.getData().add(new XYChart.Data<>(nomesMeses.get(i), contadorDeMes[i]));
        }
        
        graficoDeBarras.getData().add(series);
    }
}
