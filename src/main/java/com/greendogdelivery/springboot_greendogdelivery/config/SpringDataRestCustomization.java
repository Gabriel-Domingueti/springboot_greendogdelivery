package com.greendogdelivery.springboot_greendogdelivery.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;
import com.greendogdelivery.springboot_greendogdelivery.domain.Item;
import com.greendogdelivery.springboot_greendogdelivery.domain.Pedido;

@Component 
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Expõe o campo "id" no JSON para estas entidades
        config.exposeIdsFor(Item.class, Cliente.class, Pedido.class);
    }
    
}
