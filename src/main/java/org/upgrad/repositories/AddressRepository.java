package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Address;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "INSERT INTO ADDRESS(flat_buil_number, locality, city, zipcode, state_id) VALUES(?1,?2,?3,?4,?5)")
    void addAddress(String flatBuilNumber, String locality, String city, String zipcode, Integer stateId);

    @Query(nativeQuery = true, value = "SELECT ID FROM ADDRESS ORDER BY ID DESC LIMIT 1")
    int findIdForLatestAddress();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "INSERT INTO USER_ADDRESS(type, user_id, address_id) VALUES(?1,?2,?3)")
    void userAddressMapping (String type, Integer userId, Integer addressId);

    @Query(nativeQuery = true, value = "SELECT * FROM ADDRESS WHERE ID=?1" )
    Address findAddressbyId(int addressId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE ADDRESS SET flat_buil_number=?1, locality=?2, city=?3, zipcode=?4, state_id=?5 WHERE id=?6")
    void updatePermAddress (String flatBuilNumber, String locality, String city, String zipcode, Integer stateId, Integer addressId);

    @Query (nativeQuery = true, value = "SELECT ADDRESS_ID FROM USER_ADDRESS WHERE TYPE LIKE '%perm%' AND USER_ID=?1")
    List<Integer> getAllPermAddIdByUser (int userId);
}