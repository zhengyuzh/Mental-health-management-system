package com.mentalhealth.project.system.major.service;

import java.util.List;
import com.mentalhealth.project.system.major.domain.Major;

/**
 * 专业Service接口
 * 
 * @author ruoyi
 * @date 2021-09-30
 */
public interface IMajorService 
{
    /**
     * 查询专业
     * 
     * @param majorId 专业主键
     * @return 专业
     */
    public Major selectMajorByMajorId(String majorId);

    /**
     * 查询专业列表
     * 
     * @param major 专业
     * @return 专业集合
     */
    public List<Major> selectMajorList(Major major);

    /**
     * 新增专业
     * 
     * @param major 专业
     * @return 结果
     */
    public int insertMajor(Major major);

    /**
     * 修改专业
     * 
     * @param major 专业
     * @return 结果
     */
    public int updateMajor(Major major);

    /**
     * 批量删除专业
     * 
     * @param majorIds 需要删除的专业主键集合
     * @return 结果
     */
    public int deleteMajorByMajorIds(String majorIds);

    /**
     * 删除专业信息
     * 
     * @param majorId 专业主键
     * @return 结果
     */
    public int deleteMajorByMajorId(String majorId);

    /**
     * 校验专业名称是否唯一
     *
     * @param major 专业信息
     * @return 结果
     */
    public String checkMajorNameUnique(Major major);
}
