package com.mentalhealth.project.system.result.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.scale.domain.Scale;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 测评结果参考信息对象 sys_scale_result
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class ScaleResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评结果id */
    private Long resultId;

    /** 测评量表id */
    @Excel(name = "测评量表id")
    private Long scaleId;

    /** 测评量表标题 */
    @Excel(name = "测评量表标题")
    private String scaleTitle;

    /** 结果详情 */
    @Excel(name = "测评建议")
    private String resultDetails;

    /** 结论 */
    @Excel(name = "分数范围和结果提示")
    private String resultContent;

    private Scale scale;

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public void setResultId(Long resultId)
    {
        this.resultId = resultId;
    }

    public Long getResultId()
    {
        return resultId;
    }
    public void setScaleId(Long scaleId)
    {
        this.scaleId = scaleId;
    }

    public Long getScaleId()
    {
        return scaleId;
    }
    public void setScaleTitle(String scaleTitle)
    {
        this.scaleTitle = scaleTitle;
    }

    public String getScaleTitle()
    {
        return scaleTitle;
    }
    public void setResultDetails(String resultDetails)
    {
        this.resultDetails = resultDetails;
    }

    public String getResultDetails()
    {
        return resultDetails;
    }
    public void setResultContent(String resultContent)
    {
        this.resultContent = resultContent;
    }

    public String getResultContent()
    {
        return resultContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("scaleId", getScaleId())
            .append("scaleTitle", getScaleTitle())
            .append("resultDetails", getResultDetails())
            .append("resultContent", getResultContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("scale", getScale())
            .toString();
    }
}
