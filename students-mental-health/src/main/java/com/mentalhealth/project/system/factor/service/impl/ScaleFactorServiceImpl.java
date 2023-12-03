package com.mentalhealth.project.system.factor.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.factor.domain.ScaleFactor;
import com.mentalhealth.project.system.factor.mapper.ScaleFactorMapper;
import com.mentalhealth.project.system.factor.service.IScaleFactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测评因子信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Service("factorService")
public class ScaleFactorServiceImpl implements IScaleFactorService 
{
    @Autowired
    private ScaleFactorMapper scaleFactorMapper;

    /**
     * 查询测评因子信息
     * 
     * @param factorId 测评因子信息主键
     * @return 测评因子信息
     */
    @Override
    public ScaleFactor selectScaleFactorByFactorId(Long factorId)
    {
        return scaleFactorMapper.selectScaleFactorByFactorId(factorId);
    }

    public Long selectScaleFactorAllByScaleId(Long scaleId){
        return scaleFactorMapper.selectScaleFactorAllByScaleId(scaleId);
    }


    /**
     * 查询测评因子信息列表
     * 
     * @param scaleFactor 测评因子信息
     * @return 测评因子信息
     */
    @Override
    public List<ScaleFactor> selectScaleFactorList(ScaleFactor scaleFactor)
    {
        return scaleFactorMapper.selectScaleFactorList(scaleFactor);
    }

    /**
     * 新增测评因子信息
     * 
     * @param scaleFactor 测评因子信息
     * @return 结果
     */
    @Override
    public int insertScaleFactor(ScaleFactor scaleFactor)
    {
        scaleFactor.setCreateTime(DateUtils.getNowDate());
        return scaleFactorMapper.insertScaleFactor(scaleFactor);
    }

    /**
     * 修改测评因子信息
     * 
     * @param scaleFactor 测评因子信息
     * @return 结果
     */
    @Override
    public int updateScaleFactor(ScaleFactor scaleFactor)
    {
        scaleFactor.setUpdateTime(DateUtils.getNowDate());
        return scaleFactorMapper.updateScaleFactor(scaleFactor);
    }

    /**
     * 批量删除测评因子信息
     * 
     * @param factorIds 需要删除的测评因子信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleFactorByFactorIds(String factorIds)
    {
        return scaleFactorMapper.deleteScaleFactorByFactorIds(Convert.toStrArray(factorIds));
    }

    /**
     * 删除测评因子信息信息
     * 
     * @param factorId 测评因子信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleFactorByFactorId(Long factorId)
    {
        return scaleFactorMapper.deleteScaleFactorByFactorId(factorId);
    }
}
