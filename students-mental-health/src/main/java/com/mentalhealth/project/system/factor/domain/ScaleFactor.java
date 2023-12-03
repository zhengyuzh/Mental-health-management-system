package com.mentalhealth.project.system.factor.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.formula.domain.ScaleFormula;
import com.mentalhealth.project.system.scale.domain.Scale;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 测评因子信息对象 sys_scale_factor
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public class ScaleFactor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 因子id */
    private Long factorId;

    /** 因子名称 */
    @Excel(name = "因子名称")
    private String factorName;

    /** 因子所属量表id */
    @Excel(name = "因子所属量表id")
    private Long scaleId;

    /**
     * 测评量表标题
     */
    @Excel(name = "测评量表标题")
    private String scaleTitle;

    /** 因子分结果提示 */
    @Excel(name = "因子分结果提示")
    private String factorResult;

    /** 因子分计算公式 */
    @Excel(name = "因子分计算公式")
    private Long formulaId;

    private String formulaName;

    private Scale scale;

    private ScaleFormula scaleFormula;


    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public ScaleFormula getScaleFormula() {
        return scaleFormula;
    }

    public void setScaleFormula(ScaleFormula scaleFormula) {
        this.scaleFormula = scaleFormula;
    }

    public String getScaleTitle() {
        return scaleTitle;
    }

    public void setScaleTitle(String scaleTitle) {
        this.scaleTitle = scaleTitle;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
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
    public void setScaleId(Long scaleId)
    {
        this.scaleId = scaleId;
    }

    public Long getScaleId()
    {
        return scaleId;
    }
    public void setFactorResult(String factorResult)
    {
        this.factorResult = factorResult;
    }

    public String getFactorResult()
    {
        return factorResult;
    }
    public void setFormulaId(Long formulaId)
    {
        this.formulaId = formulaId;
    }

    public Long getFormulaId()
    {
        return formulaId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("factorId", getFactorId())
            .append("factorName", getFactorName())
            .append("scaleId", getScaleId())
            .append("scaleTitle", getScaleTitle())
            .append("factorResult", getFactorResult())
            .append("formulaId", getFormulaId())
            .append("formulaName", getFormulaName())
            .append("scaleFormula", getScaleFormula())
            .append("scale", getScale())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
