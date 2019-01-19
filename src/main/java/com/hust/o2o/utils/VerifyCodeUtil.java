package com.hust.o2o.utils;

import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wang
 * @Desciption: 验证码验证工具
 * @Date: Created in 16:37 2019/1/6
 * @Modified By:
 **/
public class VerifyCodeUtil {

    private static Logger logger = LoggerFactory.getLogger(VerifyCodeUtil.class.getName());

    /**
     * 验证码校对
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        logger.info("---验证码校验工具---");
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("---图片代码: {}", verifyCodeExpected);
        String verifyCOdeActual = HttpServletRequestUtil.getString(request,MyContants.VERIFY_CODE_FIELD);
        logger.debug("---输入代码: {}", verifyCOdeActual);
        if (verifyCOdeActual == null || !verifyCOdeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }

}
