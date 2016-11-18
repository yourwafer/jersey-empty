package com.tw.study.jersey.endpoint.simple;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by hwwei on 2016/11/17.
 */
@Path("file")
@Component
public class FileResource {

    @PostConstruct
    void post(){
        System.out.println("******((((((((((((((((((((((((((((");

    }

    @POST
    public File file(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        String s = new String(bytes, Charset.forName("utf-8"));
        System.out.println("上传文件内容-》" + s);
        return file;
    }
}
