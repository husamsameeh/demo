package com.example.demo.clients;

import com.example.demo.Constants;
import com.example.GetidRequest;
import com.example.GetidResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CoffeClient extends WebServiceGatewaySupport {

    public GetidResponse getByid(int id) {
        GetidRequest request = new GetidRequest();
        request.setId(id);
        try{
        GetidResponse response = (GetidResponse) getWebServiceTemplate()
                .marshalSendAndReceive(Constants.HTTP + Constants.LOCALHOST+"/"+Constants.WS, request,
                        new SoapActionCallback(Constants.NAMESPACE+"/"+Constants.GET_ID_REQUEST));
            return response;
        }
catch(NullPointerException e)
{
    return null;
}

    }
}
