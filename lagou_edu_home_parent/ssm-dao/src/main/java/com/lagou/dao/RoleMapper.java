package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件进行查询
     */

    public List<Role> findAllRole(Role role);


    /*
        根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
     */
    public List<Integer> findMenuByRoleId(Integer roleid);


    /*
        根据roleid清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);


    /*
        为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */

    public void deleteRole(Integer roleid);

    /*
       根据角色ID获取用户拥有的资源权限类别信息
    */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);

    /*
       根据资源类别ID获取拥对应的资源权限内容信息
    */
    public List<Resource> findResourceByCId(Integer cId);

    /*
            根据roleid清空角色资源中间表的关联关系
         */
    public void deleteRoleContextResource(Integer rid);

    /*
       为角色分配资源信息
    */
    public void roleContextResource(Role_Resource_relation role_resource_relation);

}
