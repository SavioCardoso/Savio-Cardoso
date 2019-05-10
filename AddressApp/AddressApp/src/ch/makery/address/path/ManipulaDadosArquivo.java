/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template arquivo, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.path;

import ch.makery.address.model.Pessoa;
import ch.makery.address.model.PessoaOrganizadorLista;
import java.io.File;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Sávio Cardoso
 */
public class ManipulaDadosArquivo {
    
    private CaminhoArquivo caminho;
    private ObservableList<Pessoa> pessoa;
    
    public ManipulaDadosArquivo(CaminhoArquivo caminho, ObservableList<Pessoa> pessoa){
        this.caminho=caminho;
        this.pessoa = pessoa;
    }
    /**
    * Salva os dados da pessoa atual no arquivo especificado.
    * 
    * @param arquivo
    */
    public void salvaDadosPessoaParaArquivo(File arquivo) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PessoaOrganizadorLista.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Envolvendo nossos dados da pessoa.
            PessoaOrganizadorLista wrapper = new PessoaOrganizadorLista();
            wrapper.setPessoas(pessoa);

            // Enpacotando e salvando XML  no arquivo.
            m.marshal(wrapper, arquivo);

            // Salva o caminho do arquivo no registro.
            caminho.setCaminhoArquivoPessoa(arquivo);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Erro")
                    .masthead("Não foi possível salvar os dados do arquivo:\n" 
                              + arquivo.getPath()).showException(e);
        }
    }
    /**
    * Carrega os dados da pessoa do arquivo especificado. A pessoa atual
    * será substituída.
    * 
    * @param arquivo
    */
    public void carregaDadosPessoaDoArquivo(File arquivo) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PessoaOrganizadorLista.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the arquivo and unmarshalling.
            PessoaOrganizadorLista wrapper = (PessoaOrganizadorLista) um.unmarshal(arquivo);

            pessoa.clear();
            pessoa.addAll(wrapper.getPessoas());

            // Save the arquivo path to the registry.
            caminho.setCaminhoArquivoPessoa(arquivo);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Erro")
                    .masthead("Não foi possível carregar dados do arquivo:\n" 
                              + arquivo.getPath()).showException(e);
        }
    }
}
