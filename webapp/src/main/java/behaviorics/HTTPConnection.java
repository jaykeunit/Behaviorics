package behaviorics;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection {

    public static String makeGetRequest(String endpoint) throws Exception {
        return sendRequestWithNoBody(endpoint, "GET");
    }

    public static String makePostRequest(String endpoint, String body) throws IOException {
        return sendRequestWithBody(endpoint, "POST", body);
    }

    public static String makePutRequest(String endpoint, String body) throws IOException {
        return sendRequestWithBody(endpoint, "PUT", body);
    }

    public static String makeDeleteRequest(String endpoint) throws Exception {
        return sendRequestWithNoBody(endpoint, "DELETE");
    }

    public static String sendRequestWithNoBody(String endpoint, String verb) throws Exception {
        String encodedUrl = endpoint.replaceAll(" ", "%20");
        URL url = new URL(encodedUrl);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();

        //set headers
        request.setRequestMethod(verb);

        return readRequestResponse(request);
    }

    public static String sendRequestWithBody(String endpoint, String verb, String body) throws IOException {
        String encodedUrl = endpoint.replaceAll(" ", "%20");
        URL url = new URL(encodedUrl);
        HttpURLConnection request = (HttpURLConnection)url.openConnection();

        //set headers
        request.setRequestMethod(verb);
        request.setRequestProperty("Content-Type", "application/json");
        request.setRequestProperty("Content-Length", Integer.toString(body.getBytes().length));
        request.setDoInput(true);
        request.setDoOutput(true);

        //write body
        DataOutputStream wr = new DataOutputStream (request.getOutputStream ());
        wr.writeBytes(body);
        wr.flush();
        wr.close();

        return readRequestResponse(request);
    }

    private static String readRequestResponse(HttpURLConnection request) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder response = new StringBuilder();

        String inputLine;
        while ((inputLine = buffer.readLine()) != null) {
            response.append(inputLine);
        }
        buffer.close();

        return response.toString();
    }

}
