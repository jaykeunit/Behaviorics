package behaviorics.httpRequests;

import behaviorics.models.Entity;
import behaviorics.models.State;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static behaviorics.HTTPConnection.makeGetRequest;

public class StateRequests extends HttpRequests {

    public static State getStateByName(String name) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "states", name));
        return mapper.readValue(response, State.class);
    }

    public static State getStateByAcronym(String acronym) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "states/acr", acronym));
        return mapper.readValue(response, State.class);
    }

    public static List<State> getAllStates() throws Exception {
        String response = makeGetRequest(String.format("%s/%s", restEndpoint, "states"));
        return mapper.readValue(response, new TypeReference<List<State>>() {});
    }

}
