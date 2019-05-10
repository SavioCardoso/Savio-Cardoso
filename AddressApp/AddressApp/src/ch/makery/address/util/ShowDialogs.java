/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.util;

import ch.makery.address.MainApp;
import ch.makery.address.controllers.ControleDeEditarPessoa;
import ch.makery.address.controllers.ControleDeEstatisticasDeAniversarios;
import ch.makery.address.model.Pessoa;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Sávio Cardoso
 */
public class ShowDialogs {
    
    private Stage palcoPrimario;
    
    public ShowDialogs(Stage palcoPrimario){
        this.palcoPrimario = palcoPrimario;
    }
    /**
    * Abre uma janela para editar detalhes para a pessoa especificada. Se o usuário clicar
    * OK, as mudanças são salvasno objeto pessoa fornecido e retorna true.
    * 
    * @param pessoa O objeto pessoa a ser editado
    * @return true Se o usuário clicou OK,  caso contrário false.
    */
    public boolean showPessoaEditDialog(Pessoa pessoa) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditarPessoa.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Pessoa");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(palcoPrimario);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            ControleDeEditarPessoa controller = loader.getController();
            controller.setPalcoDialogo(dialogStage);
            controller.setPessoa(pessoa);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
    * Abre uma janela para mostrar as estatísticas de aniversário.
    */
    public void showBirthdayStatistics(ObservableList<Pessoa> pessoa) {
        System.out.println("ANIVERSARIO");
        try {
            Dialogs.create().title("Certo")
                    .masthead("Chamou o showBirthdayStatistics no main app\n");
            // Carrega o arquivo fxml e cria um novo palco para o popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EstatisticasDeAniversarios.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(palcoPrimario);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa dentro do controller.
            ControleDeEstatisticasDeAniversarios controller = loader.getController();
            controller.adicionaDadosAoGrafico(pessoa);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
