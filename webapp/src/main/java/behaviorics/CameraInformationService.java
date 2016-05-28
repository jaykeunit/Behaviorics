package behaviorics;

import behaviorics.httpRequests.CameraRequests;
import behaviorics.models.Camera;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class CameraInformationService {

    public static List<Camera> getAllCamerasWithExpiredWarranties() throws Exception {
        List<Camera> cameraWithExpiredWarrantiesList = new ArrayList<>(CameraRequests.getAllCameras());

        cameraWithExpiredWarrantiesList.removeIf(new Predicate<Camera>() {
            @Override
            public boolean test(Camera camera) {

                try {
                    Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(camera.getWarrantyExpiration());
                    Date currentDate = Calendar.getInstance().getTime();
                    if(expirationDate.before(currentDate)){
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return cameraWithExpiredWarrantiesList;
    }

    public static List<Camera> getAllCamerasWithUpcomingExpiration() throws Exception {
        List<Camera> cameraWithExpiredWarrantiesList = new ArrayList<>(CameraRequests.getAllCameras());

        cameraWithExpiredWarrantiesList.removeIf(new Predicate<Camera>() {
            @Override
            public boolean test(Camera camera) {

                try {
                    Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(camera.getWarrantyExpiration());
                    Calendar newCalender = Calendar.getInstance();
                    newCalender.add(Calendar.MONTH, 1);
                    Date futureDate = newCalender.getTime();
                    Date currentDate = Calendar.getInstance().getTime();

                    if(expirationDate.after(currentDate) && expirationDate.before(futureDate)){
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return cameraWithExpiredWarrantiesList;
    }

    public static List<Camera> getAllCameras() throws Exception {
        return CameraRequests.getAllCameras();
    }
}