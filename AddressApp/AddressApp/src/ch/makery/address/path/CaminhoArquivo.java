package ch.makery.address.path;

import ch.makery.address.MainApp;
import java.io.File;
import java.util.prefs.Preferences;
import javafx.stage.Stage;

/**
 * Classe Modelo para uma Pessoa.
 * Tem como responsabilidade única servir como modelo caminho do Arquivo.
 *
 * @author Sávio Cardoso
 * @version 1.0
 */
public class CaminhoArquivo {
   
    private Stage palcoPrimario;
    
    public CaminhoArquivo(Stage palcoPrimario){
        this.palcoPrimario=palcoPrimario;
    }
    
    /**
    * Retorna o arquivo de preferências da pessoa, o último arquivo que foi aberto.
    * As preferências são lidas do registro específico do SO (Sistema Operacional). 
    * Se tais prefêrencias não puderem  ser encontradas, ele retorna null.
    * 
    * @return
    */
    public File getCaminhoArquivoPessoa() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String caminhoArquivo = prefs.get("caminhoArquivo", null);
        if (caminhoArquivo != null) {
            return new File(caminhoArquivo);
        } else {
            return null;
        }
    }

    /**
     * Define o caminho do arquivo do arquivo carregado atual. O caminho é persistido no
     * registro específico do SO (Sistema Operacional).
     * 
     * @param arquivo O arquivo ou null para remover o caminho
     */
    public void setCaminhoArquivoPessoa(File arquivo) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (arquivo != null) {
            prefs.put("caminhoArquivo", arquivo.getPath());

            // Update the stage title.
            palcoPrimario.setTitle("AddressApp - " + arquivo.getName());
        } else {
            prefs.remove("caminhoArquivo");

            // Update the stage title.
            palcoPrimario.setTitle("AddressApp");
        }
    }
}
