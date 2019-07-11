package com.example.mySource.abstractclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class AbstractComponent {
    protected static final Logger log = LoggerFactory.getLogger(AbstractComponent.class);
    
    @Value("${client.origin}") 
	protected String clientOrigin;
}