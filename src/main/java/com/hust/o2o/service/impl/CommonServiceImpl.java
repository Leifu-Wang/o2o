package com.hust.o2o.service.impl;

import com.hust.o2o.service.CommonService;
import com.hust.o2o.utils.PathUtils;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public File getImage(String relativePath) {
        return new File(PathUtils.getImgBasePath() + relativePath);
    }
}
