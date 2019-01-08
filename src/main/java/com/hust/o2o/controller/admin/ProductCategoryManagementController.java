package com.hust.o2o.controller.admin;

import com.hust.o2o.dto.ProductCategoryExecution;
import com.hust.o2o.enums.ProductCategoryStateEnum;
import com.hust.o2o.exceptions.ProductCategoryOperationException;
import com.hust.o2o.model.ProductCategory;
import com.hust.o2o.model.Shop;
import com.hust.o2o.service.ProductCategoryService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类别相关 控制器类
 *
 * @author wangleifu
 * @create 2019-01-07 14:22
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/addproductcategories", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> addProductCategories(@RequestBody List<ProductCategory> productCategoryList,
                                                   HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory : productCategoryList) {
            productCategory.setShopId(currentShop.getShopId());
        }

        if (productCategoryList.size() > 0) {
            try {
                ProductCategoryExecution pcExecution =  productCategoryService.batchAddProductCategory(productCategoryList);
                if (pcExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pcExecution.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                logger.info("addProductCategories error: " + e.getMessage());
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        }
        else
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少添加一个商品类别！");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategory", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        if (productCategoryId != null && productCategoryId > 0) {
            try {
                ProductCategoryExecution pcExecution =
                        productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
                if (pcExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pcExecution.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                logger.info("removeProductCategory error: " + e.getMessage());
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        }
        else
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少选择一个商品类别！");
        }
        return modelMap;
    }
}
