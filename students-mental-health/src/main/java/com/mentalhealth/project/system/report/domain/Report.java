package com.mentalhealth.project.system.report.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 举报信息对象 sys_report
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class Report extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 举报id */
    private Long reportId;

    /** 被举报帖子id */
    @Excel(name = "被举报帖子id")
    private Long postsId;

    /** 被举报内容 */
    @Excel(name = "被举报内容")
    private String postsComment;

    /** 举报类型 */
    @Excel(name = "举报类型")
    private String reportType;

    /** 举报内容 */
    @Excel(name = "举报内容")
    private String reportComment;

    /** 举报者id */
    @Excel(name = "举报者id")
    private Long reportUserId;

    /** 反馈信息 */
    @Excel(name = "反馈信息")
    private String feedback;

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }
    public void setPostsId(Long postsId)
    {
        this.postsId = postsId;
    }

    public Long getPostsId()
    {
        return postsId;
    }
    public void setPostsComment(String postsComment)
    {
        this.postsComment = postsComment;
    }

    public String getPostsComment()
    {
        return postsComment;
    }
    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }

    public String getReportType()
    {
        return reportType;
    }
    public void setReportComment(String reportComment)
    {
        this.reportComment = reportComment;
    }

    public String getReportComment()
    {
        return reportComment;
    }
    public void setReportUserId(Long reportUserId)
    {
        this.reportUserId = reportUserId;
    }

    public Long getReportUserId()
    {
        return reportUserId;
    }
    public void setFeedback(String feedback)
    {
        this.feedback = feedback;
    }

    public String getFeedback()
    {
        return feedback;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId", getReportId())
            .append("postsId", getPostsId())
            .append("postsComment", getPostsComment())
            .append("reportType", getReportType())
            .append("reportComment", getReportComment())
            .append("reportUserId", getReportUserId())
            .append("feedback", getFeedback())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
