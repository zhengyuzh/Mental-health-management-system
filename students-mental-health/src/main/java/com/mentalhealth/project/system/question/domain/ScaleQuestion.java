package com.mentalhealth.project.system.question.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.answer.domain.ScaleAnswer;
import com.mentalhealth.project.system.factor.domain.ScaleFactor;
import com.mentalhealth.project.system.scale.domain.Scale;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * 测评量表问题信息对象 sys_scale_question
 *
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class ScaleQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评问题id */
    @Excel(name = "测评问题id")
    private Long questionId;

    /** 所属测评量表id */
    private Long scaleId;

    /** 问题序号 */
    @Excel(name = "问题序号")
    private Integer questionOrder;

    /** 测评量表标题 */
    @Excel(name = "测评量表标题")
    private String scaleTitle;

    /** 问题内容 */
    @Excel(name = "问题内容")
    private String questionContent;

    /** 所属因子维度 */
    private Long factorId;

    /** 因子名称 */
    @Excel(name = "因子名称")
    private String factorName;

    /** 选项信息信息 */
    public List<ScaleAnswer> scaleAnswerList;

    private ScaleFactor scaleFactor;
    private Scale scale;

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public ScaleFactor getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(ScaleFactor scaleFactor) {
        this.scaleFactor = scaleFactor;
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
    public void setQuestionOrder(Integer questionOrder)
    {
        this.questionOrder = questionOrder;
    }

    public Integer getQuestionOrder()
    {
        return questionOrder;
    }
    public void setScaleTitle(String scaleTitle)
    {
        this.scaleTitle = scaleTitle;
    }

    public String getScaleTitle()
    {
        return scaleTitle;
    }
    public void setQuestionContent(String questionContent)
    {
        this.questionContent = questionContent;
    }

    public String getQuestionContent()
    {
        return questionContent;
    }
    public void setFactorId(Long factorId)
    {
        this.factorId = factorId;
    }

    public Long getFactorId()
    {
        return factorId;
    }
    public void setFactorName(String factorName)
    {
        this.factorName = factorName;
    }

    public String getFactorName()
    {
        return factorName;
    }

    public List<ScaleAnswer> getScaleAnswerList()
    {
        return scaleAnswerList;
    }

    public void setScaleAnswerList(List<ScaleAnswer> scaleAnswerList)
    {
        this.scaleAnswerList = scaleAnswerList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("questionId", getQuestionId())
                .append("scaleId", getScaleId())
                .append("questionOrder", getQuestionOrder())
                .append("scaleTitle", getScaleTitle())
                .append("scaleFactor", getScaleFactor())
                .append("scale", getScale())
                .append("questionContent", getQuestionContent())
                .append("factorId", getFactorId())
                .append("createTime", getCreateTime())
                .append("factorName", getFactorName())
                .append("updateTime", getUpdateTime())
                .append("scaleAnswerList", getScaleAnswerList())
                .toString();
    }
}