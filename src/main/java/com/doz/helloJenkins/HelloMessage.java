package com.doz.helloJenkins;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class HelloMessage {

    private static final Logger LOGGER = Logger.getLogger(HelloMessage.class.getName());

    @PostConstruct
    public void hello() {
        LOGGER.info("Hello Jenkins");
    }
}
