package com.mentalhealth.project.system.answer.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.answer.domain.ScaleAnswer;
import com.mentalhealth.project.system.answer.mapper.ScaleAnswerMapper;
import com.mentalhealth.project.system.answer.service.IScaleAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题选项信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Service
public class ScaleAnswerServiceImpl implements IScaleAnswerService 
{
    @Autowired
    private ScaleAnswerMapper scaleAnswerMapper;

    /**
     * 查询问题选项信息
     * 
     * @param answerId 问题选项信息主键
     * @return 问题选项信息
     */
    @Override
    public ScaleAnswer selectScaleAnswerByAnswerId(Long answerId)
    {
        return scaleAnswerMapper.selectScaleAnswerByAnswerId(answerId);
    }

    /**
     * 查询问题选项信息列表
     * 
     * @param scaleAnswer 问题选项信息
     * @return 问题选项信息
     */
    @Override
    public List<ScaleAnswer> selectScaleAnswerList(ScaleAnswer scaleAnswer)
    {
        return scaleAnswerMapper.selectScaleAnswerList(scaleAnswer);
    }

    /**
     * 查询问题选项信息列表
     *
     * @param questionId 问题选项信息
     * @return 问题选项信息
     */
    @Override
    public List<ScaleAnswer> selectScaleAnswerByQuestionId(Long questionId)
    {
        return scaleAnswerMapper.selectScaleAnswerByQuestionId(questionId);
    }

    /**
     * 新增问题选项信息
     * 
     * @param scaleAnswer 问题选项信息
     * @return 结果
     */
    @Override
    public int insertScaleAnswer(ScaleAnswer scaleAnswer)
    {
        scaleAnswer.setCreateTime(DateUtils.getNowDate());
        return scaleAnswerMapper.insertScaleAnswer(scaleAnswer);
    }

    /**
     * 修改问题选项信息
     * 
     * @param scaleAnswer 问题选项信息
     * @return 结果
     */
    @Override
    public int updateScaleAnswer(ScaleAnswer scaleAnswer)
    {
        scaleAnswer.setUpdateTime(DateUtils.getNowDate());
        return scaleAnswerMapper.updateScaleAnswer(scaleAnswer);
    }

    /**
     * 批量删除问题选项信息
     * 
     * @param answerIds 需要删除的问题选项信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleAnswerByAnswerIds(String answerIds)
    {
        return scaleAnswerMapper.deleteScaleAnswerByAnswerIds(Convert.toStrArray(answerIds));
    }

    /**
     * 删除问题选项信息信息
     * 
     * @param answerId 问题选项信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleAnswerByAnswerId(Long answerId)
    {
        return scaleAnswerMapper.deleteScaleAnswerByAnswerId(answerId);
    }

    public List<ScaleAnswer> addScore(String[] answerIds){
        return scaleAnswerMapper.addScore(answerIds);
    }
}
