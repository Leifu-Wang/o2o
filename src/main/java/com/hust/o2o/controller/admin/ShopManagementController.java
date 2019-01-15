package com.hust.o2o.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.o2o.dto.ShopExecution;
import com.hust.o2o.exceptions.ShopOperationException;
import com.hust.o2o.model.Person;
import com.hust.o2o.model.Shop;
import com.hust.o2o.service.AreaService;
import com.hust.o2o.service.ShopCategoryService;
import com.hust.o2o.service.ShopService;
import com.hust.o2o.utils.HttpServletRequestUtil;
import com.hust.o2o.utils.ShopStateEnum;
import com.hust.o2o.utils.VerifyCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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
    public String shopOperation() {

        return "shop/shopOperation";
    }

    @RequestMapping("/list")
    private String shopList(){
        return "shop/shopList";
    }

    @RequestMapping(value = "/infoInit", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> shopInfoInit() {
        logger.info("---Controller shop/shopInfoInit");
        logger.info("---获取店铺注册需要的区域列表以及店铺类别列表---");
        Map<String, Object> modelMap = new HashMap<>();
        try {
            modelMap.put("areaList", areaService.getAreaList());
            modelMap.put("shopCategoryList", shopCategoryService.getShopCategoryList());
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * 店铺注册处理器。
     * 1. 接收前端传参
     * 2. Service 层处理店铺添加
     * 3. 依据 Service 层的返回结果包装前端返回值
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request) {
        logger.info("---Controller shop/registerShop---");
        Map<String, Object> modelMap = new HashMap<>();

        logger.info("---校验验证码---");
        if (!VerifyCodeUtil.checkVerifyCode(request)) {
            logger.info("---验证码错误---");
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }
        logger.info("---验证码正确---");
//        logger.debug("---register 参数名：");
//        Enumeration<String> parameters = request.getParameterNames();
//        while (parameters.hasMoreElements()){
//            logger.debug("-------------------{}", parameters.nextElement());
//        }
        //1. 接收并转化相应的参数
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        logger.debug("-----前端传参：{}", shopStr);
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            logger.info("---json 解析失败---{}", e.getMessage());
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //获取前端传递的文件流并进行处理
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
            logger.info("---上传的图片名：{}", shopImg.getOriginalFilename());
        } else {
            logger.info("---上传图片为空---");
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        //2. 注册店铺
        if (shop != null && shopImg != null) {
            // 模拟传参，运行环境下应从 session 中取值
            Person person = new Person();
            person.setUserId(4l);
            shop.setOwner(person);
//            Person userOnline = (Person) request.getSession().getAttribute("userOnline");
//            shop.setOwner(userOnline);

            ShopExecution se;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

    @RequestMapping("/getShopInfo")
    @ResponseBody
    public Map<String, Object> getShopInfo(HttpServletRequest request) {
        logger.info("---getShopInfo---");
        Map<String, Object> modelMap = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        logger.debug("---解析参数 shopId = {}", shopId);
        if (shopId < -1 || shopService.getShopById(shopId) == null) {
            logger.info("---Shop Id 无效---");
            modelMap.put("success", false);
            modelMap.put("errMsg", "Shop Id 不存在");
            return modelMap;
        }
        try {
            logger.info("---获取店铺信息、区域列表、店铺类别列表---");
            modelMap.put("shop", shopService.getShopById(shopId));
            modelMap.put("areaList", areaService.getAreaList());
            modelMap.put("shopCategoryList", shopCategoryService.getShopCategoryList());
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyShop(HttpServletRequest request) {
        logger.info("---modifyShop---");
        Map<String, Object> modelMap = new HashMap<>();

        logger.info("---校验验证码---");
        if (!VerifyCodeUtil.checkVerifyCode(request)) {
            logger.info("---验证码错误---");
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }
        logger.info("---验证码正确---");
        //1. 接收并转化相应的参数
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        logger.debug("-----前端传参：{}", shopStr);
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            logger.info("---json 解析失败---{}", e.getMessage());
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //获取前端传递的文件流并进行处理
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
            if (shopImg != null) {
                logger.info("---上传的图片名：{}", shopImg.getOriginalFilename());
            }
        }

        ShopExecution se = null;
        try {
            se = shopService.modifyShop(shop,
                    shopImg == null ? null : shopImg.getInputStream(),
                    shopImg == null ? null : shopImg.getOriginalFilename());
            if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("errMsg", se.getStateInfo());
                modelMap.put("success", false);
            }
        } catch (ShopOperationException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/getShopList", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        logger.info("---getShopList---");
        Map<String, Object> modelMap = new HashMap<>();
        // 模拟获取当前登录人信息
        Person owner = new Person();
        owner.setUserId(4l);
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex") == -1 ? 0 : HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize") == -1 ? 20 : HttpServletRequestUtil.getInt(request, "pageSize");
//        Person owner = (Person) request.getSession().getAttribute("userOnline");
        logger.info("---当前登录人（请求人）:{}", owner.getUserId());
        List<Shop> shopList = new ArrayList<>();
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(owner);
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("success", true);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("totalCount", se.getCount());
            modelMap.put("user", owner);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }

        return modelMap;
    }

    @RequestMapping(value = "/getShopManagementInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request){
        logger.info("---getShopManagementInfo---");
        Map<String, Object> modelMap = new HashMap<>();

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0){
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null){
                modelMap.put("redirect", true);
                modelMap.put("url", "/o2o/shop/getShopList");
            }
            else {
                modelMap.put("redirecr", false);
                modelMap.put("shopId", ((Shop)currentShopObj).getShopId());
            }
        }else{
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }

}
