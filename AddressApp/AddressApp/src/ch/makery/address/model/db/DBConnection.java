/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.model.db;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 * Classe Caminho do Banco de Dados.
 * Tem como responsabilidade mostrar o caminho que o banco de dados está.
 *
 * @author Sávio Cardoso
 * @version 1.0
 */
public class DBConnection {
    
    
    public static void createBD() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306","root","root");
            Statement stmt=con.createStatement();
            stmt.executeQuery("CREATE DATABASE agenda;\nUSE agenda;\n"
                    + "CREATE TABLE pessoa(id int, firstName varchar(20), "
                    + "lastName varchar(40), street varchar(50), "
                    + "postalCode int(8), city varchar(20), birthday date;");   
            con.close();
        }catch(Exception e){ 
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possível criar o BD");

                alert.showAndWait();
        }
    }
    
    public static void deleteBD() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/agenda","root","root");
            Statement stmt=con.createStatement();
            stmt.executeQuery("DROP DATABASE agenda;");   
            con.close();
        }catch(Exception e){ 
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possível deletar o Banco de Dados");

                alert.showAndWait();
        }
    }
}
