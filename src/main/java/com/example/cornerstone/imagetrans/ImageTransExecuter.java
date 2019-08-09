package com.example.cornerstone.imagetrans;

import com.example.cornerstone.imagetrans.storage.LocalFileStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * @author reborntodie
 * @date 2019/8/9 13:24
 */
public class ImageTransExecuter {

    private static Logger log = LoggerFactory.getLogger(ImageTransExecuter.class);

    /* 图片转换限制大小*/
    private static double LIMIT_SIZE = 8.0 * 1024.0 * 1024.0 * 50.0;

    /**
     * 根据原始图片文件创建缩略图
     *
     * @param source     - 原始图片路径(绝对路径)
     * @param target     - 目标缩略图路径(绝对路径)
     * @param sizePatten 缩放尺寸 50:保持比例最大尺寸为50, 50x50:破坏原尺寸强制尺寸为50x50
     * @param suffix     - 文件后缀
     * @return 是否创建成功
     */
    private static boolean storeThumbnailFile(String source, String target,
                                              String sizePatten, String suffix) throws IOException {
        String[] indexArray = sizePatten.split("\\*");
        if (1 == indexArray.length) {
            int maxThumSize = Integer.valueOf(sizePatten);
            return storeThumbnailFile(source, target, maxThumSize, suffix);
        } else if (2 == indexArray.length) {
            int width = Integer.valueOf(indexArray[0]);
            int height = Integer.valueOf(indexArray[1]);
            return storeThumbnailFile(source, target, width, height, suffix);
        } else {
            log.error("error sizePatten:" + sizePatten);
            return false;
        }
    }

    /**
     * 根据原始图片文件创建缩略图 (保持原比例)
     *
     * @param source      - 原始图片路径(绝对路径)
     * @param target      - 目标缩略图路径(绝对路径)
     * @param maxThumSize 最大缩放尺寸
     * @param suffix      - 文件后缀
     * @return 是否创建成功
     */
    private static synchronized boolean storeThumbnailFile(String source,
                                                           String target, int maxThumSize, String suffix) throws IOException {
        long start = System.currentTimeMillis();
        if (suffix.startsWith(".")) {
            suffix = suffix.substring(1);
        }
        if (suffix.length() == 0) {
            suffix = "jpg";
        }
        InputStream is = null;
        OutputStream out = null;
        ImageInputStream iis = null;
        ImageReader imgReader = null;
        BufferedImage buffimg = null;

        try {
            is = LocalFileStorage.getInstance().getInputStream(source);
            iis = ImageIO.createImageInputStream(is);
            Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
            if (it != null && it.hasNext()) {
                imgReader = it.next();
                imgReader.setInput(iis);
                double imgWidth = imgReader.getWidth(0);
                double imgHeight = imgReader.getHeight(0);
                // 如果目标尺寸超过原图 我们将不进行处理
                if (imgWidth < maxThumSize || imgHeight < maxThumSize) {
                    return false;
                }
                buffimg = getBufferedImage(imgReader, imgWidth, imgHeight);
                out = LocalFileStorage.getInstance().getOutputStream(target);
                //如果使用BufferedImage读入会自动根据图片信息做旋转处理, 所以我们使用InputStream
                Thumbnails.of(buffimg).size(maxThumSize, maxThumSize)
                        .outputFormat(suffix).toOutputStream(out);
            } else {
                return false;
            }
        } catch (IOException e) {
            log.error("创建缩略图失败!", e);
            throw e;
        } finally {
            if (buffimg != null) {
                buffimg.flush();
            }
            if (imgReader != null) {
                imgReader.dispose();
            }
            if (iis != null) {
                iis.flush();
                iis.close();
            }
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }

        log.debug("create thumbnail file...qtime:"
                + (System.currentTimeMillis() - start));
        return true;
    }

    /**
     * 根据原始图片文件创建指定尺寸缩略图
     *
     * @param source - 原始图片路径(绝对路径)
     * @param target - 目标缩略图路径(绝对路径)
     * @param width  - 宽
     * @param height - 高
     * @param suffix - 文件后缀
     * @return 是否创建成功
     */
    private static synchronized boolean storeThumbnailFile(String source,
                                                           String target, int width, int height, String suffix) throws IOException {
        long start = System.currentTimeMillis();
        if (suffix.startsWith(".")) {
            suffix = suffix.substring(1);
        }
        if (suffix.length() == 0) {
            suffix = "jpg";
        }
        InputStream is = null;
        OutputStream out = null;
        ImageInputStream iis = null;
        ImageReader imgReader = null;
        BufferedImage buffimg = null;
        try {
            is = LocalFileStorage.getInstance().getInputStream(source);
            iis = ImageIO.createImageInputStream(is);
            Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
            if (it != null && it.hasNext()) {
                imgReader = it.next();
                imgReader.setInput(iis);
                double imgWidth = imgReader.getWidth(0);
                double imgHeight = imgReader.getHeight(0);
                // 如果目标尺寸超过原图 我们将不进行处理
                if (imgWidth < width || imgHeight < height) {
                    return false;
                }
                buffimg = getBufferedImage(imgReader, imgWidth, imgHeight);
                out = LocalFileStorage.getInstance().getOutputStream(target);
                if (buffimg.getWidth() < width || buffimg.getHeight() < height) {
                    ImageIO.write(buffimg, suffix, out);
                    return true;
                }
                //如果使用BufferedImage读入会自动根据图片信息做旋转处理, 所以我们使用InputStream
                Thumbnails.of(buffimg).size(width, height)
                        .crop(Positions.CENTER).outputFormat(suffix)
                        .toOutputStream(out);
            } else {
                return false;
            }
        } catch (IOException e) {
            log.error("创建缩略图失败!", e);
            throw e;
        } finally {
            if (buffimg != null) {
                buffimg.flush();
            }
            if (imgReader != null) {
                imgReader.dispose();
            }
            if (iis != null) {
                try {
                    iis.flush();
                    iis.close();
                } catch (Exception e2) {
                    log.error(e2.getMessage(), e2);
                }

            }
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }

        log.debug("create thumbnail file...qtime:"
                + (System.currentTimeMillis() - start));
        return true;
    }

    /**
     * 图片转换是使用磁盘缓存，防止内存溢出
     *
     * @param imgReader
     * @param imgWidth
     * @param imgHeight
     * @return
     * @throws IOException
     */
    private static BufferedImage getBufferedImage(ImageReader imgReader, double imgWidth, double imgHeight) throws IOException {
        try {
            return imgReader.readThumbnail(0, 0);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
        } catch (IndexOutOfBoundsException e1) {
            log.error(e1.getMessage(), e1);
        } catch (UnsupportedOperationException e2) {
            log.error(e2.getMessage(), e2);
        }
        //默认位深为24
        int pixelSixe = 24;
        ImageTypeSpecifier imagetype = imgReader.getRawImageType(0);
        if (imagetype != null) {
            pixelSixe = imagetype.getColorModel().getPixelSize();
        }
        //图片大小为 宽X高X位深
        double imgSize = imgWidth * imgHeight * pixelSixe;

        if (imgSize > LIMIT_SIZE) {
            int subsampling = (int) Math.floor(Math.sqrt(imgSize / LIMIT_SIZE)) + 1;
            ImageReadParam readParam = imgReader.getDefaultReadParam();
            readParam.setSourceSubsampling(subsampling, subsampling, 0, 0);
            return imgReader.read(0, readParam);
        }
        return imgReader.read(0);
    }

    public static void main(String[] args) {
        try {
            storeThumbnailFile("F:\\xh\\image\\100.png", "F:\\xh\\image\\png\\100.png", "2000", ".png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
