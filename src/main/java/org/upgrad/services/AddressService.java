package org.upgrad.services;

import org.upgrad.models.Address;
import org.upgrad.models.States;

import java.util.List;

public interface AddressService {

    List<States> getAllStates();

    void addAddress (String flatBuilNumber, String locality, String city, String zipcode, Integer stateId);

    void userAddressMapping (String type, Integer userId, Integer addressId);

    //This method will fetch the id for the latest entry in the address table;
    int findIdForLatestAddress();

}
