package behaviorics;

import behaviorics.httpRequests.*;
import behaviorics.models.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    public List<Entity> getEntities() throws Exception {
        return EntityRequests.getAllEntitiesByOrganizationID(1);
    }

    public List<Building> getAllBuildingsByOrganizationID() throws Exception {
        return BuildingRequests.getAllBuildingsByOrganizationID(1);
    }

    public List<Floor> getAllFloorsForAnOrganization() throws Exception {
        return FloorRequests.getAllFloorsForAnOrganization(1);
    }

    public List<User> getAllUsersByOrganizationID() throws Exception {
        return UserRequests.getAllUsersByOrganizationID(1);
    }

    public List<EditCamera> getAllCamerasForEditPage() throws Exception {
        return CameraRequests.getAllCamerasForEditPage(1);
    }

    public List<State> getStates() throws Exception {
        return StateRequests.getAllStates();
    }

    public String multipartToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        if(!convertedFile.exists()){
            convertedFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertedFile.getPath();
    }

    public byte[] convertPDF(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        pdfRenderer.renderImageWithDPI(0, 700, ImageType.RGB);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIOUtil.writeImage(pdfRenderer.renderImage(0), "jpg", outputStream);
        document.close();
        return outputStream.toByteArray();
    }

    public User getCompletedUser(User user) {
            LoginService service = new LoginService();
            user.setSalt(KeyGenerators.secureRandom().generateKey());
            user.setHash(service.salt(user.getKey(), user.getSalt()));

        return user;
    }
}
