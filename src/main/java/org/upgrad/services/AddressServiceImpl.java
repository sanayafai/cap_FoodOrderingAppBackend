package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Address;
import org.upgrad.models.States;
import org.upgrad.repositories.AddressRepository;
import org.upgrad.repositories.StateRepository;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    private final StateRepository stateRepository;
    private final AddressRepository addressRepository;

    public AddressServiceImpl (StateRepository stateRepository, AddressRepository addressRepository) {
        this.stateRepository = stateRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<States> getAllStates(){
        return stateRepository.getAllStates();
    }

    @Override
    public void addAddress (String flatBuilNumber, String locality, String city, String zipcode, Integer stateId) {
        addressRepository.addAddress(flatBuilNumber, locality, city, zipcode, stateId);
    }

    @Override
    public void userAddressMapping (String type, Integer userId, Integer addressId) {
        addressRepository.userAddressMapping(type, userId, addressId);
    }

    //This method will fetch the id for the latest entry in the address table;
    @Override
    public int findIdForLatestAddress() {
        return addressRepository.findIdForLatestAddress();
    }

    @Override
    public Address findAddressbyId(int addressId) {
        return addressRepository.findAddressbyId(addressId);
    }

    @Override
    public void updatePermAddress (String flatBuilNumber, String locality, String city, String zipcode, Integer stateId, Integer addressId) {
        addressRepository.updatePermAddress(flatBuilNumber, locality, city, zipcode, stateId, addressId);
    }

    @Override
    public List<Integer> getAllPermAddIdByUser (int userId) {
        return addressRepository.getAllPermAddIdByUser(userId);
    }
}