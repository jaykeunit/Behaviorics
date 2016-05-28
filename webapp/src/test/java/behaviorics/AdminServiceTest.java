package behaviorics;

import behaviorics.httpRequests.*;
import behaviorics.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BuildingRequests.class, EntityRequests.class, FloorRequests.class, UserRequests.class, StateRequests.class, ManagementService.class})
public class AdminServiceTest {
    AdminService service;

    @Before
    public void setUp() throws Exception {
        service = new AdminService();
    }

    @Test
    public void testGetEntities() throws Exception {
        Entity entity1 = new Entity(1, 1, "TestEntity", "7137432255","TTT");
        Entity entity2 = new Entity(1, 1, "TestEntity", "7137432255","AAA");
        List<Entity> entities = Arrays.asList(entity1 ,entity2);

        PowerMockito.stub(method(EntityRequests.class, "getAllEntitiesByOrganizationID")).toReturn(entities);

        assertEquals(entities, service.getEntities());
    }

    @Test
    public void testGetBuildings() throws Exception {
        Building building1 = new Building(1, "TestBuilding1", 10, 1234, "University Drive", "City", 77004, "AAA", "TX");
        Building building2 = new Building(1, "TestBuilding2", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");
        List<Building> buildings = Arrays.asList(building1 ,building2);

        PowerMockito.stub(method(BuildingRequests.class, "getAllBuildingsByOrganizationID")).toReturn(buildings);

        assertEquals(buildings, service.getAllBuildingsByOrganizationID());
    }

    @Test
    public void testGetAllFloorsForAnOrganization() throws Exception {
        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getAllFloorsForAnOrganization")).toReturn(outOfOrderFloors);

        assertEquals(Arrays.asList(floor1, floor2), service.getAllFloorsForAnOrganization());
    }

    @Test
    public void testGetAllUsersByOrganizationID() throws Exception {
        User user1 = new User(1, "username", "admin", 1, "University");
        User user2 = new User(1, "username", "admin", 1, "University");
        List<User> userList = Arrays.asList(user1 ,user2);

        PowerMockito.stub(method(UserRequests.class, "getAllUsersByOrganizationID")).toReturn(userList);

        assertEquals(Arrays.asList(user1, user2), service.getAllUsersByOrganizationID());
    }

    @Test
    public void testGetStates() throws Exception {
        State state1 = new State(1, "Texas", "TX");
        State state2 = new State(2, "New York", "NY");
        List<State> stateList = Arrays.asList(state1 ,state2);

        PowerMockito.stub(method(StateRequests.class, "getAllStates")).toReturn(stateList);

        assertEquals(Arrays.asList(state1, state2), service.getStates());
    }

    @Test
    public void testGetCompletedUser(){
        User user = new User(1, "username", "admin", 1, "University");
        user.setKey("password");

        assertNotNull(service.getCompletedUser(user).getSalt());
    }
}
