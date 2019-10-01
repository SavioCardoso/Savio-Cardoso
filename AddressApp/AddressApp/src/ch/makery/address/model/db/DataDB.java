/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.model.db;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 * Classe Pessoa para Banco de Dados.
 * Tem como responsabilidade converter uma Pessoa para Banco de Dados.
 *
 * @author Sávio Cardoso
 * @version 1.0
 */
public class DataDB {
    
    /**
     * Salva os dados da pessoa atual no BD.
     * 
     * @param file
     */
    public void savePersonDataToBD(PersonListWrapper wrapper) {
        try{
            List<Person> pessoas = wrapper.getPersons();
            Person pessoa;
            DBConnection.deleteBD();
            DBConnection.createBD();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/agenda","root","root");
            Statement stmt=con.createStatement();
            
            for (int i = 0; i < pessoas.size(); i++) {
                pessoa=pessoas.get(i);
                stmt.executeQuery("INSERT INTO agenda (firstName, lastName,"
                        + " street, postalCode, city, birthday) VALUES ("+
                        pessoa.getFirstName()+", "+pessoa.getLastName()+", "+ 
                        pessoa.getStreet()+", "+pessoa.getPostalCode()+", " + 
                        pessoa.getCity()+", "+pessoa.getBirthday()+");"); 
            }
            
            con.close();
            
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel salvar os dados");
                alert.setContentText("Não foi possivel salvar os dados no Banco de Dados\n");

                alert.showAndWait();
        }
    }
}
