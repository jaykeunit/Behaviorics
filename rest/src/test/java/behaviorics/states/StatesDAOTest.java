package behaviorics.states;

import behaviorics.BehavioricsDatabaseConnection;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import behaviorics.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StatesDAO.class)
public class StatesDAOTest {

    private StatesBO statesBO;
    private StatesDAO statesDAO;

    @Before
    public void setUp() throws Exception {
        statesDAO = new StatesDAO();
        statesBO = new StatesBO(5,"Texas","TX");

    }

    @Test
    public void testGetStateByStateName() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("stateID")).thenReturn(5);
        Mockito.when(rs.getString("stateName")).thenReturn("Texas");
        Mockito.when(rs.getString("stateAbbr")).thenReturn("TX");

        assertEquals(statesBO.toString(), statesDAO.getStateByStateName("Texas").toString());
    }

    @Test
    public void testGetStateByStateAcronym() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("stateID")).thenReturn(5);
        Mockito.when(rs.getString("stateName")).thenReturn("Texas");
        Mockito.when(rs.getString("stateAbbr")).thenReturn("TX");

        assertEquals(statesBO.toString(), statesDAO.getStateByStateAcronym("TX").toString());
    }

    @Test
    public void testGetAllStates() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("stateID")).thenReturn(1).thenReturn(2).thenReturn(3);
        Mockito.when(rs.getString("stateName")).thenReturn("Texas").thenReturn("Alabama").thenReturn("Florida");
        Mockito.when(rs.getString("stateAbbr")).thenReturn("TX").thenReturn("AL").thenReturn("FL");

        List<StatesBO> test = new ArrayList<>();
        test.add(new StatesBO(1, "Texas","TX"));
        test.add(new StatesBO(2, "Alabama","AL"));
        test.add(new StatesBO(3, "Florida","FL"));

        assertEquals(test.toString(),statesDAO.getAllStates().toString());
    }

}