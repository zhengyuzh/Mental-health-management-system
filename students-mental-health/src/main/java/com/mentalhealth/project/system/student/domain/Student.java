package com.mentalhealth.project.system.student.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.dept.domain.Dept;
import com.mentalhealth.project.system.major.domain.Major;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 学生信息表对象 sys_student
 * 
 * @author zhengyuzhu
 * @date 2021-10-15
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long stuId;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String loginName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;


    /** 学生性别（0男 1女 2未知） */
    @Excel(name = "学生性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private String birth;

    /** 年级(字典获取) */
    @Excel(name = "年级")
    private String grade;

    /** 所属专业id */
    @Excel(name = "所属专业id")
    private Long majorId;

    private Major major;

    /** 班级(字典获取) */
    @Excel(name = "班级")
    private String classes;

    /** 所属二级学院id */
    @Excel(name = "所属二级学院id")
    private Long deptId;

    private Dept dept;

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 个人照片路径 */
    @Excel(name = "个人照片路径")
    private String avatar;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginName()
    {
        return loginName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setStuName(String stuName)
    {
        this.stuName = stuName;
    }

    public String getStuName()
    {
        return stuName;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("loginName", getLoginName())
            .append("password", getPassword())
            .append("stuName", getStuName())
            .append("sex", getSex())
            .append("birth", getBirth())
            .append("grade", getGrade())
            .append("majorId", getMajorId())
            .append("deptId", getDeptId())
            .append("email", getEmail())
            .append("avatar", getAvatar())
            .append("status", getStatus())
            .append("phone", getPhone())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
