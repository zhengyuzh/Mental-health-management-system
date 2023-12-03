package com.mentalhealth.project.system.major.service.impl;

import java.util.List;

import com.mentalhealth.common.constant.UserConstants;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.project.system.dept.domain.Dept;
import com.mentalhealth.project.system.dept.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mentalhealth.project.system.major.mapper.MajorMapper;
import com.mentalhealth.project.system.major.domain.Major;
import com.mentalhealth.project.system.major.service.IMajorService;
import com.mentalhealth.common.utils.text.Convert;

/**
 * 专业Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-30
 */
@Service("majorService")
public class MajorServiceImpl implements IMajorService 
{
    @Autowired
    private MajorMapper majorMapper;

    /**
     * 查询专业
     * 
     * @param majorId 专业主键
     * @return 专业
     */
    @Override
    public Major selectMajorByMajorId(String majorId)
    {
        return majorMapper.selectMajorByMajorId(majorId);
    }

    /**
     * 查询专业列表
     * 
     * @param major 专业
     * @return 专业
     */
    @Override
    public List<Major> selectMajorList(Major major)
    {
        return majorMapper.selectMajorList(major);
    }

    /**
     * 新增专业
     * 
     * @param major 专业
     * @return 结果
     */
    @Override
    public int insertMajor(Major major)
    {
        major.setCreateTime(DateUtils.getNowDate());
        return majorMapper.insertMajor(major);
    }

    /**
     * 修改专业
     * 
     * @param major 专业
     * @return 结果
     */
    @Override
    public int updateMajor(Major major)
    {
        major.setUpdateTime(DateUtils.getNowDate());
        return majorMapper.updateMajor(major);
    }

    /**
     * 批量删除专业
     * 
     * @param majorIds 需要删除的专业主键
     * @return 结果
     */
    @Override
    public int deleteMajorByMajorIds(String majorIds)
    {
        return majorMapper.deleteMajorByMajorIds(Convert.toStrArray(majorIds));
    }

    /**
     * 删除专业信息
     * 
     * @param majorId 专业主键
     * @return 结果
     */
    @Override
    public int deleteMajorByMajorId(String majorId)
    {
        return majorMapper.deleteMajorByMajorId(majorId);
    }

    /**
     * 校验专业名称是否唯一
     *
     * @param major 专业信息
     * @return
     */
    @Override
    public String checkMajorNameUnique(Major major){
        Long majorId = StringUtils.isNull(major.getMajorId()) ? -1L : major.getMajorId();
        Major info = majorMapper.checkMajorNameUnique(major.getMajorName());
        if (StringUtils.isNotNull(info) && info.getMajorId().longValue() != majorId.longValue())
        {
            return UserConstants.Major_NAME_NOT_UNIQUE;
        }
        return UserConstants.Major_NAME_UNIQUE;
    }
}
