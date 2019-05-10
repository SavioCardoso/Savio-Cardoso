package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe auxiliar para envolver uma lista de pessoas. 
 * Tem como responsabilidade única salvar a lista de pessoas em XML.
 * 
 * @author Sávio Cardoso, Marco Jakob
 * @version 1.5
 */
@XmlRootElement(name = "pessoas")
public class PessoaOrganizadorLista {

    private List<Pessoa> pessoas;

    @XmlElement(name = "pessoa")
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}