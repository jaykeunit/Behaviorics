package behaviorics.models;

public class RepairRequest {
    private int cameraID;
    private String failReason;

    public RepairRequest() {}

    public RepairRequest(int cameraID, String failReason) {
        this.cameraID = cameraID;
        this.failReason = failReason;
    }

    public int getCameraID() {
        return cameraID;
    }

    public void setCameraID(int cameraID) {
        this.cameraID = cameraID;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }


}
