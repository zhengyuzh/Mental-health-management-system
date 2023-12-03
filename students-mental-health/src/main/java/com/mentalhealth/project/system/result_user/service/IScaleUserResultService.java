package com.mentalhealth.project.system.result_user.service;

import java.util.List;
import com.mentalhealth.project.system.result_user.domain.ScaleUserResult;

/**
 * 测评成绩报告信息Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface IScaleUserResultService 
{
    /**
     * 查询测评成绩报告信息
     * 
     * @param resultId 测评成绩报告信息主键
     * @return 测评成绩报告信息
     */
    public ScaleUserResult selectScaleUserResultByResultId(Long resultId);

    /**
     * 查询测评成绩报告信息列表
     * 
     * @param scaleUserResult 测评成绩报告信息
     * @return 测评成绩报告信息集合
     */
    public List<ScaleUserResult> selectScaleUserResultList(ScaleUserResult scaleUserResult);

    /**
     * 新增测评成绩报告信息
     * 
     * @param scaleUserResult 测评成绩报告信息
     * @return 结果
     */
    public int insertScaleUserResult(ScaleUserResult scaleUserResult);

    /**
     * 修改测评成绩报告信息
     * 
     * @param scaleUserResult 测评成绩报告信息
     * @return 结果
     */
    public int updateScaleUserResult(ScaleUserResult scaleUserResult);

    /**
     * 批量删除测评成绩报告信息
     * 
     * @param resultIds 需要删除的测评成绩报告信息主键集合
     * @return 结果
     */
    public int deleteScaleUserResultByResultIds(String resultIds);

    /**
     * 删除测评成绩报告信息信息
     * 
     * @param resultId 测评成绩报告信息主键
     * @return 结果
     */
    public int deleteScaleUserResultByResultId(Long resultId);
}
