package com.mentalhealth.project.system.question.service;

import com.mentalhealth.project.system.question.domain.ScaleQuestion;

import java.util.List;

/**
 * 测评量表问题信息Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public interface IScaleQuestionService 
{
    /**
     * 查询测评量表问题信息
     * 
     * @param questionId 测评量表问题信息主键
     * @return 测评量表问题信息
     */
    public ScaleQuestion selectScaleQuestionByQuestionId(Long questionId);

    /**
     * 查询测评量表问题信息列表
     * 
     * @param scaleQuestion 测评量表问题信息
     * @return 测评量表问题信息集合
     */
    public List<ScaleQuestion> selectScaleQuestionList(ScaleQuestion scaleQuestion);
    /**
     * 查询测评量表问题信息列表
     *
     * @param scaleId 所属测评量id
     * @return 测评量表问题信息集合
     */
    public List<ScaleQuestion> selectScaleQuestionListByScaleId(Long scaleId);

    /**
     * 新增测评量表问题信息
     * 
     * @param scaleQuestion 测评量表问题信息
     * @return 结果
     */
    public int insertScaleQuestion(ScaleQuestion scaleQuestion);

    /**
     * 修改测评量表问题信息
     * 
     * @param scaleQuestion 测评量表问题信息
     * @return 结果
     */
    public int updateScaleQuestion(ScaleQuestion scaleQuestion);

    /**
     * 批量删除测评量表问题信息
     * 
     * @param questionIds 需要删除的测评量表问题信息主键集合
     * @return 结果
     */
    public int deleteScaleQuestionByQuestionIds(String questionIds);

    /**
     * 删除测评量表问题信息信息
     * 
     * @param questionId 测评量表问题信息主键
     * @return 结果
     */
    public int deleteScaleQuestionByQuestionId(Long questionId);

    public List<ScaleQuestion> selectScaleQuestionAll();
}
