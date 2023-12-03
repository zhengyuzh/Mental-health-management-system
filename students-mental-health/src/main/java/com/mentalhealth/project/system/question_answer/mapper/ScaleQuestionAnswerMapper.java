package com.mentalhealth.project.system.question_answer.mapper;

import java.util.List;
import com.mentalhealth.project.system.question_answer.domain.ScaleQuestionAnswer;

/**
 * 测评问题选项Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface ScaleQuestionAnswerMapper 
{
    /**
     * 查询测评问题选项
     * 
     * @param questionId 测评问题选项主键
     * @return 测评问题选项
     */
    public ScaleQuestionAnswer selectScaleQuestionAnswerByQuestionId(Long questionId);

    /**
     * 查询测评问题选项列表
     * 
     * @param scaleQuestionAnswer 测评问题选项
     * @return 测评问题选项集合
     */
    public List<ScaleQuestionAnswer> selectScaleQuestionAnswerList(ScaleQuestionAnswer scaleQuestionAnswer);

    /**
     * 新增测评问题选项
     * 
     * @param scaleQuestionAnswer 测评问题选项
     * @return 结果
     */
    public int insertScaleQuestionAnswer(ScaleQuestionAnswer scaleQuestionAnswer);

    /**
     * 修改测评问题选项
     * 
     * @param scaleQuestionAnswer 测评问题选项
     * @return 结果
     */
    public int updateScaleQuestionAnswer(ScaleQuestionAnswer scaleQuestionAnswer);

    /**
     * 删除测评问题选项
     * 
     * @param questionId 测评问题选项主键
     * @return 结果
     */
    public int deleteScaleQuestionAnswerByQuestionId(Long questionId);

    /**
     * 批量删除测评问题选项
     * 
     * @param questionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScaleQuestionAnswerByQuestionIds(String[] questionIds);
}
