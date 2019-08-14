package com.example.cornerstone.controller;

import com.example.cornerstone.imagetrans.storage.LocalFileStorage;
import com.example.cornerstone.imagetrans.util.ConstantConf;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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
            String fileAbsolutePath = imageDir + fileName;
            log.info("ImageFileController file absolutePath : {}",fileAbsolutePath);
            LocalFileStorage.getInstance().saveFile(fileAbsolutePath, file.getInputStream());
            return new ObjectMapper().writeValueAsString("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString("FAILURE");
        }
    }

}
