package com.hust.o2o.utils;

import com.hust.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;

/**
 * 图片操作 工具类
 *
 * @author wangleifu
 * @created 2019-01-08 11:28
 */
public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    private static String basePath = "";

    /**
     * 添加商品缩略图，并返回存储的相对路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr,
                                           int imgL, int imgW, float outputQuality) {
        // 获取不重复的随机名
        String imgFileName = getRandomFileName();
        // 获取文件的扩展名，如png，jgp等
        String extension = getFileExtension(thumbnail.getImageName());
        // 如果目标路径不存在则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径（带文件名）
        String relativeAddr = targetAddr + imgFileName + extension;
        // 获取文件要保存的目标路径
        File dest = new File(PathUtil.getBasePath() + relativeAddr);
        // 调用Thumbnails生成带有水印的图片

        try {
            Thumbnails.of(thumbnail.getImage()).size(imgL, imgW)
                    //.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark")),0.6F)
                    .outputQuality(outputQuality).toFile(dest);
        } catch (Exception e) {
            logger.error("创建缩略图失败： " + e.toString());
            throw new RuntimeException("创建缩略图失败: " + e.toString());
        }
        return relativeAddr;
    }

    private static void makeDirPath(String targetAddr) {
        File dir = new File(targetAddr);
        boolean hasDir = dir.mkdirs();
        logger.info("hashDir: " + !hasDir);
    }

    private static String getFileExtension(String imageName) {
        int index = imageName.lastIndexOf(".");
        String extension = imageName.substring(index + 1);
        logger.info("getFileExtension: " + extension);
        return extension;
    }

    private static String getRandomFileName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void deleteFileOrPath(String imgAddr) {
        new File(imgAddr).delete();
    }
}
