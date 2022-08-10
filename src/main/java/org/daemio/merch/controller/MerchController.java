package org.daemio.merch.controller;

import java.util.List;

import org.daemio.merch.dto.MerchListResponse;
import org.daemio.merch.model.Merch;
import org.daemio.merch.service.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/merch")
@RestController
@Slf4j
public class MerchController {
    
    @Autowired
    private MerchService merchService;

    @GetMapping
    public ResponseEntity<MerchListResponse> getMerchList() {
        log.info("Getting some merch");

        List<Merch> list = merchService.getMerchList();
        
        MerchListResponse response = new MerchListResponse();
        response.setMerch(list);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{merchId}")
    public ResponseEntity<Merch> getMerchItem(@PathVariable int merchId) {
        log.info("Getting piece of merch");

        Merch merch = merchService.getMerch(merchId);

        return ResponseEntity.ok(merch);
    }
}
