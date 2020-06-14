package com.example.demo;

//import com.techprimers.spring_boot_soap_example.GetidRequest;
//import com.techprimers.spring_boot_soap_example.GetidResponse;


import io.spring.guides.gs_producing_web_service.GetidRequest;
import io.spring.guides.gs_producing_web_service.GetidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CoffeEndPoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private restService serviceProviderOfRest;

    @Autowired
    public CoffeEndPoint(restService ss) {
        this.serviceProviderOfRest = ss;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getidRequest")
    @ResponsePayload
    public GetidResponse getCoffe(@RequestPayload GetidRequest request) {
        GetidResponse response = new GetidResponse();
        response.setCoffe(serviceProviderOfRest.findCoffe(request.getId()));
        System.out.println(serviceProviderOfRest.findCoffe(request.getId()));
        return response;
    }

}
