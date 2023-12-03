package com.mentalhealth.project.system.user.mapper;

import com.mentalhealth.project.system.user.domain.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与角色关联表 数据层
 * 
 * @author ruoyi
 */
public interface UserRoleMapper
{
    /**
     * 通过用户ID查询用户和角色关联
     * 
     * @param userId 用户ID
     * @return 用户和角色关联列表
     */
    public List<UserRole> selectUserRoleByUserId(Long userId);

    public UserRole findOneByUserId(Long userId);

    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<UserRole> userRoleList);

    /**
     * 删除用户和角色关联信息
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteUserRoleInfo(UserRole userRole);

    /**
     * 批量取消授权用户角色
     * 
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

    @Insert("insert into sys_user_role(role_id,user_id) values(#{roleId},#{userId})")
    public int insertUserRole(@Param("roleId") Long roleId,@Param("userId")Long userId);
}
