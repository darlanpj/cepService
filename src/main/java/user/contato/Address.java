package user.contato;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by darlanpj on 28/03/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable{

    @Id
    public String id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String uf;


    public Address(String id, String cep, String logradouro, String bairro, String cidade, String uf) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    /**
     * Valida se é um CEP.
     *
     * @param cep CEP
     * @throws InvalidCepException Caso o CEP seja inválido
     */
    public static void validateCep(String cep) throws InvalidCepException{
        if(cep==null || !cep.matches("[0-9]{8}")){
            throw new InvalidCepException(cep);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getId() != null ? !getId().equals(address.getId()) : address.getId() != null) return false;
        if (getCep() != null ? !getCep().equals(address.getCep()) : address.getCep() != null) return false;
        if (getLogradouro() != null ? !getLogradouro().equals(address.getLogradouro()) : address.getLogradouro() != null)
            return false;
        if (getBairro() != null ? !getBairro().equals(address.getBairro()) : address.getBairro() != null) return false;
        if (getCidade() != null ? !getCidade().equals(address.getCidade()) : address.getCidade() != null) return false;
        return getUf() != null ? getUf().equals(address.getUf()) : address.getUf() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCep() != null ? getCep().hashCode() : 0);
        result = 31 * result + (getLogradouro() != null ? getLogradouro().hashCode() : 0);
        result = 31 * result + (getBairro() != null ? getBairro().hashCode() : 0);
        result = 31 * result + (getCidade() != null ? getCidade().hashCode() : 0);
        result = 31 * result + (getUf() != null ? getUf().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
