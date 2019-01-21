package com.hust.o2o.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.o2o.dto.ImageHolder;
import com.hust.o2o.dto.ProductExecution;
import com.hust.o2o.enums.ProductStateEnum;
import com.hust.o2o.exceptions.ProductOperationException;
import com.hust.o2o.model.Product;
import com.hust.o2o.model.ProductCategory;
import com.hust.o2o.model.Shop;
import com.hust.o2o.service.ProductCategoryService;
import com.hust.o2o.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品管理控制器
 *
 * @author wangleifu
 * @created 2019-01-08 16:19
 */
@Controller
@RequestMapping("/shop")
public class ProductManagementController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品详情图允许上传的最大个数
     */
    private static int MAX_IMG_COUNT = 6;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // TODO 验证码校验
        /*
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMSg", "验证码输入错误！");
            return modelMap;
        }
         */
        try {
            // 接受前端参数变量的初始化，包括商品、缩略图、详情图列表 实体类
            Product product;
            ImageHolder thumbnail;
            List<ImageHolder> productImgList = new ArrayList<>();

            MultipartHttpServletRequest multipartRequest;
            CommonsMultipartResolver multipartResolver =
                    new CommonsMultipartResolver(request.getSession().getServletContext());

            /**
             * 若请求中存在文件流，则取出相关的文件(包括缩略图和详情图)
             */
            if (multipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, productImgList, multipartResolver);
                product = getProduct(request);
                Shop shop = getShop(request);
                product.setShop(shop);

                // 执行Service层提供的添加操作
                ProductExecution productExecution = productService.addProduct(product, thumbnail, productImgList); //
                // throws ProductOperationException
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMSg", productExecution.getStateInfo());
                }
            }
            /**
             * 若请求中不存在文件流，则返回错误并报告信息
             */
            else {
                modelMap.put("success", false);
                modelMap.put("errMSg", "获取商品图片失败！");
            }

            logger.info("商品添加结果：", modelMap.get("success").toString());
            logger.info("结果信息：", modelMap.get("errMSg").toString());
            // 返回执行结果
            return modelMap;
        } catch (IOException | ProductOperationException e) {
            modelMap.put("success", false);
            modelMap.put("errMSg", e.toString());
            logger.info("商品添加结果：", modelMap.get("success").toString());
            logger.info("结果信息：", modelMap.get("errMSg").toString());
            return modelMap;
        }
    }

    @RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductById(@RequestParam long productId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productId > -1) {
            Product product = productService.getProductById(productId);
            List<ProductCategory> productCategoryList =
                    productCategoryService.getProductCategoryList(product.getShop().getShopId());
            modelMap.put("product", product);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        /*
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMSg", "验证码输入错误！");
            return modelMap;
        }
        */
        try {
            /*若请求中存在文件流，则取出相关的文件(包括缩略图和详情图)*/
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            if (multipartResolver.isMultipart(request)) {
                /*接受前端参数变量的初始化，包括商品、缩略图、详情图列表 实体类*/
                List<ImageHolder> productImgList = new ArrayList<>();
                ImageHolder thumbnail = handleImage(request, productImgList, multipartResolver);

                Product product = getProduct(request);
                Shop shop = getShop(request);
                product.setShop(shop);

                /*修改商品信息*/
                ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "商品修改失败: " + pe.getStateInfo());
                }
            }
            /*若请求中不存在文件流，则返回错误并报告信息*/
            else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "商品修改失败！");
            }
            return modelMap;
        } catch (IOException | ProductOperationException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "商品修改失败: " + e.toString());
            return modelMap;
        }

    }

    @RequestMapping(value = "/getproductbyid", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getProductById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        int pageIndex = -1;
        int pageSize = -1;
        long productCategoryId = -1;
        String productName = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pageIndex")) {
                pageIndex = new Integer(cookie.getValue());
            } else if (cookie.getName().equals("pageSize")) {
                pageSize = new Integer(cookie.getValue());
            } else if (cookie.getName().equals("productCategoryId")) {
                productCategoryId = new Long(cookie.getValue());
            } else if (cookie.getName().equals("productName")) {
                productName = cookie.getValue();
            }
        }
        Product product = new Product();
        /*从前端获取shopId，并赋值给product*/
        Shop shop = getShop(request);
        product.setShop(shop);

        if (pageIndex > -1 && pageSize > -1 && shop != null && shop.getShopId() != null) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            product.setProductCategory(productCategory);
            product.setProductName(productName);

            ProductExecution pe = productService.getProductList(product, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageIndex or pageSize or shopId");
        }
        return modelMap;
    }

    private Shop getShop(HttpServletRequest request) {
        /*从前端获取shop*/
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        Shop shop = new Shop();
        shop.setShopId(currentShop.getShopId());
        return shop;
    }


    private Product getProduct(HttpServletRequest request) throws IOException {
        /*从前端获取商品实例*/
        ObjectMapper mapper = new ObjectMapper();
        String productStr = request.getSession().getAttribute("productStr").toString();
        Product product = mapper.readValue(productStr, Product.class); // throws IOException
        return product;
    }

    /**
     * 从前端获取商品的 缩略图 和 详情图
     *
     * @param request
     * @param productImgList
     * @param multipartResolver
     * @return
     * @throws IOException
     */
    private ImageHolder handleImage(HttpServletRequest request, List<ImageHolder> productImgList,
                                    CommonsMultipartResolver multipartResolver) throws IOException {
        MultipartHttpServletRequest multipartRequest;
        ImageHolder thumbnail;
        multipartRequest = multipartResolver.resolveMultipart(request);

        /*
         * 取出缩略图, 并构建ImageHolder对象
         */
        CommonsMultipartFile thumbnailFile =
                (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
        thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());

        /*
         * 取出详情图列表, 并构建List<ImageHolder>对象，最多支持6张上传
         */
        for (int i = 0; i < MAX_IMG_COUNT; i++) {
            CommonsMultipartFile productImgFile =
                    (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
            if (productImgFile != null) {
                /* 若取出的第i个详情图片文件流不为空，则将其加入详情列表 */
                ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
                        productImgFile.getInputStream()); // throw IO exception
                productImgList.add(productImg);
            } else {
                /* 若取出的第i个详情图片文件流为空，终止循环 */
                break;
            }
        }
        return thumbnail;
    }
}
