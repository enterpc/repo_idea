package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        //根据角色ID查询出所有的资源信息类别
        List<ResourceCategory> rcList = roleMapper.findResourceCategoryByRoleId(roleId);
        //查询封装资源类别关联的资源信息
        for (ResourceCategory resourceCategory : rcList) {
            List<Resource> ResourceList = roleMapper.findResourceByCId(resourceCategory.getId());
            resourceCategory.setResourceList(ResourceList);
        }
        return rcList;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        //删除中间表中与角色ID关联的所有资源信息
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();

        for (Integer RId: resourceIdList) {

            Role_Resource_relation role_resource_relation = new Role_Resource_relation();

            role_resource_relation.setResourceId(RId);

            role_resource_relation.setRoleId(roleResourceVo.getRoleId());

            Date date = new Date();
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedby("system");

            roleMapper.roleContextResource(role_resource_relation);

        }

    }
}
