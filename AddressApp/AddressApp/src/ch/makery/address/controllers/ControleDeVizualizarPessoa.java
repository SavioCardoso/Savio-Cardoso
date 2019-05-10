package ch.makery.address.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Pessoa;
import ch.makery.address.date.FormataData;
import ch.makery.address.util.ShowDialogs;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControleDeVizualizarPessoa {
    @FXML
    private TableView<Pessoa> tabelaPessoa;
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    @FXML
    private TableColumn<Pessoa, String> colunaSobrenome;

    @FXML
    private Label campoNome;
    @FXML
    private Label campoSobrenome;
    @FXML
    private Label campoRua;
    @FXML
    private Label campoCEP;
    @FXML
    private Label campoCidade;
    @FXML
    private Label campoAniversario;

    // Reference to the main application.
    private MainApp mainApp;
    
    // Reference to showDialogs.
    private ShowDialogs showDialogs;

    /**
     * O construtor.
     * O construtor é chamado antes do método inicialize().
     */
    public ControleDeVizualizarPessoa(){
    }

    /**
    * PReenche todos os campos de texto para mostrar detalhes sobre a pessoa.
    * Se a pessoa especificada for null, todos os campos de texto são limpos.
    * 
    * @param pessoa a pessoa ou null
    */
    private void showPessoaDetails(Pessoa pessoa) {
        if (pessoa != null) {
            // Preenche as labels com informações do objeto pessoa.
            campoNome.setText(pessoa.getNome());
            campoSobrenome.setText(pessoa.getSobrenome());
            campoRua.setText(pessoa.getRua());
            campoCEP.setText(Integer.toString(pessoa.getCEP()));
            campoCidade.setText(pessoa.getCidade());
            campoAniversario.setText(FormataData.format(pessoa.getAniversario()));
            
        } else {
            // Pessoa é null, remove todo o texto.
            campoNome.setText("");
            campoSobrenome.setText("");
            campoRua.setText("");
            campoCEP.setText("");
            campoCidade.setText("");
            campoAniversario.setText("");
        }
    }
    
    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Inicializa a tabela de pessoas com duas colunas.
        colunaNome.setCellValueFactory(
                cellData -> cellData.getValue().propertyNome());
        colunaSobrenome.setCellValueFactory(
                cellData -> cellData.getValue().propertySobrenome());

        // Limpa os detalhes da pessoa.
        showPessoaDetails(null);

        // Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver mudança.
        tabelaPessoa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPessoaDetails(newValue));
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Adiciona os dados da observable list na tabela
        tabelaPessoa.setItems(mainApp.getDadosPessoa());
    }
    
    /**
    * Chamado quando o usuário clica no botão delete.
    */
   @FXML
    private void handleDeletePessoa() {
        int selectedIndex = tabelaPessoa.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tabelaPessoa.getItems().remove(selectedIndex);
        } else {
            // Nada selecionado.

        Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");

                alert.showAndWait();
        }
    }
   
    /**
    * Chamado quando o usuário clica no botão novo. Abre uma janela para editar
    * detalhes da nova pessoa.
    */
    @FXML
    private void handleNewPessoa() {
        showDialogs = new ShowDialogs(mainApp.getPalcoPrimario());
        Pessoa tempPessoa = new Pessoa();
        boolean okClicked = showDialogs.showPessoaEditDialog(tempPessoa);
        if (okClicked) {
            mainApp.getDadosPessoa().add(tempPessoa);
        }
    }

    /**
     * Chamado quando o usuário clica no botão edit. Abre a janela para editar
     * detalhes da pessoa selecionada.
     */
    @FXML
    private void handleEditPessoa() {
        showDialogs = new ShowDialogs(mainApp.getPalcoPrimario());
        Pessoa selectedPessoa = tabelaPessoa.getSelectionModel().getSelectedItem();
        if (selectedPessoa != null) {
            boolean okClicked = showDialogs.showPessoaEditDialog(selectedPessoa);
            if (okClicked) {
                showPessoaDetails(selectedPessoa);
            }

        } else {
            // Nada seleciondo.
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");
                alert.showAndWait();
        }
    }
}