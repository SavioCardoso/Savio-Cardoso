package ch.makery.address.controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.MainApp;
import ch.makery.address.model.Pessoa;
import ch.makery.address.path.CaminhoArquivo;
import ch.makery.address.path.ManipulaDadosArquivo;
import ch.makery.address.util.ShowDialogs;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * O controlador para o root layout. O root layout provê um layout básico
 * para a aplicação contendo uma barra de menu e um espaço onde outros elementos
 * JavaFX podem ser colocados.
 * 
 * @author Marco Jakob
 */
public class ControleDeLayoutBase {

    
    
    // Referência à aplicação principal
    private MainApp mainApp;
    private CaminhoArquivo caminho;
    private ManipulaDadosArquivo manipuladorDados;
    private ShowDialogs showDialogs;
    private ObservableList<Pessoa> pessoa;
    
    

    /**
     * É chamado pela aplicação principal para referenciar a si mesma.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * 
     * @param caminho
     */
    public void setCaminhoArquivo(CaminhoArquivo caminho) {
        this.caminho = caminho;
    }
    
    /**
     * 
     * @param caminho
     */
    public void setShowDialogs(Stage palcoPrimario, 
            ObservableList<Pessoa> pessoa){
        showDialogs = new ShowDialogs(palcoPrimario);
        this.pessoa=pessoa;
    }
    
    /**
     * 
     * @param manipuladorDados
     */
    public void setManipulaDadosArquivo(ManipulaDadosArquivo manipuladorDados) {
        this.manipuladorDados = manipuladorDados;
    }

    /**
     * Cria uma agenda vazia.
     */
    @FXML
    private void handleNew() {
        mainApp.getDadosPessoa().clear();
        caminho.setCaminhoArquivoPessoa(null);
    }

    /**
     * Abre o FileChooser para permitir o usuário selecionar uma agenda
     * para carregar.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Define um filtro de extensão
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostra a janela de salvar arquivo
        File file = fileChooser.showOpenDialog(mainApp.getPalcoPrimario());

        if (file != null) {
            manipuladorDados.carregaDadosPessoaDoArquivo(file);
        }
    }

    /**
     * Salva o arquivo para o arquivo de pessoa aberto atualmente. Se não houver
     * arquivo aberto, a janela "salvar como" é mostrada.
     */
    @FXML
    private void handleSave() {
        File pessoaFile = caminho.getCaminhoArquivoPessoa();
        if (pessoaFile != null) {
            manipuladorDados.salvaDadosPessoaParaArquivo(pessoaFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Abre um FileChooser para permitir o usuário selecionar um arquivo
     * para salvar.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser escolhaArquivo = new FileChooser();

        // Define o filtro de extensão
        FileChooser.ExtensionFilter filtroExtencao = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        escolhaArquivo.getExtensionFilters().add(filtroExtencao);

        // Mostra a janela para salvar arquivo
        File arquivo = escolhaArquivo.showSaveDialog(mainApp.getPalcoPrimario());

        if (arquivo != null) {
            // Certifica de que esta é a extensão correta
            if (!arquivo.getPath().endsWith(".xml")) {
                arquivo = new File(arquivo.getPath() + ".xml");
            }
            manipuladorDados.salvaDadosPessoaParaArquivo(arquivo);
        }
    }

    /**
     * Abre uma janela Sobre.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("Sobre");
        alert.setContentText("Autor: Sávio Cardoso e Marco Jakob");

        alert.showAndWait();
    }
    
    /**
    * Abre as estatísticas de aniversário.
    */
    @FXML
    private void handleShowBirthdayStatistics() {
        Dialogs.create().title("Certo")
                    .masthead("Chamou o handleShowBirthdayStatistics\n");
        showDialogs.showBirthdayStatistics(pessoa);
    }

    /**
     * Fecha a aplicação.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}