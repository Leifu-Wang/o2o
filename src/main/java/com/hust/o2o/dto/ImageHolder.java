package com.hust.o2o.dto;

import java.io.InputStream;

/**
 * 商品缩略图 和 商品详情图片 包装类
 *
 * @author wangleifu
 * @created 2019-01-08 9:33
 */
public class ImageHolder {
    private String imageName;
    private InputStream image;

    public ImageHolder(String imageName, InputStream image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
