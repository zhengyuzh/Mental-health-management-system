package com.mentalhealth.project.system.classes.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.classes.domain.Classes;
import com.mentalhealth.project.system.classes.mapper.ClassesMapper;
import com.mentalhealth.project.system.classes.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级信息Service业务层处理
 *
 * @author zhengyuzhu
 * @date 2021-11-12
 */
@Service("classesService")
public class ClassesServiceImpl implements IClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    /**
     * 查询班级信息
     *
     * @param classesId 班级信息主键
     * @return 班级信息
     */
    @Override
    public Classes selectClassesByClassesId(Long classesId) {
        return classesMapper.selectClassesByClassesId(classesId);
    }

    /**
     * 查询班级信息列表
     *
     * @param classes 班级信息
     * @return 班级信息
     */
    @Override
    public List<Classes> selectClassesList(Classes classes) {
        return classesMapper.selectClassesList(classes);
    }

    @Override
    public List<Classes> selectAllClasses(Classes classes) {
        return classesMapper.selectAllClasses(classes);
    }

    /**
     * 新增班级信息
     *
     * @param classes 班级信息
     * @return 结果
     */
    @Override
    public int insertClasses(Classes classes) {
        classes.setCreateTime(DateUtils.getNowDate());
        return classesMapper.insertClasses(classes);
    }

    /**
     * 修改班级信息
     *
     * @param classes 班级信息
     * @return 结果
     */
    @Override
    public int updateClasses(Classes classes) {
        classes.setUpdateTime(DateUtils.getNowDate());
        return classesMapper.updateClasses(classes);
    }

    /**
     * 批量删除班级信息
     *
     * @param classesIds 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassesByClassesIds(String classesIds) {
        return classesMapper.deleteClassesByClassesIds(Convert.toStrArray(classesIds));
    }

    /**
     * 删除班级信息信息
     *
     * @param classesId 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassesByClassesId(Long classesId) {
        return classesMapper.deleteClassesByClassesId(classesId);
    }
}
