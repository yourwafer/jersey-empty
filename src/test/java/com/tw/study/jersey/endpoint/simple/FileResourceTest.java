package com.tw.study.jersey.endpoint.simple;

import com.tw.study.jersey.App;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hwwei on 2016/11/17.
 */
public class FileResourceTest {

    private Client client;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        App.start();

        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080");
    }

    @Test
    public void test_get(){

        Invocation.Builder builder = target.path("file").request();
        URL systemResource = ClassLoader.getSystemResource("file.txt");
        String fileName = systemResource.getFile();
        File file =new File(fileName);
        Entity entity = Entity.entity(file, MediaType.TEXT_PLAIN_TYPE);
        Response post = builder.post(entity, Response.class);
        File response = post.readEntity(File.class);
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(response.toURI()));
            String s = new String(bytes, Charset.forName("utf-8"));
            assertThat(s, CoreMatchers.is("abc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        App.stop();
    }
}
