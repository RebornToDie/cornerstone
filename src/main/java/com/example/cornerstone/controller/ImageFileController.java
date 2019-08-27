package com.example.cornerstone.controller;

import com.example.cornerstone.comm.response.ReturnResponse;
import com.example.cornerstone.imagetrans.ImageTransExecuter;
import com.example.cornerstone.imagetrans.storage.LocalFileStorage;
import com.example.cornerstone.imagetrans.util.ConstantConf;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author reborntodie
 * @date 2019/8/12 11:21
 */
@RestController
public class ImageFileController {

    private static Logger log = LoggerFactory.getLogger(ImageFileController.class);

    String imageDir = ConstantConf.filedir + "imagedir" + File.separator;

    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request) throws JsonProcessingException {
        String fileName = file.getOriginalFilename();

        try {
            String imageFilePath = imageDir + fileName;
            log.info("ImageFileController fileUpload Path : {}", imageFilePath);
            LocalFileStorage.getInstance().saveFile(imageFilePath, file.getInputStream());
            return new ObjectMapper().writeValueAsString(new ReturnResponse<>(1, "SUCCESS", null));
        } catch (IOException e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<>(-1, "FAILED", null));
        }
    }

    @RequestMapping(value = "deleteFile", method = RequestMethod.GET)
    public String deleteFile(String fileName) throws JsonProcessingException {
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }

        try {
            String imageFilePath = imageDir + fileName;
            log.info("ImageFileController deleteFile path : {}", imageFilePath);
            LocalFileStorage.getInstance().deleteFile(imageFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
    }

    @RequestMapping(value = "listFiles", method = RequestMethod.GET)
    public String getFiles() throws JsonProcessingException {
        List<Map<String, String>> list = Lists.newArrayList();
        File file = new File(imageDir);
        if (file.exists()) {
            Arrays.stream(file.listFiles()).forEach(file1 -> list.add(ImmutableMap.of("fileName", imageDir + "/" + file1.getName())));
        }
        log.info("ImageFileController fileList : {}", list);
        return new ObjectMapper().writeValueAsString(list);
    }

    @RequestMapping(value = "getThumbnailFile", method = RequestMethod.GET)
    public void getFile(String fileName, String sizePatten, HttpServletResponse response) throws IOException {
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        String suffix = getSuffix(fileName);
        String imageFilePath = imageDir + fileName;
        String imageFiletransPath = imageFilePath +"_file"+ File.separator + sizePatten + "." + suffix;
        LocalFileStorage.getInstance().createNewFile(imageFiletransPath);
        ImageTransExecuter.storeThumbnailFile(imageFilePath, imageFiletransPath, sizePatten, suffix);

        InputStream inputStream = LocalFileStorage.getInstance().getInputStream(imageFiletransPath);
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copyLarge(inputStream, outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

    private String getSuffix(String fileName) {
        String suffix = "";
        if (fileName.contains(".")) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return suffix;
    }

}
