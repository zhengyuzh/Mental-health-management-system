package com.mentalhealth.project.system.factor.mapper;

import com.mentalhealth.project.system.factor.domain.ScaleFactor;

import java.util.List;

/**
 * 测评因子信息Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public interface ScaleFactorMapper 
{
    /**
     * 查询测评因子信息
     * 
     * @param factorId 测评因子信息主键
     * @return 测评因子信息
     */
    public ScaleFactor selectScaleFactorByFactorId(Long factorId);

    public Long selectScaleFactorAllByScaleId(Long scaleId);

    /**
     * 查询测评因子信息列表
     * 
     * @param scaleFactor 测评因子信息
     * @return 测评因子信息集合
     */
    public List<ScaleFactor> selectScaleFactorList(ScaleFactor scaleFactor);

    /**
     * 新增测评因子信息
     * 
     * @param scaleFactor 测评因子信息
     * @return 结果
     */
    public int insertScaleFactor(ScaleFactor scaleFactor);

    /**
     * 修改测评因子信息
     * 
     * @param scaleFactor 测评因子信息
     * @return 结果
     */
    public int updateScaleFactor(ScaleFactor scaleFactor);

    /**
     * 删除测评因子信息
     * 
     * @param factorId 测评因子信息主键
     * @return 结果
     */
    public int deleteScaleFactorByFactorId(Long factorId);

    /**
     * 批量删除测评因子信息
     * 
     * @param factorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScaleFactorByFactorIds(String[] factorIds);
}
