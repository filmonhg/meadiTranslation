package Handler;

import java.io.IOException;
import java.util.ArrayList;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.WordResponse;

public class TWordDetailsResponse {


    private ArrayList<WordResponse> responses = new ArrayList<WordResponse>();

    public void addItem(WordResponse wordResponse) throws IOException
    {
        responses.add(wordResponse);
    }

    public ArrayList<WordResponse> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<WordResponse> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "TWordDetailsResponse{" +
                "responses=" + responses +
                '}';
    }


    /*    private ArrayList<TWordDetailsRequest> response = new ArrayList<TWordDetailsRequest>();


    public void addItem(Item item) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TWordDetailsRequest tWordDetailsRequest= new TWordDetailsRequest();
        tWordDetailsRequest = objectMapper.readValue(item.toJSON(),TWordDetailsRequest.class);
        response.add(tWordDetailsRequest);
    }
    public ArrayList<TWordDetailsRequest> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<TWordDetailsRequest> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "TWordDetailsResponse{" +
                "response=" + response +
                '}';
    }*/
}
