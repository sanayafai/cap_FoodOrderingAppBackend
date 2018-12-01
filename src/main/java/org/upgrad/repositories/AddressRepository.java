package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Address;

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
}
