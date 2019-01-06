package com.hust.o2o.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wang
 * @Desciption: 处理 HttpRequest 参数
 * @Date: Created in 18:51 2019/1/4
 * @Modified By:
 **/
public class HttpServletRequestUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpServletRequestUtil.class.getName());

    public static int getInt(HttpServletRequest request, String key){
        try{
            return Integer.parseInt(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key){
        try {
            return Long.parseLong(request.getParameter(key));
        }catch (Exception e){
            return -1l;
        }
    }

    public static double getDouble(HttpServletRequest request, String key){
        try {
            return Double.parseDouble(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public static float getFloat(HttpServletRequest request, String key){
        try {
            return Float.parseFloat(request.getParameter(key));
        }catch (Exception e){
            return -1f;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){
        try {
            return Boolean.parseBoolean(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        try{
            String result = request.getParameter(key);
            if (result != null){
                result = result.trim();
            }
            if ("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }

}
