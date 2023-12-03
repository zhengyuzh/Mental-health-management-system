package com.mentalhealth.project.system.question.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.answer.domain.ScaleAnswer;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import com.mentalhealth.project.system.question.mapper.ScaleQuestionMapper;
import com.mentalhealth.project.system.question.service.IScaleQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 测评量表问题信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Service("scaleQuestionService")
public class ScaleQuestionServiceImpl implements IScaleQuestionService 
{
    @Autowired
    private ScaleQuestionMapper scaleQuestionMapper;

    /**
     * 查询测评量表问题信息
     * 
     * @param questionId 测评量表问题信息主键
     * @return 测评量表问题信息
     */
    @Override
    public ScaleQuestion selectScaleQuestionByQuestionId(Long questionId)
    {
        return scaleQuestionMapper.selectScaleQuestionByQuestionId(questionId);
    }

    /**
     * 查询测评量表问题信息列表
     * 
     * @param scaleQuestion 测评量表问题信息
     * @return 测评量表问题信息
     */
    @Override
    public List<ScaleQuestion> selectScaleQuestionList(ScaleQuestion scaleQuestion)
    {
        return scaleQuestionMapper.selectScaleQuestionList(scaleQuestion);
    }

    /**
     * 查询测评量表问题信息列表
     *
     * @param scaleId 所属测评量表id
     * @return 测评量表问题信息
     */
    @Override
    public List<ScaleQuestion> selectScaleQuestionListByScaleId(Long scaleId)
    {
        return scaleQuestionMapper.selectScaleQuestionListByScaleId(scaleId);
    }

    /**
     * 新增测评量表问题信息
     * 
     * @param scaleQuestion 测评量表问题信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertScaleQuestion(ScaleQuestion scaleQuestion)
    {
        scaleQuestion.setCreateTime(DateUtils.getNowDate());
        int rows = scaleQuestionMapper.insertScaleQuestion(scaleQuestion);
        insertScaleAnswer(scaleQuestion);
        return rows;
    }

    /**
     * 修改测评量表问题信息
     * 
     * @param scaleQuestion 测评量表问题信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateScaleQuestion(ScaleQuestion scaleQuestion)
    {
        scaleQuestion.setUpdateTime(DateUtils.getNowDate());
        scaleQuestionMapper.deleteScaleAnswerByQuestionId(scaleQuestion.getQuestionId());
        insertScaleAnswer(scaleQuestion);
        return scaleQuestionMapper.updateScaleQuestion(scaleQuestion);
    }

    /**
     * 批量删除测评量表问题信息
     * 
     * @param questionIds 需要删除的测评量表问题信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteScaleQuestionByQuestionIds(String questionIds)
    {
        scaleQuestionMapper.deleteScaleAnswerByQuestionIds(Convert.toStrArray(questionIds));
        return scaleQuestionMapper.deleteScaleQuestionByQuestionIds(Convert.toStrArray(questionIds));
    }

    /**
     * 删除测评量表问题信息信息
     * 
     * @param questionId 测评量表问题信息主键
     * @return 结果
     */
    @Override
    public int deleteScaleQuestionByQuestionId(Long questionId)
    {
        scaleQuestionMapper.deleteScaleAnswerByQuestionId(questionId);
        return scaleQuestionMapper.deleteScaleQuestionByQuestionId(questionId);
    }

    /**
     * 新增问题选项信息信息
     * 
     * @param scaleQuestion 测评量表问题信息对象
     */
    public void insertScaleAnswer(ScaleQuestion scaleQuestion)
    {
        List<ScaleAnswer> scaleAnswerList = scaleQuestion.getScaleAnswerList();
        Long questionId = scaleQuestion.getQuestionId();
        String questionContent = scaleQuestion.getQuestionContent();
        Long scaleId = scaleQuestion.getScaleId();
        Long  factorId =  scaleQuestion.getFactorId();
        if (StringUtils.isNotNull(scaleAnswerList))
        {
            List<ScaleAnswer> list = new ArrayList<ScaleAnswer>();
            for (ScaleAnswer scaleAnswer : scaleAnswerList)
            {
                scaleAnswer.setQuestionId(questionId);
                scaleAnswer.setFactorId(factorId);
                scaleAnswer.setQuestionContent(questionContent);
                scaleAnswer.setScaleId(scaleId);
                scaleAnswer.setCreateTime(DateUtils.getNowDate());
                list.add(scaleAnswer);
            }
            if (list.size() > 0)
            {
                scaleQuestionMapper.batchScaleAnswer(list);
            }
        }
    }

    public List<ScaleQuestion> selectScaleQuestionAll(){
        return scaleQuestionMapper.selectScaleQuestionAll();
    }
}
