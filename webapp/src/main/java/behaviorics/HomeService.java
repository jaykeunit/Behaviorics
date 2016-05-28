package behaviorics;

import behaviorics.httpRequests.RepairLogRequests;
import behaviorics.models.Camera;
import behaviorics.models.RepairLog;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HomeService {

    public List<Camera> getOperationalCameras(List<Camera> list){
        return list.stream().filter(new Predicate<Camera>() {
            @Override
            public boolean test(Camera camera) {
                return camera.getCameraStatus().equals("Working");
            }
        }).collect(Collectors.toList());
    }

    public List<Camera> getRepairRequestedCameras(List<Camera> list){
        return list.stream().filter(new Predicate<Camera>() {
            @Override
            public boolean test(Camera camera) {
                return camera.getCameraStatus().equals("UnderRepair");
            }
        }).collect(Collectors.toList());
    }

    public List<Camera> getDamagedCameras(List<Camera> list){
        return list.stream().filter(new Predicate<Camera>() {
            @Override
            public boolean test(Camera camera) {
                return camera.getCameraStatus().equals("Down");
            }
        }).collect(Collectors.toList());
    }

    public List<RepairLog> getActiveRepairs() throws Exception {
        return RepairLogRequests.getActiveRepairs();
    }
}
