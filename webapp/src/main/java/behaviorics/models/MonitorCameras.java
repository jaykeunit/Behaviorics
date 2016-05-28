package behaviorics.models;

public class MonitorCameras {

        private int organizationID;
        private int entityID;
        private String entityName;
        private int buildingID;
        private String buildingName;
        private int floorID;
        private int floorNumber;

        public MonitorCameras(){}

        public MonitorCameras(int _organizationID, int _entityID, String _entityName, int _buildingID,
                                String _buildingName, int _floorID, int _floorNumber){

            organizationID = _organizationID;
            entityID = _entityID;
            entityName = _entityName;
            buildingID = _buildingID;
            buildingName = _buildingName;
            floorID = _floorID;
            floorNumber = _floorNumber;
        }

        public int getOrganizationID() {
            return organizationID;
        }

        public int getEntityID() {
            return entityID;
        }

        public String getEntityName() {
            return entityName;
        }

        public int getBuildingID() {
            return buildingID;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public int getFloorID() {
            return floorID;
        }

        public int getFloorNumber() {
            return floorNumber;
        }

        @Override
        public String toString() {
            return "MonitorCameras{" +
                    "organizationID=" + organizationID +
                    ", entityID=" + entityID +
                    ", entityName='" + entityName + '\'' +
                    ", buildingID=" + buildingID +
                    ", buildingName='" + buildingName + '\'' +
                    ", floorID=" + floorID +
                    ", floorNumber=" + floorNumber +
                    '}';
        }
}
