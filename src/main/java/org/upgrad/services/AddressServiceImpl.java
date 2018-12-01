package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.States;
import org.upgrad.repositories.StateRepository;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    private final StateRepository stateRepository;

    public AddressServiceImpl (StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<States> getAllStates(){
        return stateRepository.getAllStates();
    }
}