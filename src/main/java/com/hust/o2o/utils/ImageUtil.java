package com.hust.o2o.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: wang
 * @Desciption: 封装图片处理工具方法
 * @Date: Created in 10:27 2019/1/4
 * @Modified By:
 **/
public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random random = new Random();

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class.getName());

    /**
     * 存储店铺缩略图，返回缩略图相对路径
     * @param thumbnailInputStream
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String targetAddr, String fileName){
        String realFileName = getRandomFileName();
        logger.info("---图片的随机文件名---:{}",realFileName);
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.info("---图片的相对路径---:{}",relativeAddr);
        File dest = new File(PathUtils.getImgBasePath() + relativeAddr);
        logger.info("---图片存储的绝对路径---:{}",dest.getAbsolutePath());
        try{
            Thumbnails.of(thumbnailInputStream).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 获取随机文件名，由 时间+五位随机数组成
     * @return
     */
    public static String getRandomFileName(){

        //获取随机五位数
        int randNum = random.nextInt(89999) + 10000;
        String nowTimeStr = sdf.format(new Date());

        return nowTimeStr + randNum;

    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName
     * @return
     */
    public static String getFileExtension(String  fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径上所涉及到的目录
     * @param targetAddr
     */
    public static void makeDirPath(String targetAddr){
        String absolutePath = PathUtils.getImgBasePath() + targetAddr;
        File dirPath = new File(absolutePath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

}
