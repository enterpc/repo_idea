package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired//注入资源分类业务类
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")//前端访问地址
    public ResponseResult findAllResourceCategory(){
        //调用资源分类信息业务类
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        //按接口文档返回固定格式
        return  new ResponseResult(true,200,"查询所有分类信息成功",allResourceCategory);

    }
    //新增及修改资源分类信息
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        if(resourceCategory.getId() == null){
            //调用service添加资源分类信息
            resourceCategoryService.saveResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else {
            //调用service添加资源分类信息
            resourceCategoryService.updateResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }

    }

    //删除资源分类
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除成功", null);
        return responseResult;
    }

}
