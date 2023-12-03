package com.mentalhealth.project.system.question.mapper;

import com.mentalhealth.project.system.answer.domain.ScaleAnswer;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;

import java.util.List;

/**
 * 测评量表问题信息Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public interface ScaleQuestionMapper 
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
     * 查询测评量表问题信息列表 By ScaleId
     *
     * @param scaleId 所属测评量表 ScaleId
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
     * 删除测评量表问题信息
     * 
     * @param questionId 测评量表问题信息主键
     * @return 结果
     */
    public int deleteScaleQuestionByQuestionId(Long questionId);

    /**
     * 批量删除测评量表问题信息
     * 
     * @param questionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScaleQuestionByQuestionIds(String[] questionIds);

    /**
     * 批量删除问题选项信息
     * 
     * @param questionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScaleAnswerByQuestionIds(String[] questionIds);
    
    /**
     * 批量新增问题选项信息
     * 
     * @param scaleAnswerList 问题选项信息列表
     * @return 结果
     */
    public int batchScaleAnswer(List<ScaleAnswer> scaleAnswerList);
    

    /**
     * 通过测评量表问题信息主键删除问题选项信息信息
     * 
     * @param questionId 测评量表问题信息ID
     * @return 结果
     */
    public int deleteScaleAnswerByQuestionId(Long questionId);

    public List<ScaleQuestion> selectScaleQuestionAll();
}
