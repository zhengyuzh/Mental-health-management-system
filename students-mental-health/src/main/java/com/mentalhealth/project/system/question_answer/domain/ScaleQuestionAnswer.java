package com.mentalhealth.project.system.question_answer.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 测评问题选项对象 sys_scale_question_answer
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class ScaleQuestionAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评问题id */
    private Long questionId;

    /** 所属测评量表id */
    private Long scaleId;


    /** 问题内容 */
    @Excel(name = "问题内容")
    private String questionContent;

    /** 问题序号 */
    @Excel(name = "问题序号")
    private Integer questionOrder;

    /** 问题A */
    @Excel(name = "问题A")
    private String questionA;

    /** 问题B */
    @Excel(name = "问题B")
    private String questionB;

    /** 问题C */
    @Excel(name = "问题C")
    private String questionC;

    /** 问题D */
    @Excel(name = "问题D")
    private String questionD;

    /** 问题E */
    @Excel(name = "问题E")
    private String questionE;

    /** 分数A */
    @Excel(name = "分数A")
    private Long scoreA;

    /** 分数B */
    @Excel(name = "分数B")
    private Long scoreB;

    /** 分数C */
    @Excel(name = "分数C")
    private Long scoreC;

    /** 分数D */
    @Excel(name = "分数D")
    private Long scoreD;

    /** 分数E */
    @Excel(name = "分数E")
    private Long scoreE;

    /** 所属因子维度 */
    private Long factorId;

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
    public void setQuestionOrder(Integer questionOrder)
    {
        this.questionOrder = questionOrder;
    }

    public Integer getQuestionOrder()
    {
        return questionOrder;
    }
    public void setQuestionA(String questionA)
    {
        this.questionA = questionA;
    }

    public String getQuestionA()
    {
        return questionA;
    }
    public void setQuestionB(String questionB)
    {
        this.questionB = questionB;
    }

    public String getQuestionB()
    {
        return questionB;
    }
    public void setQuestionC(String questionC)
    {
        this.questionC = questionC;
    }

    public String getQuestionC()
    {
        return questionC;
    }
    public void setQuestionD(String questionD)
    {
        this.questionD = questionD;
    }

    public String getQuestionD()
    {
        return questionD;
    }
    public void setQuestionE(String questionE)
    {
        this.questionE = questionE;
    }

    public String getQuestionE()
    {
        return questionE;
    }
    public void setScoreA(Long scoreA)
    {
        this.scoreA = scoreA;
    }

    public Long getScoreA()
    {
        return scoreA;
    }
    public void setScoreB(Long scoreB)
    {
        this.scoreB = scoreB;
    }

    public Long getScoreB()
    {
        return scoreB;
    }
    public void setScoreC(Long scoreC)
    {
        this.scoreC = scoreC;
    }

    public Long getScoreC()
    {
        return scoreC;
    }
    public void setScoreD(Long scoreD)
    {
        this.scoreD = scoreD;
    }

    public Long getScoreD()
    {
        return scoreD;
    }
    public void setScoreE(Long scoreE)
    {
        this.scoreE = scoreE;
    }

    public Long getScoreE()
    {
        return scoreE;
    }
    public void setFactorId(Long factorId)
    {
        this.factorId = factorId;
    }

    public Long getFactorId()
    {
        return factorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("questionId", getQuestionId())
            .append("scaleId", getScaleId())

            .append("questionContent", getQuestionContent())
            .append("questionOrder", getQuestionOrder())
            .append("questionA", getQuestionA())
            .append("questionB", getQuestionB())
            .append("questionC", getQuestionC())
            .append("questionD", getQuestionD())
            .append("questionE", getQuestionE())
            .append("scoreA", getScoreA())
            .append("scoreB", getScoreB())
            .append("scoreC", getScoreC())
            .append("scoreD", getScoreD())
            .append("scoreE", getScoreE())
            .append("factorId", getFactorId())

            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
