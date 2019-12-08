package com.springboot.learning;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
  //  private static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);
    private final Environment env;

    public Initializer(final Environment env) {
        this.env = env;
    }

    /**
     * Success: Method for initializing CrossLookup
     *
     * @throws :
     *           e - Exception object
     */
    @PostConstruct
    public void init() {
        try {
            /*LoggerHandler.setUpLoggerHandler(env.getProperty("log.appID"), env.getProperty("log.svcName"),
                    env.getProperty("log.componentName"), env.getProperty("log.svcVersion"));*/
        }
        catch (final RuntimeException e) {
         //   LOGGER.error("Error initializing!", e);
            throw e;
        }
    }

}
