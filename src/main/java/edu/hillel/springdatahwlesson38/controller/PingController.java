package edu.hillel.springdatahwlesson38.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private static final Logger LOG = LoggerFactory.getLogger(PingController.class);

    @GetMapping("/ping")
    public String ping() {
        LOG.info("Call ping() method and returned OK.");
        return "OK";
    }
}
