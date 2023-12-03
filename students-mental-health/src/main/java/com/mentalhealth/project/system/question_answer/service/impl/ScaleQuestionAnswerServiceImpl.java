package com.mentalhealth.project.system.question_answer.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.question_answer.domain.ScaleQuestionAnswer;
import com.mentalhealth.project.system.question_answer.mapper.ScaleQuestionAnswerMapper;
import com.mentalhealth.project.system.question_answer.service.IScaleQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测评问题选项Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Service
public class ScaleQuestionAnswerServiceImpl implements IScaleQuestionAnswerService 
{
    @Autowired
    private ScaleQuestionAnswerMapper scaleQuestionAnswerMapper;

    /**
     * 查询测评问题选项
     * 
     * @param questionId 测评问题选项主键
     * @return 测评问题选项
     */
    @Override
    public ScaleQuestionAnswer selectScaleQuestionAnswerByQuestionId(Long questionId)
    {
        return scaleQuestionAnswerMapper.selectScaleQuestionAnswerByQuestionId(questionId);
    }

    /**
     * 查询测评问题选项列表
     * 
     * @param scaleQuestionAnswer 测评问题选项
     * @return 测评问题选项
     */
    @Override
    public List<ScaleQuestionAnswer> selectScaleQuestionAnswerList(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        return scaleQuestionAnswerMapper.selectScaleQuestionAnswerList(scaleQuestionAnswer);
    }

    /**
     * 新增测评问题选项
     * 
     * @param scaleQuestionAnswer 测评问题选项
     * @return 结果
     */
    @Override
    public int insertScaleQuestionAnswer(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        scaleQuestionAnswer.setCreateTime(DateUtils.getNowDate());
        return scaleQuestionAnswerMapper.insertScaleQuestionAnswer(scaleQuestionAnswer);
    }

    /**
     * 修改测评问题选项
     * 
     * @param scaleQuestionAnswer 测评问题选项
     * @return 结果
     */
    @Override
    public int updateScaleQuestionAnswer(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        scaleQuestionAnswer.setUpdateTime(DateUtils.getNowDate());
        return scaleQuestionAnswerMapper.updateScaleQuestionAnswer(scaleQuestionAnswer);
    }

    /**
     * 批量删除测评问题选项
     * 
     * @param questionIds 需要删除的测评问题选项主键
     * @return 结果
     */
    @Override
    public int deleteScaleQuestionAnswerByQuestionIds(String questionIds)
    {
        return scaleQuestionAnswerMapper.deleteScaleQuestionAnswerByQuestionIds(Convert.toStrArray(questionIds));
    }

    /**
     * 删除测评问题选项信息
     * 
     * @param questionId 测评问题选项主键
     * @return 结果
     */
    @Override
    public int deleteScaleQuestionAnswerByQuestionId(Long questionId)
    {
        return scaleQuestionAnswerMapper.deleteScaleQuestionAnswerByQuestionId(questionId);
    }
}
