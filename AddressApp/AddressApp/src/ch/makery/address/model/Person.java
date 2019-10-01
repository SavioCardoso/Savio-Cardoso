package ch.makery.address.model;

import ch.makery.address.util.LocalDateAdapter;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Classe Modelo para uma Pessoa.
 * Tem como responsabilidade única servir como modelo para pessoa.
 *
 * @author Sávio Cardoso, Marco Jakob
 * @version 2.0
 */
public class Person {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;

    /**
     *  Construtor padrão.
     */
    public Person() {
        this(null, null);
    }
    
    /**
     * Construtor com alguns dados iniciais.
     * 
     * @param firstName Primeiro nome da Pessoa.
     * @param lastName Sobrenome da Pessoa.
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        
        // Alguns dados de exemplo, apenas para testes.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        int mes =(int) (Math.random() * (12 - 1) + 1); // só pelo grafico
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999,mes, 21));
    }
    
    /**
     * Retorna o Primeiro Nome.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName.get();
    }
    
    /**
     * Define o Primeiro Nome.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    /**
     * Retorna o Primeiro Nome como StringProperty.
     *
     * @return firstName
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    /**
     * Retorna o Último Nome.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName.get();
    }
    
    /**
     * Define o Último Nome.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    /**
     * Retorna o Último Nome como StringProperty.
     *
     * @return firstName
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    /**
     * Retorna a Rua.
     *
     * @return street
     */
    public String getStreet() {
        return street.get();
    }
    
    /**
     * Define a Rua.
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street.set(street);
    }
    
    /**
     * Retorna a Rua como StringProperty.
     *
     * @return street
     */
    public StringProperty streetProperty() {
        return street;
    }
    
    /**
     * Retorna o CEP.
     *
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode.get();
    }
    
    /**
     * Define o CEP.
     *
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }
    
    /**
     * Retorna o CEP como IntegerProperty.
     *
     * @return postalCode
     */
    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }
    
    /**
     * Retorna a Cidade.
     *
     * @return city
     */
    public String getCity() {
        return city.get();
    }
    
    /**
     * Define a Cidade.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city.set(city);
    }
    
    /**
     * Retorna a Cidade como StringProperty.
     *
     * @return city
     */
    public StringProperty cityProperty() {
        return city;
    }
    
    /**
     * Retorna a Data de Aniversário.
     *
     * @return firstName
     */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }
    
    /**
     * Define a Data de Aniversário.
     *
     * @param birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }
    
    /**
     * Retorna a Data de Aniversário como ObjectProperty.
     *
     * @return firstName
     */
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
}