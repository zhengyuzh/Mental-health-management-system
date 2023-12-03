package com.mentalhealth.project.system.posts.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.Size;

/**
 * 评论信息管理对象 sys_comment
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long commentId;

    /** 帖子id */
    @Excel(name = "帖子id")
    private Long postsId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 评论者头像 */
    @Excel(name = "评论者头像")
    private String avatar;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 父评论的用户id */
    @Excel(name = "父评论的用户id")
    private Long parentUserId;

    /** 父评论id */
    @Excel(name = "父评论id")
    private Long parentCommentId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
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
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setParentUserId(Long parentUserId) 
    {
        this.parentUserId = parentUserId;
    }

    public Long getParentUserId() 
    {
        return parentUserId;
    }
    public void setParentCommentId(Long parentCommentId) 
    {
        this.parentCommentId = parentCommentId;
    }

    @Size(min = 0, max = 1000, message = "内容长度不能超过1000个字符")
    public Long getParentCommentId() 
    {
        return parentCommentId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("postsId", getPostsId())
            .append("userId", getUserId())
            .append("avatar", getAvatar())
            .append("userName", getUserName())
            .append("parentUserId", getParentUserId())
            .append("parentCommentId", getParentCommentId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
