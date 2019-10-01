package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe auxiliar para envolver uma lista de pessoas. 
 * Tem como responsabilidade única salvar a lista de pessoas em XML.
 * 
 * @author Sávio Cardoso, Marco Jakob
 * @version 2.0
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;

    /**
     * Retorna uma Lista de Pessoas.
     *
     * @return persons
     */
    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Define uma Lista de Pessoas.
     *
     * @param persons
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}