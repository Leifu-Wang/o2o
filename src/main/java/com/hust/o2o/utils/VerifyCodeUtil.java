package com.hust.o2o.utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wang
 * @Desciption: 验证码验证工具
 * @Date: Created in 16:37 2019/1/6
 * @Modified By:
 **/
public class VerifyCodeUtil {

    /**
     * 验证码校对
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCOdeActual = HttpServletRequestUtil.getString(request,MyContants.VERIFY_CODE_FIELD);

        if (verifyCOdeActual == null || !verifyCOdeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }

}
