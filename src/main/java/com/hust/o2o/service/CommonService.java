package com.hust.o2o.service;

import java.io.File;

/**
 * 通用服务
 */
public interface CommonService {

    /**
     * 返回文件对象
     * @param relativePath 相对 basePath 的相对路径
     * @return
     */
    File getImage(String relativePath);

}
