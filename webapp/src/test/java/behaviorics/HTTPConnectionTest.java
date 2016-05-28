package behaviorics;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;

import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, URL.class})
public class HTTPConnectionTest {

    final String endpoint = "http://test.com";
    final String body = "{}";
    final String expectedResponse = "test_response";

    @Test
    public void testMakeGetRequest() throws Exception {

        PowerMockito.stub(method(HTTPConnection.class, "sendRequestWithNoBody")).toReturn(expectedResponse);
        Assert.assertEquals("test_response", HTTPConnection.makeGetRequest(endpoint));
    }

    @Test
    public void testMakePostRequest() throws Exception {
        PowerMockito.stub(method(HTTPConnection.class, "sendRequestWithBody")).toReturn(expectedResponse);
        Assert.assertEquals(expectedResponse, HTTPConnection.makePostRequest(endpoint, body));
    }

    @Test
    public void testMakePutRequest() throws Exception {
        PowerMockito.stub(method(HTTPConnection.class, "sendRequestWithBody")).toReturn(expectedResponse);
        Assert.assertEquals(expectedResponse, HTTPConnection.makePutRequest(endpoint, body));
    }

    @Test
    public void testMakeDeleteRequest() throws Exception {
        PowerMockito.stub(method(HTTPConnection.class, "sendRequestWithNoBody")).toReturn(expectedResponse);
        Assert.assertEquals(expectedResponse, HTTPConnection.makeDeleteRequest(endpoint));
    }

    @Test
    public void testSendRequestWithNoBody() throws Exception {

        final HttpURLConnection mockUrlCon = mock(HttpURLConnection.class);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedResponse.getBytes("UTF-8"));
        when(mockUrlCon.getInputStream()).thenReturn(inputStream);

        URLStreamHandler stubUrlHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return mockUrlCon;
            }
        };
        URL url = new URL("foo", "bar", 99, "/foobar", stubUrlHandler);

        PowerMockito.whenNew(URL.class).withArguments(endpoint).thenReturn(url);

        Assert.assertEquals(expectedResponse, HTTPConnection.sendRequestWithNoBody(endpoint, "GET"));
    }

    @Test
    public void testSendRequestWithBody() throws Exception {
        final HttpURLConnection mockUrlCon = mock(HttpURLConnection.class);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedResponse.getBytes("UTF-8"));
        when(mockUrlCon.getInputStream()).thenReturn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(expectedResponse.getBytes("UTF-8"));
        when(mockUrlCon.getOutputStream()).thenReturn(outputStream);

        URLStreamHandler stubUrlHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return mockUrlCon;
            }
        };
        URL url = new URL("foo", "bar", 99, "/foobar", stubUrlHandler);

        PowerMockito.whenNew(URL.class).withArguments(endpoint).thenReturn(url);

        Assert.assertEquals(expectedResponse, HTTPConnection.sendRequestWithBody(endpoint, "GET", "body"));
    }


}
