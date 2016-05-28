package behaviorics.httpRequests;

import behaviorics.models.Entity;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class EntityRequests extends HttpRequests {

    public static Entity getEntityByEntityName(String name) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "entity", name));
        return mapper.readValue(response, Entity.class);
    }

    public static List<Entity> getAllEntitiesByOrganizationID(int organizationId) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "entity/organizationID", Integer.toString(organizationId)));
        return mapper.readValue(response, new TypeReference<List<Entity>>() {});
    }

    public static String createEntity(Entity entity) throws IOException {

        String entityJson = mapper.writeValueAsString(entity);
        return makePostRequest(String.format("%s/%s", restEndpoint, "entity"), entityJson);
    }

    public static String updateEntityById(Entity entity) throws IOException {

        String entityJson = mapper.writeValueAsString(entity);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "entity", entity.getID()), entityJson);
    }

    public static String deleteEntityById(int id) throws Exception {

        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "entity", Integer.toString(id)));
    }

}
