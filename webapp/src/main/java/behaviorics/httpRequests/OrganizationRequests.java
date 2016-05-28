package behaviorics.httpRequests;

import behaviorics.models.Organization;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class OrganizationRequests extends HttpRequests {

    public static Organization getOrganizationById(int id) throws Exception{
        String response = makeGetRequest(String.format("%s/%s/id/%s", restEndpoint, "organization", id));
        return mapper.readValue(response, Organization.class);
    }

    public static Organization getOrganizationByName(String name) throws Exception{
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "organization", name));
        return mapper.readValue(response, Organization.class);
    }

    public static List<Organization> getAllOrganizations() throws Exception {

        String response = makeGetRequest(String.format("%s/%s", restEndpoint, "organization"));
        return mapper.readValue(response, new TypeReference<List<Organization>>() {});
    }

    public static String createOrganization(Organization organization) throws IOException {

        String buildingJson = mapper.writeValueAsString(organization);
        return makePostRequest(String.format("%s/%s", restEndpoint, "organization"), buildingJson);
    }

    public static String updateOrganization(Organization organization) throws IOException {

        String buildingJson = mapper.writeValueAsString(organization);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "organization", organization.getId()), buildingJson);
    }

    public static String deleteOrganizationById(int id) throws Exception {

        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "organization", Integer.toString(id)));
    }
}
