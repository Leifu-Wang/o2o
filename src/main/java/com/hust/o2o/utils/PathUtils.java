package com.hust.o2o.utils;

/**
 * @author: wang
 * @Desciption: 封装路径相关工具类
 * @Date: Created in 10:27 2019/1/4
 * @Modified By:
 **/
public class PathUtils {

    private static String separator = System.getProperty("file.separator");

    /**
     * 返回 Image 存储目录的根目录绝对路径
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/o2o/image/";
        }else {
            basePath = "/Users/jerrywang/WebLearning/ideaWorkSpace/resources/o2o/";
        }
        basePath = basePath.replace("/", separator);

        return basePath;
    }

    /**
     * 获取单个店铺上传图片的保存地址的相对路径，相对 BasePath
     * @param shopId
     * @return
     */
    public static String getShopImagePath(Long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }

}
