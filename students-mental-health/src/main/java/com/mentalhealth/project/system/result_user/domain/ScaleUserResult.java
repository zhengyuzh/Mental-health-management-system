package com.mentalhealth.project.system.result_user.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 测评成绩报告信息对象 sys_scale_user_result
 *
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class ScaleUserResult extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 测评结果id
     */
    private Long resultId;

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
     * 用户id
     */
    private Long userId;

    /**
     * 登录账号
     */
    @Excel(name = "登录账号")
    private String loginName;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String userName;

    /**
     * 总分
     */
    @Excel(name = "总分")
    private Double countScore;

    public Double getCountScore() {
        return countScore;
    }

    public void setCountScore(Double countScore) {
        this.countScore = countScore;
    }

    /**
     * 因子的分数
     */
    @Excel(name = "因子的分数")
    private String factorScore;

    /**
     * 分数范围和结果提示
     */
    @Excel(name = "分数范围和结果提示")
    private String resultContent;

    /**
     * 因子的分数范围及结果提示
     */
    @Excel(name = "因子的分数范围及结果提示")
    private String factorResult;

    /**
     * 用户选择数据
     */
    @Excel(name = "用户选择数据")
    private String userAnswer;

    /** 班级id */
    @Excel(name = "班级id")
    private String classesName;



    /** 学院id */
    @Excel(name = "学院id")
    private String deptName;

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }


    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getResultId() {
        return resultId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }




    public void setFactorScore(String factorScore) {
        this.factorScore = factorScore;
    }

    public String getFactorScore() {
        return factorScore;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setFactorResult(String factorResult) {
        this.factorResult = factorResult;
    }

    public String getFactorResult() {
        return factorResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("resultId", getResultId())
                .append("scaleId", getScaleId())
                .append("scaleTitle", getScaleTitle())
                .append("userId", getUserId())
                .append("loginName", getLoginName())
                .append("userName", getUserName())
                .append("classesName", getClassesName())
                .append("deptName", getDeptName())
                .append("countScore", getCountScore())
                .append("factorScore", getFactorScore())
                .append("resultContent", getResultContent())
                .append("factorResult", getFactorResult())
                .append("userAnswer", getUserAnswer())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
