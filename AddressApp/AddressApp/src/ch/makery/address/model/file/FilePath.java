package ch.makery.address.model.file;

import ch.makery.address.MainApp;
import java.io.File;
import java.util.prefs.Preferences;

/**
 * Classe Caminho do Arquivo.
 * Tem como responsabilidade mostrar o caminho que o arquivo está.
 *
 * @author Sávio Cardoso
 * @version 2.0
 */
public class FilePath {
      
    /**
    * Retorna o arquivo de preferências da pessoa, o último arquivo que foi aberto.
    * As preferências são lidas do registro específico do SO (Sistema Operacional). 
    * Se tais prefêrencias não puderem  ser encontradas, ele retorna null.
    * 
    * @return
    */
    public File getFilePath() {
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       String filePath = prefs.get("filePath", null);
       if (filePath != null) {
           return new File(filePath);
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
    public void setFilePath(File file) {
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       if (file != null) {
           prefs.put("filePath", file.getPath());

           // Update the stage title.
           MainApp.getInstance().getPrimaryStage().setTitle("AddressApp - " + file.getName());
       } else {
           prefs.remove("filePath");

           // Update the stage title.
           MainApp.getInstance().getPrimaryStage().setTitle("AddressApp");
       }
   }
}
