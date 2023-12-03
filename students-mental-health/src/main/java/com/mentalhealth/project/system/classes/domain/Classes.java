package com.mentalhealth.project.system.classes.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 班级信息对象 sys_classes
 * 
 * @author zhengyuzhu
 * @date 2021-11-12
 */
public class Classes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级编号 */
    private Long classesId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String classesName;

    /** 所属学院 */
    @Excel(name = "所属学院")
    private Long deptId;

    public void setClassesId(Long classesId)
    {
        this.classesId = classesId;
    }

    public Long getClassesId()
    {
        return classesId;
    }
    public void setClassesName(String classesName)
    {
        this.classesName = classesName;
    }

    public String getClassesName()
    {
        return classesName;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("classesId", getClassesId())
            .append("classesName", getClassesName())
            .append("deptId", getDeptId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
