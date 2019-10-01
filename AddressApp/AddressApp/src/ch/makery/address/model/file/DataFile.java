/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template arquivo, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.model.file;

import ch.makery.address.MainApp;
import ch.makery.address.model.PersonListWrapper;
import java.io.File;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Classe Pessoa para Arquivo.
 * Tem como responsabilidade converter uma Pessoa para Arquivo.
 *
 * @author Sávio Cardoso
 * @version 2.0
 */
public class DataFile {
    private FilePath filePath;
    
    public DataFile(){
        filePath = new FilePath();
    }
    
    
    public FilePath getFilePath(){
        return filePath;
    }
    
    /**
     * Carrega os dados da pessoa do arquivo especificado. A pessoa atual
     * será substituída.
     * 
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            MainApp.getInstance().getPersonData().clear();
            MainApp.getInstance().getPersonData().addAll(wrapper.getPersons());

            // Save the file path to the registry.
            filePath.setFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Could not save data");
             alert.setContentText("Could not save data to file:\n" + file.getPath());

             alert.showAndWait();
        }
    }

    /**
     * Salva os dados da pessoa atual no arquivo especificado.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Envolvendo nossos dados da pessoa.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(MainApp.getInstance().getPersonData());

            // Enpacotando e salvando XML  no arquivo.
            m.marshal(wrapper, file);

            // Saalva o caminho do arquivo no registro.
            filePath.setFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Could not save data");
             alert.setContentText("Could not save data to file:\n" + file.getPath());

             alert.showAndWait();
        }
    }
}