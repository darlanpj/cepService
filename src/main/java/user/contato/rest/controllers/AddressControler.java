package user.contato.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import user.contato.dao.AddressRepository;
import user.contato.model.Address;
import user.contato.rest.responses.BasicResponse;
import user.contato.rest.responses.MultipleAddressResponse;
import user.contato.rest.responses.SingleAddressResponse;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by darlanpj on 11/04/2017.
 * ${EMAIL}
 */
@Controller
public class AddressControler {

    private static final Logger logger = LoggerFactory.getLogger(AddressControler.class);

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("This is Default Home API CepService page.\n\n The client locale is {}.", locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "status";
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    @ResponseBody
    public MultipleAddressResponse getAllPersons() {
        logger.info("getAllPersons()...");
        List<Address> address = addressRepository.getAllAddress();
        MultipleAddressResponse resp = new MultipleAddressResponse(true, address);
        logger.info("...getAllPersons()");
        return resp;
    }

    @RequestMapping(value = "/address/{cep}", method = RequestMethod.GET)
    @ResponseBody
    public SingleAddressResponse getAddressByCep(@PathVariable("cep") String cep) {
        logger.info("getAddressByCep()...");
        Address address = addressRepository.getAddressByCep(cep);
        if (address != null) {
            logger.info("returned: " + address.toString());
        } else {
            logger.info("cep: " + cep + ", NOT FOUND!");
        }
        SingleAddressResponse resp = new SingleAddressResponse(true, address);
        logger.info("...getAddressByCep()");
        return resp;
    }

    @RequestMapping(value = "/address/delete/{cep}", method = RequestMethod.DELETE)
    @ResponseBody
    public BasicResponse deleteAddressByCep(@PathVariable("cep") String cep) {
        logger.info("deleteAddressByCep()...");
        BasicResponse resp;
        Address address = addressRepository.getAddressByCep(cep);
        if (address != null) {
            logger.info("deleted: " + address.toString());
            resp = new BasicResponse(true, "Successfully deleted Adrdess: " + address.toString());
        } else {
            logger.info("cep: " + cep + ", NOT FOUND!");
            resp = new BasicResponse(false, "Failed to delete name: " + cep);
        }
        logger.info("...deleteAddressByCep()");
        return resp;
    }

    @RequestMapping(value = "/address/update/{cep}", method = RequestMethod.PUT)
    @ResponseBody
    public BasicResponse updateAddressByCep(@PathVariable("cep") String cep, @ModelAttribute("address") Address address) {
        logger.info("updatePersonByName()...");
        BasicResponse resp;
        Address address1 = addressRepository.updateAddress(cep, address);
        if (address1 != null) {
            logger.info("updated: " + address1.toString());
            resp = new BasicResponse(true, "Successfully updated Address: " + address1.toString());
        } else {
            logger.info("cep: " + cep + ", NOT FOUND!");
            resp = new BasicResponse(false, "Failed to update Address: " + cep);
        }
        logger.info("...updatePersonByName()");
        return resp;
    }

    @RequestMapping(value = "/person/addPerson", method = RequestMethod.POST)
    @ResponseBody
    public BasicResponse addAddress(@RequestBody Address address) {
        logger.info("addAddress()...");
        BasicResponse resp;

          /*  if(address.validateCep(address.getCep()){

            }*/
            logger.info("adding: " + address.toString());
            addressRepository.addAddress(address);
            resp = new BasicResponse(true, "Successfully added Address: " + address.getCep());

            logger.info("Failed to insert...");
            resp = new BasicResponse(false, "Failed to insert...");

        logger.info("..addAddress()");
        return resp;
    }
}
