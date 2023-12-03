package com.mentalhealth.project.system.scale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.formula.domain.ScaleFormula;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 心理测评量表管理对象 sys_scale
 *
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public class Scale extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 测评量表id
     */
    private Long scaleId;

    /**
     * 测评量表标题
     */
    @Excel(name = "测评量表标题")
    private String scaleTitle;

    /**
     * 测评量表详情
     */
    @Excel(name = "测评量表详情")
    private String scaleDetails;

    /**
     * 发布者用户id
     */
    @Excel(name = "发布者用户id")
    private Long userId;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 量表的总分计分公式
     */
    @Excel(name = "量表的总分计分公式")
    private Long formulaId;

    /**
     * 量表分类
     */
    @Excel(name = "量表分类")
    private String scaleType;

    /**
     * 预警分数
     */
    @Excel(name = "预警分数")
    private Long earlyWarningScore;


    /**
     * 测评开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "测评开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 测评时间
     */
    @Excel(name = "测评时间")
    private String scaleTime;

    /**
     * 测评结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "测评结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 更新时间
     */
    private Date updateTiem;

    /**
     * 测评量表问题信息信息
     */
    public List<ScaleQuestion> scaleQuestionList;

    private String formulaName;

    private ScaleFormula scaleFormula;

    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }

    public ScaleFormula getScaleFormula() {
        return scaleFormula;
    }

    public void setScaleFormula(ScaleFormula scaleFormula) {
        this.scaleFormula = scaleFormula;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public void setEarlyWarningScore(Long earlyWarningScore) {
        this.earlyWarningScore = earlyWarningScore;
    }

    public Long getEarlyWarningScore() {
        return earlyWarningScore;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleTitle(String scaleTitle) {
        this.scaleTitle = scaleTitle;
    }

    public String getScaleTitle() {
        return scaleTitle;
    }

    public void setScaleDetails(String scaleDetails) {
        this.scaleDetails = scaleDetails;
    }

    public String getScaleDetails() {
        return scaleDetails;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }



    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setScaleTime(String scaleTime) {
        this.scaleTime = scaleTime;
    }

    public String getScaleTime() {
        return scaleTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setUpdateTiem(Date updateTiem) {
        this.updateTiem = updateTiem;
    }

    public Date getUpdateTiem() {
        return updateTiem;
    }

    public List<ScaleQuestion> getScaleQuestionList() {
        return scaleQuestionList;
    }

    public void setScaleQuestionList(List<ScaleQuestion> scaleQuestionList) {
        this.scaleQuestionList = scaleQuestionList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("scaleId", getScaleId())
                .append("scaleTitle", getScaleTitle())
                .append("scaleDetails", getScaleDetails())
                .append("userId", getUserId())
                .append("status", getStatus())
                .append("formulaId", getFormulaId())
                .append("earlyWarningScore", getEarlyWarningScore())
                .append("scaleType", getScaleType())
                .append("startTime", getStartTime())
                .append("scaleTime", getScaleTime())
                .append("endTime", getEndTime())
                .append("createTime", getCreateTime())
                .append("updateTiem", getUpdateTiem())
                .append("scaleQuestionList", getScaleQuestionList())
                .append("formulaName", getFormulaName())
                .append("scaleFormula", getScaleFormula())
                .toString();
    }
}
