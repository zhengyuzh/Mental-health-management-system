package com.mentalhealth.project.system.result.mapper;

import com.mentalhealth.project.system.result.domain.ScaleResult;

import java.util.List;

/**
 * 测评结果参考信息Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface ScaleResultMapper 
{
    /**
     * 查询测评结果参考信息
     * 
     * @param resultId 测评结果参考信息主键
     * @return 测评结果参考信息
     */
    public ScaleResult selectScaleResultByResultId(Long resultId);
    public ScaleResult selectScaleResultByScaleId(Long scaleId);

    /**
     * 查询测评结果参考信息列表
     * 
     * @param scaleResult 测评结果参考信息
     * @return 测评结果参考信息集合
     */
    public List<ScaleResult> selectScaleResultList(ScaleResult scaleResult);

    /**
     * 新增测评结果参考信息
     * 
     * @param scaleResult 测评结果参考信息
     * @return 结果
     */
    public int insertScaleResult(ScaleResult scaleResult);

    /**
     * 修改测评结果参考信息
     * 
     * @param scaleResult 测评结果参考信息
     * @return 结果
     */
    public int updateScaleResult(ScaleResult scaleResult);

    /**
     * 删除测评结果参考信息
     * 
     * @param resultId 测评结果参考信息主键
     * @return 结果
     */
    public int deleteScaleResultByResultId(Long resultId);

    /**
     * 批量删除测评结果参考信息
     * 
     * @param resultIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScaleResultByResultIds(String[] resultIds);
}
