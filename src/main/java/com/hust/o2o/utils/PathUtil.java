package com.hust.o2o.utils;

/**
 * 存储路径 工具类
 *
 * @author wangleifu
 * @created 2019-01-08 12:33
 */
public class PathUtil {
    //private static String separator = System.getProperty("file.separator");
    private static String separator = "/";

    public static String getShopImagePath(long shopId) {
        String imgPath = "upload/item/shop/" + shopId + "/";
        return imgPath.replace("/", separator);
    }

    public static String getBasePath() {
        String os = System.getProperty("os.name");
        String basePath;
        if (os.toLowerCase().startsWith("win")) {

            basePath = System.getProperty("user.dir") + "/";
        } else {
            basePath = System.getProperty("user.dir") + "/";
            //basePath = "/Users/o2o/image";
        }
        basePath.replace("/", separator);
        return basePath;
    }
}
