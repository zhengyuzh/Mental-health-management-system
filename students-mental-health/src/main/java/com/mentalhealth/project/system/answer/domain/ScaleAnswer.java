package com.mentalhealth.project.system.answer.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.factor.domain.ScaleFactor;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 选项信息对象 sys_scale_answer
 *
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class ScaleAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评答案id */
    private Long answerId;

    /** 测评问题id */
    @Excel(name = "测评问题id")
    private Long questionId;

    /** 测评量表id */
    private Long scaleId;

    /** 问题题目 */
    @Excel(name = "问题题目")
    private String questionContent;

    /** 选项 */
    @Excel(name = "选项")
    private String answerOption;

    /** 得分 */
    @Excel(name = "得分")
    private Double score;

    private ScaleQuestion scaleQuestion;

    private ScaleFactor scaleFactor;

    private Long factorId;

    public ScaleFactor getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(ScaleFactor scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public Long getFactorId() {
        return factorId;
    }

    public void setFactorId(Long factorId) {
        this.factorId = factorId;
    }

    public ScaleQuestion getScaleQuestion() {
        return scaleQuestion;
    }

    public void setScaleQuestion(ScaleQuestion scaleQuestion) {
        this.scaleQuestion = scaleQuestion;
    }

    public void setAnswerId(Long answerId)
    {
        this.answerId = answerId;
    }

    public Long getAnswerId()
    {
        return answerId;
    }
    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public Long getQuestionId()
    {
        return questionId;
    }
    public void setScaleId(Long scaleId)
    {
        this.scaleId = scaleId;
    }

    public Long getScaleId()
    {
        return scaleId;
    }
    public void setQuestionContent(String questionContent)
    {
        this.questionContent = questionContent;
    }

    public String getQuestionContent()
    {
        return questionContent;
    }
    public void setAnswerOption(String answerOption)
    {
        this.answerOption = answerOption;
    }

    public String getAnswerOption()
    {
        return answerOption;
    }
//    public void setScore(Long score)
//    {
//        this.score = score;
//    }
//
//    public Long getScore()
//    {
//        return score;
//    }


    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("answerId", getAnswerId())
                .append("questionId", getQuestionId())
                .append("scaleId", getScaleId())
                .append("questionContent", getQuestionContent())
                .append("answerOption", getAnswerOption())
                .append("score", getScore())
                .append("scaleQuestion", getScaleQuestion())
                .append("scaleFactor", getScaleFactor())
                .append("factorId", getFactorId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}