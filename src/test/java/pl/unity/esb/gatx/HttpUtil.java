package pl.unity.esb.gatx;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class HttpUtil {

    private static final Configuration CONFIG = Configuration.getInstance();
    private String url = CONFIG.getWebserviceURL();
    private HttpClient client;

    public HttpUtil() {
        client = HttpClientBuilder.create().build();
    }

    private HttpPost createPost() {
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/xml");
        return request;
    }

    public HttpResponse sendPost(Path pathToXML, String authorization) throws Exception {
        HttpPost request = createPost();
        request.addHeader("Authorization", authorization);
        request.setEntity(new ByteArrayEntity(loadFromFile(pathToXML).getBytes("UTF-8")));
        return client.execute(request);
    }

    private String loadFromFile(Path pathToFile) {
        StringBuffer result = new StringBuffer();
        String currentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile.toString()))) {
            while ((currentLine = br.readLine()) != null) {
                result.append(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result.toString();
    }

    public String readFromResponseBody(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
