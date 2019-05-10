package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ch.makery.address.model.*;
import ch.makery.address.controllers.*;
import ch.makery.address.path.CaminhoArquivo;
import ch.makery.address.path.ManipulaDadosArquivo;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
public class MainApp extends Application {

    private Stage palcoPrimario;
    private BorderPane layoutBase;
    private CaminhoArquivo caminhoArquivo;
    private ManipulaDadosArquivo manipuladorDados;
    
    /**
     * Os dados como uma observable list de Pessoas.
     */
    private ObservableList<Pessoa> dadosPessoa = FXCollections.observableArrayList();
    
    /**
     * Retorna os dados como uma observable list de Pessoas. 
     * @return
     */
    public MainApp(){
        dadosPessoa.add(new Pessoa("Diego", "Demetrio"));
        dadosPessoa.add(new Pessoa("Savio", "Cardoso"));
        dadosPessoa.add(new Pessoa("Heitor", "INF2"));
        dadosPessoa.add(new Pessoa("Cristiano", "Maffort"));
        dadosPessoa.add(new Pessoa("Wilian", "Sallum"));
    }
    
    public ObservableList<Pessoa> getDadosPessoa() {
        return dadosPessoa;
    }
    
    @Override
    public void start(Stage palcoPrimario) {
        this.palcoPrimario = palcoPrimario;
        this.palcoPrimario.setTitle("AddressApp");
        
        // Set the application icon.
        this.palcoPrimario.getIcons().add(new
                Image("file:resources/images/address_book_32.png"));
        
        caminhoArquivo = new CaminhoArquivo(palcoPrimario);
        manipuladorDados = new ManipulaDadosArquivo(caminhoArquivo, dadosPessoa);
        
        initLayoutBase();

        showVisualizarPessoa();
    }
    
    /**
    * Inicializa o root layout e tenta carregar o último file
    * de pessoa aberto.
    */
    public void initLayoutBase() {
        try {
            // Carrega o root layout do file fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/LayoutBase.fxml"));
            layoutBase = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(layoutBase);
            palcoPrimario.setScene(scene);

            // Dá ao controller o acesso ao main app.
            ControleDeLayoutBase controle = loader.getController();
            controle.setMainApp(this);
            controle.setCaminhoArquivo(caminhoArquivo);
            controle.setManipulaDadosArquivo(manipuladorDados);
            controle.setShowDialogs(palcoPrimario,  dadosPessoa);

            palcoPrimario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tenta carregar o último file de pessoa aberto.
        File file = caminhoArquivo.getCaminhoArquivoPessoa();
        if (file != null) {
            manipuladorDados.carregaDadosPessoaDoArquivo(file);
        }
        
    }
    
    /**
     * Mostra a pessoa overview dentro do root layout.
     */
    public void showVisualizarPessoa() {
        try {
            // Carrega a pessoa overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VisualizarPessoa.fxml"));
            AnchorPane visaoPessoa = (AnchorPane) loader.load();

            // Define a pessoa overview no centro do root layout.
            layoutBase.setCenter(visaoPessoa);

            // Dá ao controlador acesso à the main app.
            ControleDeVizualizarPessoa controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPalcoPrimario() {
        return palcoPrimario;
    }

    public static void main(String[] args) {
        launch(args);
    }
}