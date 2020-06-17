package com.example.demo.endpoint;

//import com.techprimers.spring_boot_soap_example.GetidRequest;
//import com.techprimers.spring_boot_soap_example.GetidResponse;


import com.example.demo.Constants;
import com.example.demo.services.RestService;
import com.example.GetidRequest;
import com.example.GetidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CoffeEndPoint {

    private static final String NAMESPACE_URI = Constants.NAMESPACE;

    private RestService serviceProviderOfRest;

    @Autowired
    public CoffeEndPoint(RestService ss) {
        this.serviceProviderOfRest = ss;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = Constants.GET_ID_REQUEST)
    @ResponsePayload
    public GetidResponse getCoffe(@RequestPayload GetidRequest request) {
        GetidResponse response = new GetidResponse();
        response.setCoffe(serviceProviderOfRest.findCoffe(request.getId()));
        System.out.println(serviceProviderOfRest.findCoffe(request.getId()));
        return response;
    }

}
