package org.daemio.merch.controller;

import org.daemio.merch.dto.MerchPage;
import org.daemio.merch.model.Merch;
import org.daemio.merch.service.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/merch")
@Slf4j
public class MerchController {
    
    @Autowired
    private MerchService merchService;

    @GetMapping
    public ResponseEntity<MerchPage> getMerchList(
            @PageableDefault(page = 0, size = 25)
            Pageable pageable) {

        log.info("Getting some merch");

        var page = merchService.getMerchPage(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{merchId}")
    public ResponseEntity<Merch> getMerchItem(@PathVariable int merchId) {
        log.info("Getting piece of merch");

        Merch merch = merchService.getMerch(merchId);

        return ResponseEntity.ok(merch);
    }
}
