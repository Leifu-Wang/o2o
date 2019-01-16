package com.hust.o2o.controller.admin;

import com.hust.o2o.service.CommonService;
import com.hust.o2o.utils.HttpServletRequestUtil;
import com.hust.o2o.utils.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller()
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    private Logger logger = LoggerFactory.getLogger(CommonController.class.getName());

    @RequestMapping("/getImage")
    @ResponseBody
    private String getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("---getImage---");

        String relativePath = HttpServletRequestUtil.getString(request, "imageUrl");
        File image = commonService.getImage(relativePath);
        InputStream inputStream = new FileInputStream(image);
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] img = swapStream.toByteArray();
//        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        os.write(img);
        os.flush();
        os.close();
        return "success";
    }

}
