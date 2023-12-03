package com.mentalhealth.project.system.major.domain;

import com.mentalhealth.project.system.dept.domain.Dept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;

/**
 * 专业对象 major
 * 
 * @author ruoyi
 * @date 2021-09-30
 */
public class Major extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 专业id */
    private Long majorId;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 所属部门id */
    @Excel(name = "所属部门id")
    private Long deptId;

    private Dept dept;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setMajorId(Long majorId)
    {
        this.majorId = majorId;
    }

    public Long getMajorId()
    {
        return majorId;
    }
    public void setMajorName(String majorName)
    {
        this.majorName = majorName;
    }

    public String getMajorName()
    {
        return majorName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("majorId", getMajorId())
                .append("majorName", getMajorName())
                .append("deptId", getDeptId())
                .append("dept", getDept())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
