package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired//注入资源分类dao层接口
    private ResourceCategoryMapper resourceCategoryMapper;


    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        //调用资源分类dao层接口
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        // 补全资源分类信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        //保存资源分类信息
        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        // 补全资源分类信息
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setUpdatedBy("system");

        //保存资源分类信息
        resourceCategoryMapper.updateResourceCategory(resourceCategory);

    }

    @Override
    public void deleteResourceCategory(Integer rCid) {

        resourceCategoryMapper.deleteResourceCategory(rCid);

    }


}
