package behaviorics.httpRequests;

import behaviorics.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class UserRequests extends HttpRequests {

    public static User getUserByUsername(String username) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "users", username));
        return mapper.readValue(response, User.class);
    }

    public static String createUser(User user) throws IOException {

        String userJson = mapper.writeValueAsString(user);
        return makePostRequest(String.format("%s/%s", restEndpoint, "users"), userJson);
    }

    public static List<User> getAllUsersByOrganizationID(int organizationId) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "users/organizationID", Integer.toString(organizationId)));
        return mapper.readValue(response, new TypeReference<List<User>>() {});
    }

    public static String updateUserById(User user) throws IOException {

        String userJson = mapper.writeValueAsString(user);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "users", user.getId()), userJson);
    }

    public static String deleteUserById(int id) throws Exception {

        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "users", Integer.toString(id)));
    }

}
