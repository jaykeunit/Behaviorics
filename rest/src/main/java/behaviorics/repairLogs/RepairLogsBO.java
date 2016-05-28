package behaviorics.repairLogs;

import java.sql.Date;

public class RepairLogsBO {

    private int id;
    private int cameraID;
    private Date dateFailed;
    private String repairStatus;
    private String failReason;
    private Date dateRepaired;
    private Date repairRequestDate;

    public RepairLogsBO() {}

    public RepairLogsBO(int id, int cameraID, Date dateFailed, String repairStatus, String failReason, Date dateRepaired, Date repairRequestDate) {
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
        return "RepairLogsBO{" +
                "id=" + id +
                ", cameraID=" + cameraID +
                ", dateFailed=" + dateFailed +
                ", repairStatus='" + repairStatus + '\'' +
                ", failReason='" + failReason + '\'' +
                ", dateRepaired=" + dateRepaired +
                ", repairRequestDate=" + repairRequestDate +
                '}';
    }
}
