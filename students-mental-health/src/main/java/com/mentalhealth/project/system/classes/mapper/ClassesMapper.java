package com.mentalhealth.project.system.classes.mapper;

import com.mentalhealth.project.system.classes.domain.Classes;

import java.util.List;

/**
 * 班级信息Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2021-11-12
 */
public interface ClassesMapper 
{
    /**
     * 查询班级信息
     * 
     * @param classesId 班级信息主键
     * @return 班级信息
     */
    public Classes selectClassesByClassesId(Long classesId);

    /**
     * 查询班级信息列表
     * 
     * @param classes 班级信息
     * @return 班级信息集合
     */
    public List<Classes> selectClassesList(Classes classes);

    public List<Classes> selectAllClasses(Classes classes);

    /**
     * 新增班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    public int insertClasses(Classes classes);

    /**
     * 修改班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    public int updateClasses(Classes classes);

    /**
     * 删除班级信息
     * 
     * @param classesId 班级信息主键
     * @return 结果
     */
    public int deleteClassesByClassesId(Long classesId);

    /**
     * 批量删除班级信息
     * 
     * @param classesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassesByClassesIds(String[] classesIds);
}
