package com.hust.o2o.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.o2o.dto.ShopExecution;
import com.hust.o2o.exceptions.ShopOperationException;
import com.hust.o2o.model.Person;
import com.hust.o2o.model.Shop;
import com.hust.o2o.model.ShopCategory;
import com.hust.o2o.service.AreaService;
import com.hust.o2o.service.ShopCategoryService;
import com.hust.o2o.service.ShopService;
import com.hust.o2o.utils.HttpServletRequestUtil;
import com.hust.o2o.utils.ShopStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wang
 * @Desciption: 店铺管理控制层
 * @Date: Created in 18:48 2019/1/4
 * @Modified By:
 **/
@Controller
@RequestMapping("/shop")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/operation")
    public String shopOperation(ModelMap modelMap){
        modelMap.put("areaList", areaService.getAreaList());
        modelMap.put("shopCategoryList", shopCategoryService.getShopCategoryList());
        return "shop/shopOperation";
    }

    /**
     * 店铺注册处理器。
     * 1. 接收前端传参
     * 2. Service 层处理店铺添加
     * 3. 依据 Service 层的返回结果包装前端返回值
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request) {
        logger.info("---Controller shop/registerShop---");
        Map<String, Object> modelMap = new HashMap<>();

        logger.debug("---register 参数名：");
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()){
            logger.debug("-------------------{}", parameters.nextElement());
        }
        //1. 接收并转化相应的参数
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        logger.debug("-----前端传参：{}", shopStr);
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            logger.info("---json 解析失败---{}",e.getMessage());
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //获取前端传递的文件流并进行处理
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
            logger.info("---上传的图片名：{}", shopImg.getOriginalFilename());
        }else {
            logger.info("---上传图片为空---");
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        //2. 注册店铺
        if (shop != null && shopImg != null){
            // 模拟传参，运行环境下应从 session 中取值
//            Person person = new Person();
//            person.setUserId(4l);
            Person userOnline = (Person) request.getSession().getAttribute("userOnline");
            shop.setOwner(userOnline);

            ShopExecution se;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()){
                    modelMap.put("success", true);
                }else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }catch (IOException e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;

        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

}
