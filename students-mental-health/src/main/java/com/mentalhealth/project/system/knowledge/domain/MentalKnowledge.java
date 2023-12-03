package com.mentalhealth.project.system.knowledge.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;


/**
 * 心理健康知识对象 sys_mental_knowledge
 *
 * @author zhengyuzhu
 * @date 2023-08-09
 */
public class MentalKnowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 心理健康知识id */
    private Long mentalKnowledgeId;

    /** 心理健康知识标题 */
    @Excel(name = "心理健康知识标题")
    private String mentalKnowledgeTitle;

    /** 心理健康知识内容 */
    @Excel(name = "心理健康知识内容")
    private String mentalKnowledgeContent;

    /** 作者 */
    @Excel(name = "作者")
    private String mentalKnowledgeAuthor;

    /** 发布者 */
    @Excel(name = "发布者")
    private String loginName;

    /** 文件存放路径 */
    @Excel(name = "文件存放路径")
    private String mentalKnowledgeFile;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer status;

    public void setMentalKnowledgeId(Long mentalKnowledgeId)
    {
        this.mentalKnowledgeId = mentalKnowledgeId;
    }

    public Long getMentalKnowledgeId()
    {
        return mentalKnowledgeId;
    }
    public void setMentalKnowledgeTitle(String mentalKnowledgeTitle)
    {
        this.mentalKnowledgeTitle = mentalKnowledgeTitle;
    }

    public String getMentalKnowledgeTitle()
    {
        return mentalKnowledgeTitle;
    }
    public void setMentalKnowledgeContent(String mentalKnowledgeContent)
    {
        this.mentalKnowledgeContent = mentalKnowledgeContent;
    }

    public String getMentalKnowledgeContent()
    {
        return mentalKnowledgeContent;
    }
    public void setMentalKnowledgeAuthor(String mentalKnowledgeAuthor)
    {
        this.mentalKnowledgeAuthor = mentalKnowledgeAuthor;
    }

    public String getMentalKnowledgeAuthor()
    {
        return mentalKnowledgeAuthor;
    }
    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginName()
    {
        return loginName;
    }
    public void setMentalKnowledgeFile(String mentalKnowledgeFile)
    {
        this.mentalKnowledgeFile = mentalKnowledgeFile;
    }

    public String getMentalKnowledgeFile()
    {
        return mentalKnowledgeFile;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("mentalKnowledgeId", getMentalKnowledgeId())
                .append("mentalKnowledgeTitle", getMentalKnowledgeTitle())
                .append("mentalKnowledgeContent", getMentalKnowledgeContent())
                .append("mentalKnowledgeAuthor", getMentalKnowledgeAuthor())
                .append("loginName", getLoginName())
                .append("mentalKnowledgeFile", getMentalKnowledgeFile())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}