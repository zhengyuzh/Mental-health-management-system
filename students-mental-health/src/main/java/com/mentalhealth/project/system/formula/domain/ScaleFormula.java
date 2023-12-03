package com.mentalhealth.project.system.formula.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 测评计算公式对象 sys_scale_formula
 * 
 * @author zhengyuzhu
 * @date 2021-10-26
 */
public class ScaleFormula extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公式id */
    private Long formulaId;

    /** 公式名称 */
    @Excel(name = "公式名称")
    private String formulaName;

    /** 公式常量 */
    @Excel(name = "公式常量")
    private Double formulaConstant;

//    @Column(length=10 ,scale=2)    // length表示长度 ， scale表示小数点后位数
//    private BigDecimal money;

    /** 公式系数 */
    @Excel(name = "公式系数")
    private Double formulaCoefficient;

    public void setFormulaId(Long formulaId)
    {
        this.formulaId = formulaId;
    }

    public Long getFormulaId()
    {
        return formulaId;
    }
    public void setFormulaName(String formulaName)
    {
        this.formulaName = formulaName;
    }

    public String getFormulaName()
    {
        return formulaName;
    }
//    public void setFormulaConstant(Long formulaConstant)
//    {
//        this.formulaConstant = formulaConstant;
//    }
//
//    public Long getFormulaConstant()
//    {
//        return formulaConstant;
//    }
//    public void setFormulaCoefficient(Long formulaCoefficient)
//    {
//        this.formulaCoefficient = formulaCoefficient;
//    }
//
//    public Long getFormulaCoefficient()
//    {
//        return formulaCoefficient;
//    }


    public Double getFormulaConstant() {
        return formulaConstant;
    }

    public void setFormulaConstant(Double formulaConstant) {
        this.formulaConstant = formulaConstant;
    }

    public Double getFormulaCoefficient() {
        return formulaCoefficient;
    }

    public void setFormulaCoefficient(Double formulaCoefficient) {
        this.formulaCoefficient = formulaCoefficient;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("formulaId", getFormulaId())
            .append("formulaName", getFormulaName())
            .append("formulaConstant", getFormulaConstant())
            .append("formulaCoefficient", getFormulaCoefficient())
            .toString();
    }
}
