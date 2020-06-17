package com.example.demo.configurations;

import com.example.demo.clients.CoffeClient;
import com.example.demo.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
@Configuration
public class CoffeConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath(Constants.PACKAGE_NAME);
        return marshaller;
    }

    @Bean
    public CoffeClient countryClient(Jaxb2Marshaller marshaller) {
        CoffeClient client = new CoffeClient();
        client.setDefaultUri(Constants.HTTP+Constants.LOCALHOST+Constants.WS);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
