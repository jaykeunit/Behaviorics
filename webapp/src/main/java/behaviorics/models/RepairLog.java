package behaviorics.models;

import java.sql.Date;

public class RepairLog {
    private int id;
    private int cameraID;
    private Date dateFailed;
    private String repairStatus;
    private String failReason;
    private Date dateRepaired;
    private Date repairRequestDate;

    public RepairLog() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setCameraID(int cameraID) {
        this.cameraID = cameraID;
    }

    public void setDateFailed(Date dateFailed) {
        this.dateFailed = dateFailed;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public void setDateRepaired(Date dateRepaired) {
        this.dateRepaired = dateRepaired;
    }

    public void setRepairRequestDate(Date repairRequestDate) {
        this.repairRequestDate = repairRequestDate;
    }

    public RepairLog(int id, int cameraID, Date dateFailed, String repairStatus, String failReason, Date dateRepaired, Date repairRequestDate) {
        this.id = id;
        this.cameraID = cameraID;
        this.dateFailed = dateFailed;

        this.repairStatus = repairStatus;
        this.dateRepaired = dateRepaired;
        this.failReason = failReason;
        this.repairRequestDate = repairRequestDate;
    }

    public int getId() {
        return id;
    }

    public int getCameraID() {
        return cameraID;
    }

    public Date getDateFailed() {
        return dateFailed;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public Date getDateRepaired() {
        return dateRepaired;
    }

    public Date getRepairRequestDate() {
        return repairRequestDate;
    }

    @Override
    public String toString() {
        return "id: " + id + ", "
                + "cameraID: " + cameraID + ", "
                //+ "dateFailed: " + dateFailed + ", "
                + "repairStatus: " + repairStatus + ", "
                //+ "dateRepaired: " + dateRepaired + ", "
                + "failReason: " + failReason;
                //+ "repairRequestDate: " + repairRequestDate;
    }

}
