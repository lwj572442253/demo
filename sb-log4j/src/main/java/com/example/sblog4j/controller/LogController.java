package com.example.sblog4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/console")
    public void consoleLog(){
        logger.debug("debug");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
    }
}
