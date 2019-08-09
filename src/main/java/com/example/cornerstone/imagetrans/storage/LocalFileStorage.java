package com.example.cornerstone.imagetrans.storage;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;

/**
 * @author reborntodie
 * @date 2019/8/9 11:51
 */
public class LocalFileStorage implements IFileStorage {

    private static Logger log = LoggerFactory.getLogger(LocalFileStorage.class);

    private LocalFileStorage(){

    }

    private static LocalFileStorage localFileStorage = new LocalFileStorage();

    public static LocalFileStorage getInstance(){
        return localFileStorage;
    }

    @Override
    public long saveFile(String absolutePath, InputStream input)
            throws IOException {
        long size = -1;
        File target = new File(absolutePath);
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        // move file to upload_file_path

//		OutputStream output = new FileOutputStream(target);
        OutputStream output = null;
        try {
            output = new BufferedOutputStream(new FileOutputStream(target));
            size = IOUtils.copyLarge(input, output);
        } catch (Exception e) {
            log.error("move file error.", e);
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
        return size;
    }

    @Override
    public boolean createNewFile(String path) throws IOException {
        File target = new File(path);
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        // 文件存在则返回false
        if (target.exists()) {
            return false;
        }
        return target.createNewFile();
    }

    @Override
    public boolean deleteFile(String path) throws IOException {
        // delete from local file system
        File target = new File(path);
        if (!target.exists()) {
            // throw new IOException(" the file not found:" + path);
            log.warn(" the path can not be found:" + path);
            return false;
        }
        if (!target.isFile()) {
            log.warn(" the path is not a file:" + path);
            return false;
            // throw new IOException(" the path is not a file:" + path);
        }
        return target.delete();

    }

    @Override
    public boolean exists(String absolutePath) throws IOException {
        // get file from local file system
        File target = new File(absolutePath);
        return target.exists();
    }

    @Override
    public InputStream getInputStream(String absolutePath) throws IOException {
        try {
            absolutePath = URLDecoder.decode(absolutePath, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            log.warn("url decode error" + e1);
        }
        // get from local file system
        File file = new File(absolutePath);
//		return new FileInputStream(file);
        return new BufferedInputStream(new FileInputStream(file));
    }

    @Override
    public OutputStream getOutputStream(String absolutePath) throws IOException {
        try {
            absolutePath = URLDecoder.decode(absolutePath, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            log.warn("url decode error" + e1);
        }
        File target = new File(absolutePath);
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        // get from local file system
        File file = new File(absolutePath);
//		return new FileOutputStream(file);
        return new BufferedOutputStream(new FileOutputStream(file));

    }

    @Override
    public String[] list(String path) throws IOException {
        // get from local file system
        File target = new File(path);
        if (!target.exists()) {
            // throw new IOException(" the file not found:" + path);
            log.warn(" the path not exists:" + path);
        }
        if (target.isFile()) {
            log.warn(" the path is a file:" + path);
            // throw new IOException(" the path is not a file:" + path);
        }
        return target.list();
    }

    @Override
    public boolean deleteDirectory(String path) throws IOException {
        // delete from local file system
        File target = new File(path);
        if (!target.exists()) {
            // throw new IOException(" the file not found:" + path);
            log.warn(" the path can not be found:" + path);
            return false;
        }
        if (!target.isDirectory()) {
            log.warn(" the path is not a file:" + path);
            return false;
            // throw new IOException(" the path is not a file:" + path);
        }
        return target.delete();
    }

    @Override
    public long lastModified(String path) throws IOException {
        File target = new File(path);
        if (target.exists()) {
            return target.lastModified();
        } else {
            return -1;
        }

    }

    @Override
    public long getSize(String path) throws IOException {
        File target = new File(path);
        return target.length();
    }

    @Override
    public void touch(String path) throws IOException {
        File target = new File(path);
        if (target.exists()) {
            boolean success = target
                    .setLastModified(System.currentTimeMillis());
            if (!success) {
                throw new IOException(
                        "Unable to set the last modification time for " + path);
            }
        }
    }

}

