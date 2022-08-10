package org.daemio.merch.service;

import java.util.ArrayList;
import java.util.List;

import org.daemio.merch.model.Merch;
import org.daemio.merch.repository.MerchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MerchService {

    @Autowired
    private MerchRepository repo;
    
    public List<Merch> getMerchList() {
        log.info("Getting a merch list from data");

        List<Merch> list = new ArrayList<>();

        repo.findAll().forEach(list::add);

        return list;
    }
}
