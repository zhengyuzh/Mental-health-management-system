package com.mentalhealth.project.system.result.service.impl;

import java.util.List;
import com.mentalhealth.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mentalhealth.project.system.result.mapper.ScaleResultMapper;
import com.mentalhealth.project.system.result.domain.ScaleResult;
import com.mentalhealth.project.system.result.service.IScaleResultService;
import com.mentalhealth.common.utils.text.Convert;

/**
 * 测评结果参考信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Service
public class ScaleResultServiceImpl implements IScaleResultService 
{
    @Autowired
    private ScaleResultMapper scaleResultMapper;

    /**
     * 查询测评结果参考信息
     * 
     * @param resultId 测评结果参考信息主键
     * @return 测评结果参考信息
     */
    @Override
    public ScaleResult selectScaleResultByResultId(Long resultId)
    {
        return scaleResultMapper.selectScaleResultByResultId(resultId);
    }
    @Override
    public ScaleResult selectScaleResultByScaleId(Long scaleId)
    {
        return scaleResultMapper.selectScaleResultByScaleId(scaleId);
    }

    /**
     * 查询测评结果参考信息列表
     * 
     * @param scaleResult 测评结果参考信息
     * @return 测评结果参考信息
     */
    @Override
    public List<ScaleResult> selectScaleResultList(ScaleResult scaleResult)
    {
        return scaleResultMapper.selectScaleResultList(scaleResult);
    }

    /**
     * 新增测评结果参考信息
     * 
     * @param scaleResult 测评结果参考信息
     * @return 结果
     */
    @Override
    public int insertScaleResult(ScaleResult scaleResult)
    {
        scaleResult.setCreateTime(DateUtils.getNowDate());
        return scaleResultMapper.insertScaleResult(scaleResult);
    }

    /**
     * 修改测评结果参考信息
     * 
     * @param scaleResult 测评结果参考信息
     * @return 结果
     */
    @Override
    public int updateScaleResult(ScaleResult scaleResult)
    {
        scaleResult.setUpdateTime(DateUtils.getNowDate());
        return scaleResultMapper.updateScaleResult(scaleResult);
    }

    /**
     * 批量删除测评结果参考信息
     * 
     * @param resultIds 需要删除的测评结果参考信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleResultByResultIds(String resultIds)
    {
        return scaleResultMapper.deleteScaleResultByResultIds(Convert.toStrArray(resultIds));
    }

    /**
     * 删除测评结果参考信息信息
     * 
     * @param resultId 测评结果参考信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleResultByResultId(Long resultId)
    {
        return scaleResultMapper.deleteScaleResultByResultId(resultId);
    }
}
