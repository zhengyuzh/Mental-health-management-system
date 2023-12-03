package com.mentalhealth.project.system.posts.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 帖子信息管理对象 sys_posts
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class Posts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子id */
    private Long postsId;

    /** 发帖用户id */
    @Excel(name = "发帖用户id")
    private Long userId;

    /** 帖子标题 */
    @Excel(name = "帖子标题")
    private String postsTitle;

    /** 帖子内容 */
    @Excel(name = "帖子内容")
    private String postsContent;

    /** 封面 */
    @Excel(name = "封面")
    private String postsImage;

    /** 帖子的回复的数量 */
    @Excel(name = "帖子的回复的数量")
    private Long commentNum;

    /** 最后回复者id */
    @Excel(name = "最后回复者id")
    private Long lastComUserId;

    /** 最后回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastComTime;

    /** 评论信息管理信息 */
    private List<Comment> commentList;

    /** 社区类型，大众还是心理社区 **/
    private Long communityType;

    public Long getCommunityType() {
        return communityType;
    }

    public void setCommunityType(Long communityType) {
        this.communityType = communityType;
    }

    public void setPostsId(Long postsId)
    {
        this.postsId = postsId;
    }

    public Long getPostsId()
    {
        return postsId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setPostsTitle(String postsTitle)
    {
        this.postsTitle = postsTitle;
    }

    @Size(min = 0, max = 100, message = "标题长度不能超过100个字符")
    public String getPostsTitle()
    {
        return postsTitle;
    }
    public void setPostsContent(String postsContent)
    {
        this.postsContent = postsContent;
    }

    @Size(min = 0, max = 1000, message = "内容长度不能超过1000个字符")
    public String getPostsContent()
    {
        return postsContent;
    }
    public void setPostsImage(String postsImage)
    {
        this.postsImage = postsImage;
    }

    public String getPostsImage()
    {
        return postsImage;
    }
    public void setCommentNum(Long commentNum)
    {
        this.commentNum = commentNum;
    }

    public Long getCommentNum()
    {
        return commentNum;
    }
    public void setLastComUserId(Long lastComUserId)
    {
        this.lastComUserId = lastComUserId;
    }

    public Long getLastComUserId()
    {
        return lastComUserId;
    }
    public void setLastComTime(Date lastComTime)
    {
        this.lastComTime = lastComTime;
    }

    public Date getLastComTime()
    {
        return lastComTime;
    }

    public List<Comment> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList)
    {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("postsId", getPostsId())
            .append("userId", getUserId())
            .append("postsTitle", getPostsTitle())
            .append("postsContent", getPostsContent())
            .append("postsImage", getPostsImage())
            .append("commentNum", getCommentNum())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("lastComUserId", getLastComUserId())
            .append("lastComTime", getLastComTime())
            .append("commentList", getCommentList())
                .append("communityType",getCommunityType())
            .toString();
    }
}
