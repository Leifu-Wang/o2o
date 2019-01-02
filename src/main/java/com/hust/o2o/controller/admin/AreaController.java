package com.hust.o2o.controller.admin;

import com.hust.o2o.model.Area;
import com.hust.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 15:38 2019/1/2
 * @Modified By:
 **/
@Controller
@RequestMapping("/admin/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listArea(){
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> list = areaService.getAreaList();
        modelMap.put("areas",list);
        modelMap.put("total",list.size());

        return modelMap;
    }

}
