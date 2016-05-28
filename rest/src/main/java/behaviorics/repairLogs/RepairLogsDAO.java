package behaviorics.repairLogs;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairLogsDAO {

    public RepairLogsBO getRepairLogByRepairLogID(int repairLogID) throws Exception {

        int id;
        int cameraID;
        Date dateFailed;
        String repairStatus;
        String failReason;
        Date dateRepaired;
        Date repairRequestDate;

        String query = "SELECT * FROM RepairLogs WHERE repairID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, repairLogID);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("repairID");
                cameraID = rs.getInt("cameraID");
                dateFailed = rs.getDate("dateFailed");
                repairStatus = rs.getString("repairStatus");
                failReason = rs.getString("failReason");
                dateRepaired = rs.getDate("dateRepaired");
                repairRequestDate = rs.getDate("repairRequestDate");
            }

        }
        return new RepairLogsBO(id, cameraID, dateFailed, repairStatus, failReason, dateRepaired, repairRequestDate);
    }

    public List<RepairLogsBO> getAllRepairLogsByCameraID(int entityID) throws Exception {

        int id;
        int cameraID;
        Date dateFailed;
        String repairStatus;
        String failReason;
        Date dateRepaired;
        Date repairRequestDate;

        List<RepairLogsBO> repairLogsBOList = new ArrayList<>();

        String query = "SELECT * FROM RepairLogs WHERE cameraID = ? ORDER BY dateFailed DESC, repairStatus DESC;";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, entityID);

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("repairID");
                    cameraID = rs.getInt("cameraID");
                    dateFailed = rs.getDate("dateFailed");
                    repairStatus = rs.getString("repairStatus");
                    failReason = rs.getString("failReason");
                    dateRepaired = rs.getDate("dateRepaired");
                    repairRequestDate = rs.getDate("repairRequestDate");

                    repairLogsBOList.add(new RepairLogsBO(id, cameraID, dateFailed, repairStatus, failReason, dateRepaired, repairRequestDate));
                }
            }
        }

        return repairLogsBOList;
    }

    public List<RepairLogsBO> getActiveRepairs() throws Exception {

        int id;
        int cameraID;
        Date dateFailed;
        String repairStatus;
        String failReason;
        Date dateRepaired;
        Date repairRequestDate;

        List<RepairLogsBO> repairLogsBOList = new ArrayList<>();

        String query = "SELECT * FROM RepairLogs WHERE repairStatus = ? OR repairStatus = ?";
        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, "Down");
            pstmt.setString(2, "UnderRepair");

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    id = rs.getInt("repairID");
                    cameraID = rs.getInt("cameraID");
                    dateFailed = rs.getDate("dateFailed");
                    repairStatus = rs.getString("repairStatus");
                    failReason = rs.getString("failReason");
                    dateRepaired = rs.getDate("dateRepaired");
                    repairRequestDate = rs.getDate("repairRequestDate");

                    repairLogsBOList.add(new RepairLogsBO(id, cameraID, dateFailed, repairStatus, failReason, dateRepaired, repairRequestDate));
                }
            }
        }

        return repairLogsBOList;
    }
    public void createRepairLog(RepairLogsBO repairLogsBO) throws Exception {

        String query = "INSERT INTO RepairLogs (cameraID, dateFailed, repairStatus, failReason, dateRepaired, repairRequestDate)  VALUES (?, ?, ?, ?, ?, ?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, repairLogsBO.getCameraID());
            pstmt.setDate(2, repairLogsBO.getDateFailed());
            pstmt.setString(3, repairLogsBO.getRepairStatus());
            pstmt.setString(4, repairLogsBO.getFailReason());
            pstmt.setDate(5, repairLogsBO.getDateRepaired());
            pstmt.setDate(6, repairLogsBO.getRepairRequestDate());

            pstmt.executeUpdate();
        }
    }

    public void updateRepairLogById(int id, RepairLogsBO repairLogsBO) throws Exception {

        String query = "UPDATE RepairLogs SET cameraID=?, dateFailed=?, repairStatus=?, failReason=?, dateRepaired=?, repairRequestDate=? WHERE repairID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, repairLogsBO.getCameraID());
            pstmt.setDate(2, repairLogsBO.getDateFailed());
            pstmt.setString(3, repairLogsBO.getRepairStatus());
            pstmt.setString(4, repairLogsBO.getFailReason());
            pstmt.setDate(5, repairLogsBO.getDateRepaired());
            pstmt.setDate(6, repairLogsBO.getRepairRequestDate());
            pstmt.setInt(7, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteRepairLogById(int id) throws Exception {

        String query = "DELETE FROM RepairLogs WHERE repairID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
