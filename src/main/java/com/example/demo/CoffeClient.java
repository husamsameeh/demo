package com.example.demo;

import io.spring.guides.gs_producing_web_service.GetidRequest;
import io.spring.guides.gs_producing_web_service.GetidResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CoffeClient extends WebServiceGatewaySupport {

    public GetidResponse getByid(int id) {
        GetidRequest request = new GetidRequest();
        request.setId(id);
        try{
        GetidResponse response = (GetidResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetidRequest"));
            return response;
        }
catch(NullPointerException e)
{
    return null;
}

    }

}
