package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Address;
import org.upgrad.models.States;
import org.upgrad.services.AddressService;
import org.upgrad.services.UserAuthTokenService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    /*
     * This endpoint is to get all states.
     */

    @GetMapping("/states")
    @CrossOrigin
    public ResponseEntity<?> allStates() {
        return new ResponseEntity<>(addressService.getAllStates(), HttpStatus.OK);
    }


    /*
     * This endpoint is to add new address.
     * Requires authentication.
     * Updates both address and user_address table.
     */
    @PostMapping("/address")
    @CrossOrigin
    public ResponseEntity<?> addAddress (@RequestParam String flatBuilNo, @RequestParam String locality, @RequestParam String city, @RequestParam String zipcode, String type, @RequestParam Integer stateId, @RequestHeader String accessToken) {
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            // Only setting temp OR perm values if the user is authenticated.
            if (type==null || !type.equals("perm")) {
                type = "temp";
            } else {
                type = "perm";
            }
            // Checking if zip code is valid.
            if (!isZipcodeValid(zipcode) || zipcode.length()!=6) {
                return new ResponseEntity<>("Invalid zipcode!", HttpStatus.BAD_REQUEST);
            // Checking if state id exists.
            } else if (!isStateIdValid(stateId)) {
                return new ResponseEntity<>("No state by this state id!", HttpStatus.BAD_REQUEST);
            } else {
                //Adding new address to the address table.
                addressService.addAddress(flatBuilNo, locality, city, zipcode, stateId);
                //Finding the Id for the latest entry in address table.
                int latestAddressId = addressService.findIdForLatestAddress();
                //Fetching userId for the current user.
                int userId = userAuthTokenService.getUserId(accessToken);
                //Adding type, user id and address id to the user_address table;
                addressService.userAddressMapping(type, userId, latestAddressId);
                return new ResponseEntity<>("Address has been saved successfully!", HttpStatus.CREATED);
            }
        }
    }

    //Method to check if a zip code is a valid 6 digit number.
    public static boolean isZipcodeValid (String zipcode) {
        try {
            Integer.parseInt(zipcode);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    //Method to check if a state id getting passed is available in DB.
    public Boolean isStateIdValid (Integer stateId) {
        List <States> states = addressService.getAllStates();
        for (States state: states) {
            if (state.getId()==stateId) {
                return true;
            }
        }
        return false;
    }
}