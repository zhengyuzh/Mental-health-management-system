package com.mentalhealth.project.system.answer.service;

import com.mentalhealth.project.system.answer.domain.ScaleAnswer;

import java.util.List;

/**
 * 问题选项信息Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public interface IScaleAnswerService 
{
    /**
     * 查询问题选项信息
     * 
     * @param answerId 问题选项信息主键
     * @return 问题选项信息
     */
    public ScaleAnswer selectScaleAnswerByAnswerId(Long answerId);

    /**
     * 查询问题选项信息列表
     * 
     * @param scaleAnswer 问题选项信息
     * @return 问题选项信息集合
     */
    public List<ScaleAnswer> selectScaleAnswerList(ScaleAnswer scaleAnswer);

    public List<ScaleAnswer> selectScaleAnswerByQuestionId(Long questionId);

    /**
     * 新增问题选项信息
     * 
     * @param scaleAnswer 问题选项信息
     * @return 结果
     */
    public int insertScaleAnswer(ScaleAnswer scaleAnswer);

    /**
     * 修改问题选项信息
     * 
     * @param scaleAnswer 问题选项信息
     * @return 结果
     */
    public int updateScaleAnswer(ScaleAnswer scaleAnswer);

    /**
     * 批量删除问题选项信息
     * 
     * @param answerIds 需要删除的问题选项信息主键集合
     * @return 结果
     */
    public int deleteScaleAnswerByAnswerIds(String answerIds);

    /**
     * 删除问题选项信息信息
     * 
     * @param answerId 问题选项信息主键
     * @return 结果
     */
    public int deleteScaleAnswerByAnswerId(Long answerId);

    public List<ScaleAnswer> addScore(String[] answerIds);
}
