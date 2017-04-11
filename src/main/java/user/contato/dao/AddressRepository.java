package user.contato.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import user.contato.model.Address;

import java.util.List;

/**
 * Created by darlanpj on 10/04/2017.
 * ${EMAIL}
 */
public class AddressRepository {

    public static final String PERSON_ADDRESS_COLLECTION_NAME = "address";

    @Autowired
    private MongoTemplate mongo;


    public void addAddress(Address address){
        if(!mongo.collectionExists(Address.class)){
            mongo.createCollection(Address.class);
        }
        mongo.insert(address, PERSON_ADDRESS_COLLECTION_NAME);
    }


    public Address getAddressByCep(String cep) {
        return mongo.findOne(Query.query(Criteria.where("cep").is(cep)), Address.class, PERSON_ADDRESS_COLLECTION_NAME);
    }

    public List<Address> getAllAddress() {
        return mongo.findAll(Address.class, PERSON_ADDRESS_COLLECTION_NAME);
    }

    public Address deletePerson(String cep) {
        Address address = mongo.findOne(Query.query(Criteria.where("cep").is(cep)), Address.class, PERSON_ADDRESS_COLLECTION_NAME);
        mongo.remove(address, PERSON_ADDRESS_COLLECTION_NAME);

        return address;
    }

    public Address updateAddress(String cep, Address address) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cep").is(cep));

        Update update = new Update();
        update.set("cp", address.getCep());
        update.set("logradouro", address.getLogradouro());
        update.set("bairro", address.getBairro());
        update.set("cidade", address.getCidade());
        update.set("uf", address.getUf());

        mongo.updateFirst(query, update, Address.class);

        return address;
    }

}
