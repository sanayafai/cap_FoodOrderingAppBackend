package org.upgrad.services;

import org.upgrad.models.Address;
import org.upgrad.models.States;

import java.util.List;

/**
 * This AddressService interface gives the list of all the service that exist in the address  service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface AddressService {

    List<States> getAllStates();

    void addAddress(String flatBuilNumber, String locality, String city, String zipcode, Integer stateId);

    void userAddressMapping(String type, Integer userId, Integer addressId);

    //This method will fetch the id for the latest entry in the address table;
    int findIdForLatestAddress();

    Boolean getAddress(int addressId);

    void updatePermAddress(String flatBuilNumber, String locality, String city, String zipcode, Integer stateId, Integer addressId);

    List<Address> getPermAddress(int userId);

    void updatePermAddressWithoutState(String flatBuilNumber, String locality, String city, String zipcode, Integer addressId);

    Boolean deletePermAddressById(int addressId);

}
