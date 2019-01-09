package com.hust.o2o.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.o2o.dto.ImageHolder;
import com.hust.o2o.dto.ProductExecution;
import com.hust.o2o.enums.ProductStateEnum;
import com.hust.o2o.exceptions.ProductOperationException;
import com.hust.o2o.model.Product;
import com.hust.o2o.model.Shop;
import com.hust.o2o.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
@RequestMapping("/shopadmin")
public class ProductManagementController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProductService productService;

    /**
     * 商品详情图允许上传的最大个数
     */
    private static int MAX_IMG_COUNT = 6;

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
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

            // 若请求中存在文件流，则取出相关的文件(包括缩略图和详情图)
            if (multipartResolver.isMultipart(request)) {
                multipartRequest = multipartResolver.resolveMultipart(request);

                /**
                 * 取出缩略图, 并构建ImageHolder对象
                 */
                CommonsMultipartFile thumbnailFile =
                        (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());

                /**
                 * 取出详情图列表, 并构建List<ImageHolder>对象，最多支持6张上传
                 */
                for (int i = 0; i < MAX_IMG_COUNT; i++) {
                    CommonsMultipartFile productImgFile =
                            (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
                    if (productImgFile != null) {
                        /* 若取出的第i个详情图片文件流不为空，则将其加入详情列表 */
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
                                productImgFile.getInputStream());
                        productImgList.add(productImg);
                    }
                    else {
                        /* 若取出的第i个详情图片文件流为空，终止循环 */
                        break;
                    }
                }

                // 从前端获取商品实例
                ObjectMapper mapper = new ObjectMapper();
                String productStr = request.getSession().getAttribute("productStr").toString();
                product = mapper.readValue(productStr, Product.class); // throws IOException

                // 从前端获取shopId，并赋值给product
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);

                // 执行Service层提供的添加操作
                ProductExecution productExecution = productService.addProduct(product,thumbnail, productImgList); // throws ProductOperationException
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                }
                else {
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

            // 返回执行结果
            return modelMap;
        } catch (IOException | ProductOperationException e) {
            modelMap.put("success", false);
            modelMap.put("errMSg", e.toString());
            return modelMap;
        }

    }
}
