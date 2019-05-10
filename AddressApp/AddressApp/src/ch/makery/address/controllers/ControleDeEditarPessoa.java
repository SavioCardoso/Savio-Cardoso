package ch.makery.address.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ch.makery.address.model.Pessoa;
import ch.makery.address.date.FormataData;
import ch.makery.address.util.ValidaPessoa;

/**
 * Classe Controle para EditarPessoa.
 * Tem como responsabilidade única controlar a view EditarPessoa.
 *
 * @author Sávio Cardoso, Marco Jakob
 * @version 1.5
 */
public class ControleDeEditarPessoa {

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoSobrenome;
    @FXML
    private TextField campoRua;
    @FXML
    private TextField campoCEP;
    @FXML
    private TextField campoCidade;
    @FXML
    private TextField campoAniversario;


    private Stage palcoDialogo;
    private Pessoa pessoa;
    private boolean okClicked = false;

    /**
     * Inicializa a classe controle. 
     * Este método é chamado automaticamente após o arquivo fxml ter sido
     * carregado.
     */
    @FXML
    private void inicialize() {
    }

    /**
     * Define o palco deste dialog.
     * 
     * @param palcoDialogo
     */
    public void setPalcoDialogo(Stage palcoDialogo) {
        this.palcoDialogo = palcoDialogo;
    }

    /**
     * Define a pessoa a ser editada no dialog.
     * 
     * @param pessoa
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;

        campoNome.setText(pessoa.getNome());
        campoSobrenome.setText(pessoa.getSobrenome());
        campoRua.setText(pessoa.getRua());
        campoCEP.setText(Integer.toString(pessoa.getCEP()));
        campoCidade.setText(pessoa.getCidade());
        campoAniversario.setText(FormataData.format(pessoa.getAniversario()));
        campoAniversario.setPromptText("dd.mm.yyyy");
    }

    /**
     * Retorna true se o usuário clicar OK,caso contrário false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Chamado quando o usuário clica OK.
     */
    @FXML
    private void handleOk() {
        if (ValidaPessoa.isInputValid(campoNome, campoSobrenome, campoRua, 
                campoCEP, campoCidade, campoAniversario)) {
            pessoa.setNome(campoNome.getText());
            pessoa.setSobrenome(campoSobrenome.getText());
            pessoa.setRua(campoRua.getText());
            pessoa.setCEP(Integer.parseInt(campoCEP.getText()));
            pessoa.setCidade(campoCidade.getText());
            pessoa.setAniversario(FormataData.parse(campoAniversario.getText()));

            okClicked = true;
            palcoDialogo.close();
        }
    }

    /**
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void handleCancel() {
        palcoDialogo.close();
    }
}